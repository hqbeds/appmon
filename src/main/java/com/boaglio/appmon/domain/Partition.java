package com.boaglio.appmon.domain;

import com.boaglio.appmon.util.FormatUtil;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Partition {

	private String name;
	private long totalSpace;
	private long usabledSpace;
	private long usedSpace;

	public Partition() {}

	public Partition(String name,long totalSpace,long usabledSpace,long usedSpace) {
		this.name = name;
		this.totalSpace = totalSpace;
		this.usabledSpace = usabledSpace;
		this.usedSpace = usedSpace;
	}

	public String getName() {
		return name;
	}

	public String getTotalSpace() {
		return FormatUtil.humanReadableByteCount(totalSpace);
	}

	public String getUsabledSpace() {
		return FormatUtil.humanReadableByteCount(usabledSpace);
	}

	public int getUsabledSpacePercent() {
		return (int) (usabledSpace * 100 / totalSpace);
	}

	public String getUsedSpace() {
		return FormatUtil.humanReadableByteCount(usedSpace);
	}

	public int getUsedSpacePercent() {
		return (int) (usedSpace * 100 / totalSpace);
	}

}
