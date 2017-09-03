package com.boaglio.appmon.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DynamicConfig {

	@Value("${appmon.data:none}")
	private String dataDiretory;

	@Value("${appmon.debug:false}")
	private boolean debug;

	@Value("${appmon.config:appmon.json}")
	private String config;

	public String getDataDiretory() {
		return dataDiretory;
	}

	public void setDataDiretory(String dataDiretory) {
		this.dataDiretory = dataDiretory;
	}

	public boolean isDebug() {
		return debug;
	}

	public void setDebug(boolean debug) {
		this.debug = debug;
	}

	public String getConfig() {
		return config;
	}

	public void setConfig(String config) {
		this.config = config;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ( (dataDiretory == null) ? 0 : dataDiretory.hashCode());
		result = prime * result + ( (config == null) ? 0 : config.hashCode());
		result = prime * result + (debug ? 1231 : 1237);
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
		DynamicConfig other = (DynamicConfig) obj;
		if (dataDiretory == null) {
			if (other.dataDiretory != null)
				return false;
		} else if (!dataDiretory.equals(other.dataDiretory))
			return false;
		if (config == null) {
			if (other.config != null)
				return false;
		} else if (!config.equals(other.config))
			return false;
		if (debug != other.debug)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "DynamicConfig [dataDiretory=" + dataDiretory + ", debug=" + debug + ", config=" + config + "]";
	}

}
