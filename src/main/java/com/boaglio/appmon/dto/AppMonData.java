package com.boaglio.appmon.dto;

import java.time.LocalDateTime;
import java.util.Map;

import com.boaglio.appmon.config.DynamicConfig;
import com.boaglio.appmon.type.Operation;

public class AppMonData {

	private DynamicConfig dynamicConfig;

	private Map<Stats,LocalDateTime> serviceLogs;

	private Map<Operation,LocalDateTime> appMonlogs;

	public DynamicConfig getDynamicConfig() {
		return dynamicConfig;
	}

	public void setDynamicConfig(DynamicConfig dynamicConfig) {
		this.dynamicConfig = dynamicConfig;
	}

	public Map<Stats,LocalDateTime> getServiceLogs() {
		return serviceLogs;
	}

	public void setServiceLogs(Map<Stats,LocalDateTime> serviceLogs) {
		this.serviceLogs = serviceLogs;
	}

	public Map<Operation,LocalDateTime> getAppMonlogs() {
		return appMonlogs;
	}

	public void setAppMonlogs(Map<Operation,LocalDateTime> appMonlogs) {
		this.appMonlogs = appMonlogs;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ( (appMonlogs == null) ? 0 : appMonlogs.hashCode());
		result = prime * result + ( (dynamicConfig == null) ? 0 : dynamicConfig.hashCode());
		result = prime * result + ( (serviceLogs == null) ? 0 : serviceLogs.hashCode());
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
		AppMonData other = (AppMonData) obj;
		if (appMonlogs == null) {
			if (other.appMonlogs != null)
				return false;
		} else if (!appMonlogs.equals(other.appMonlogs))
			return false;
		if (dynamicConfig == null) {
			if (other.dynamicConfig != null)
				return false;
		} else if (!dynamicConfig.equals(other.dynamicConfig))
			return false;
		if (serviceLogs == null) {
			if (other.serviceLogs != null)
				return false;
		} else if (!serviceLogs.equals(other.serviceLogs))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AppMonData [dynamicConfig=" + dynamicConfig + "]";
	}

}
