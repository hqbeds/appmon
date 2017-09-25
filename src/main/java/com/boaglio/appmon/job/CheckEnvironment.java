package com.boaglio.appmon.job;

import java.io.File;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.boaglio.appmon.config.DynamicConfig;
import com.boaglio.appmon.dto.Config;
import com.boaglio.appmon.dto.FullStats;
import com.boaglio.appmon.dto.ServiceStats;
import com.boaglio.appmon.dto.Stats;
import com.boaglio.appmon.exception.InvalidDataDirectoryException;
import com.boaglio.appmon.type.ServiceStatus;
import com.boaglio.appmon.util.DataDirUtil;
import com.boaglio.appmon.util.FileSystemUtil;
import com.boaglio.appmon.util.FileUtil;
import com.boaglio.appmon.util.SocketUtil;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CheckEnvironment {

	private DynamicConfig dynamicConfig;
	private DataDirUtil dataDirUtil;
	private FileSystemUtil fileSystemUtil;
	private FullStats fullStats;

	private static final int CHECK_IN_SECONDS = 10 * 1000;
	private static final int TIMEOUT = 200;
	private static final int DEFAULT_RETENTION_SIZE = 10;

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

	@Autowired
	public CheckEnvironment(FileSystemUtil fileSystemUtil,DynamicConfig dynamicConfig,DataDirUtil dataDirUtil,FullStats fullStats) {
		this.fileSystemUtil = fileSystemUtil;
		this.dynamicConfig = dynamicConfig;
		this.dataDirUtil = dataDirUtil;
		this.fullStats = fullStats;
	}

	@Scheduled(fixedRate = CHECK_IN_SECONDS)
	public void checkEnvironment() throws InvalidDataDirectoryException,JsonParseException,JsonMappingException,IOException {
		Config config = Config.getInstance();
		if (dynamicConfig.isDebug()) {
			System.out.println("Config = " + config);
		}
		if (config != null && config.getServices() == null) {
			if (dynamicConfig.isDebug())
				System.out.println(" AppMon data dir = " + dynamicConfig.getDataDiretory());

			dataDirUtil.init();

			if (dynamicConfig.isDebug()) {
				System.out.println(" AppMon conf = " + dynamicConfig.getConfig());
				System.out.println("check  at " + dateFormat.format(new Date()));
			}

			fileSystemUtil.refresh();

			ObjectMapper mapper = new ObjectMapper();

			String configText = null;
			try {

				String confDir = dynamicConfig.getDataDiretory();
				if (FileUtil.existsFileFromDirectory(System.getProperty("user.dir"),dynamicConfig.getConfig())) {
					confDir = System.getProperty("user.dir");
				} else if (!FileUtil.existsFileFromDirectory(dynamicConfig.getDataDiretory(),dynamicConfig.getConfig())) { throw new NoSuchFileException("Configuration file not found in " + dynamicConfig.getDataDiretory() + File.separator + dynamicConfig.getConfig()); }

				configText = FileUtil.getFileFromDirectory(confDir,dynamicConfig.getConfig());

			} catch (NoSuchFileException nsfe) {

				System.out.println("Configuration file not found!");
				nsfe.printStackTrace();
				System.exit(0);
			}

			if (dynamicConfig.isDebug()) {
				System.out.println("JSON = " + configText);
			}

			config = mapper.readValue(configText,Config.class);

			if (config.getDebug() != null) {
				dynamicConfig.setDebug(config.getDebug());
			}
			if (dynamicConfig.isDebug()) {
				System.out.println("config = " + config);
				System.out.println("config description = " + config.getDescription());
			}

			if (config.getServices() != null) {
				config.getServices().stream().forEach(System.out::println);

			} else {

				if (dynamicConfig.isDebug())
					System.out.println("no services to monitor... wtf?");
			}

			if (config.getRetentionSize() == null) {
				config.setRetentionSize(DEFAULT_RETENTION_SIZE);
			}

			Config.updateInstance(config);
		}
		// criar rotina para ler os servicos
		List<ServiceStats> services = new ArrayList<ServiceStats>();
		if (config != null && config.getServices() != null)
			for (com.boaglio.appmon.domain.Service service : config.getServices()) {
				if (dynamicConfig.isDebug()) {
					System.out.println("Checking " + service.getName() + " on port " + service.getPort());
				}

				final ExecutorService es = Executors.newFixedThreadPool(20);

				Future<Boolean> portStatus = SocketUtil.portIsOpen(es,service.getPort(),TIMEOUT);
				es.shutdown();

				ServiceStatus serviceStatus = ServiceStatus.DOWN;
				try {
					if (portStatus.get()) {
						serviceStatus = ServiceStatus.UP;
					}
				} catch (InterruptedException | ExecutionException e) {
					e.printStackTrace();
				}
				services.add(new ServiceStats(service,serviceStatus));

			}
		Stats stats = new Stats();
		stats.setServiceStats(services);
		fullStats.updateStats(stats);
		// escrever os nos logs as metricas
		if (dynamicConfig.isDebug()) {
			System.out.println(stats);
		}

	}
}
