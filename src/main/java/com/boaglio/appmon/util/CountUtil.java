package com.boaglio.appmon.util;


public class CountUtil {

	public static String humanReadableByteCount(long bytes) {
	    int unit = 1024;
	    if (bytes < unit) return bytes + " B";
	    int exp = (int) (Math.log(bytes) / Math.log(unit));
	    char pre = ("KMGTPE").charAt(exp-1) ;
	    return String.format("%.1f %sB", bytes / Math.pow(unit, exp), pre);
	}
	
}
