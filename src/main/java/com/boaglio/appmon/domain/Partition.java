package com.boaglio.appmon.domain;

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

	public long getTotalSpace() {
		return totalSpace;
	}

	public long getUsabledSpace() {
		return usabledSpace;
	}

	public long getUsedSpace() {
		return usedSpace;
	}

}
