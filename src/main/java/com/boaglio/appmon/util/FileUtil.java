package com.boaglio.appmon.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class FileUtil {

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

}
