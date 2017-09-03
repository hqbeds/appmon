package com.boaglio.appmon.util;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.boaglio.appmon.config.DynamicConfig;
import com.boaglio.appmon.exception.InvalidDataDirectoryException;

@Component
public class DataDirUtil {
	

	private static final String JAVA_TEMP_DIR = "java.io.tmpdir";
	private final String NO_DATA_DIR="none";
	private final String DATA_SUBDIR="appmon";
	
	private DynamicConfig dynamicConfig;
	private FileSystemUtil fileSystemUtil;
	
	@Autowired
	public DataDirUtil( DynamicConfig dynamicConfig,FileSystemUtil fileSystemUtil) {
		this.dynamicConfig=dynamicConfig;
		this.fileSystemUtil=fileSystemUtil;
	}

	
	public void init() throws InvalidDataDirectoryException {
		
		if (dynamicConfig.getDataDiretory().equals(NO_DATA_DIR) ){
			
			String tempDir = System.getProperty(JAVA_TEMP_DIR);
			dynamicConfig.setDataDiretory( tempDir + File.separator + DATA_SUBDIR );
			
		}
		fileSystemUtil.validateDiretory();
		
	}

}
