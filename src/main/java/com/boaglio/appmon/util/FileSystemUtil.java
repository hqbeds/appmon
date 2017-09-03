package com.boaglio.appmon.util;

import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.boaglio.appmon.config.DynamicConfig;
import com.boaglio.appmon.domain.FileSystem;
import com.boaglio.appmon.domain.Partition;
import com.boaglio.appmon.exception.InvalidDataDirectoryException;

@Component
public class FileSystemUtil implements Refreshable {

	private DynamicConfig dynamicConfig;

	@Autowired
	public FileSystemUtil(DynamicConfig dynamicConfig) {
		this.dynamicConfig = dynamicConfig;
	}

	@Override
	public FileSystem refresh() {

		FileSystem fileSystem = new FileSystem();
		List<Partition> rootDirectories = new ArrayList<Partition>();

		for (Path path : FileSystems.getDefault().getRootDirectories()) {
			File aDrive = path.toFile();
			long totalSpace = aDrive.getTotalSpace();
			long usabledSpace = aDrive.getUsableSpace();
			long usedSpace = totalSpace - usabledSpace;
			if (dynamicConfig.isDebug()) {
				System.out.println("    partition = " + path);
				System.out.println("   used space = " + CountUtil.humanReadableByteCount(usedSpace));
				System.out.println("usabled space = " + CountUtil.humanReadableByteCount(usabledSpace));
				System.out.println("        total = " + CountUtil.humanReadableByteCount(totalSpace));
			}

			Partition partition = new Partition(path.toString(),totalSpace,usabledSpace,usedSpace);
			rootDirectories.add(partition);

		}
		fileSystem.setRootDirectories(rootDirectories);

		return fileSystem;
	}

	public void validateDiretory() throws InvalidDataDirectoryException {

		File dataDir = new File(dynamicConfig.getDataDiretory());

		if (!dataDir.exists()) {
			try {
				dataDir.mkdirs();
			} catch (Exception e) {
				throw new InvalidDataDirectoryException("unable to create data directory: " + dynamicConfig.getDataDiretory());
			}
		}

		if (!dataDir.exists() || !dataDir.isDirectory()) { throw new InvalidDataDirectoryException("Invalid data directory: " + dynamicConfig.getDataDiretory()); }

	}

}
