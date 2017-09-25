package com.boaglio.appmon.dto;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Component;

import com.boaglio.appmon.domain.FileSystem;
import com.boaglio.appmon.util.RotateList;
import com.fasterxml.jackson.annotation.JsonInclude;

@Component
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Stats implements Comparable<Stats> {

	public Stats(FileSystem fileSystem,List<ServiceStats> serviceStats) {
		this.date = LocalDateTime.now();
		this.fileSystem = fileSystem;
		this.serviceStats = serviceStats;
		try {
			this.hostname = InetAddress.getLocalHost().getHostName();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

	public Stats() {
		this.date = LocalDateTime.now();
		this.serviceStats = new RotateList<ServiceStats>();
		try {
			this.hostname = InetAddress.getLocalHost().getHostName();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

	private String hostname;

	private LocalDateTime date;

	private FileSystem fileSystem;

	private List<ServiceStats> serviceStats;

	public void updateInstance(Stats s) {
		this.date = s.date;
		this.fileSystem = s.fileSystem;
		this.serviceStats = s.serviceStats;
	}

	public FileSystem getFileSystem() {
		return fileSystem;
	}

	public List<ServiceStats> getServiceStats() {
		return serviceStats;
	}

	public void setServiceStats(List<ServiceStats> serviceStats) {
		this.serviceStats = serviceStats;
	}

	public void setFileSystem(FileSystem fileSystem) {
		this.fileSystem = fileSystem;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public String getHostname() {
		return hostname;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ( (date == null) ? 0 : date.hashCode());
		result = prime * result + ( (fileSystem == null) ? 0 : fileSystem.hashCode());
		result = prime * result + ( (serviceStats == null) ? 0 : serviceStats.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Stats other = (Stats) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (fileSystem == null) {
			if (other.fileSystem != null)
				return false;
		} else if (!fileSystem.equals(other.fileSystem))
			return false;
		if (serviceStats == null) {
			if (other.serviceStats != null)
				return false;
		} else if (!serviceStats.equals(other.serviceStats))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Stats [date=" + date + ", fileSystem=" + fileSystem + ", serviceStats=" + serviceStats + "]";
	}

	@Override
	public int compareTo(Stats s) {
		return s.date.compareTo(this.date);
	}

}
