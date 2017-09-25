package com.boaglio.appmon.dto;

import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.boaglio.appmon.util.RotateList;
import com.fasterxml.jackson.annotation.JsonInclude;

@Component
@Scope(scopeName = "singleton")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FullStats {

	private List<Stats> allStats;

	private Stats lastStats;

	public FullStats() {
		allStats = new RotateList<Stats>();
		lastStats = new Stats();
	}

	public List<Stats> getAllStats() {
		return allStats;
	}

	public void setAllStats(List<Stats> allStats) {
		this.allStats = allStats;
	}

	public Stats getLastStats() {
		return lastStats;
	}

	public void setLastStats(Stats lastStats) {
		this.lastStats = lastStats;
	}

	public void updateStats(Stats s) {
		allStats.add(s);
		Collections.sort(allStats);
		lastStats = s;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ( (allStats == null) ? 0 : allStats.hashCode());
		result = prime * result + ( (lastStats == null) ? 0 : lastStats.hashCode());
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
		FullStats other = (FullStats) obj;
		if (allStats == null) {
			if (other.allStats != null)
				return false;
		} else if (!allStats.equals(other.allStats))
			return false;
		if (lastStats == null) {
			if (other.lastStats != null)
				return false;
		} else if (!lastStats.equals(other.lastStats))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "FullStats [allStats=" + allStats + ", lastStats=" + lastStats + "]";
	}

}
