package com.boaglio.appmon.util;

public class FormatUtil {

	public static String humanReadableBitCount(long bytes) {
		int unit = 1000;
		if (bytes < unit)
			return bytes + " B";
		int exp = (int) (Math.log(bytes) / Math.log(unit));
		String pre = ("kMGTPE").charAt(exp - 1) + ("");
		return String.format("%.1f %sB",bytes / Math.pow(unit,exp),pre);
	}

	public static String humanReadableByteCount(long bytes) {
		int unit = 1024;
		if (bytes < unit)
			return bytes + " B";
		int exp = (int) (Math.log(bytes) / Math.log(unit));
		String pre = ("KMGTPE").charAt(exp - 1) + ("i");
		return String.format("%.1f %sB",bytes / Math.pow(unit,exp),pre);
	}

}
