package com.boaglio.appmon.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class FileSystem {

	private List<Partition> rootDirectories;

	public List<Partition> getRootDirectories() {
		return rootDirectories;
	}

	public void setRootDirectories(List<Partition> rootDirectories) {
		this.rootDirectories = rootDirectories;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ( (rootDirectories == null) ? 0 : rootDirectories.hashCode());
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
		FileSystem other = (FileSystem) obj;
		if (rootDirectories == null) {
			if (other.rootDirectories != null)
				return false;
		} else if (!rootDirectories.equals(other.rootDirectories))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "FileSystem [rootDirectories=" + rootDirectories + "]";
	}

}
