package com.boaglio.appmon.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import com.boaglio.appmon.dto.ServiceStats;
import com.boaglio.appmon.dto.Stats;

public class FileUtil {

	public static void createOrUpdateLog(Stats stats) {
		createOrUpdateServiceLog(stats.getServiceStats());
	}

	public static boolean existsFileFromDirectory(String directory,String fileName) throws IOException {
		File f = new File(directory + File.separator + fileName);
		System.out.println("exists in [" + directory + File.separator + fileName + "]? " + f.exists());
		return f.exists();
	}

	public static String getFileFromDirectory(String directory,String fileName) throws IOException {
		File f = new File(directory + File.separator + fileName);
		Path path = f.toPath();
		return new String(java.nio.file.Files.readAllBytes(path));
	}

	private static void createOrUpdateServiceLog(List<ServiceStats> service) {

	}

	private static void createOrUpdateAppMonLog(List<ServiceStats> service) {

	}

}
