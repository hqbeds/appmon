package com.boaglio.appmon.dto;

import java.io.Serializable;
import java.util.List;

public class Config implements Serializable {

	private static final long serialVersionUID = 1297479827022295470L;

	private static Config instance = new Config();

	public static Config getInstance() {
		return instance;
	}

	public static void updateInstance(Config c) {
		instance = c;
	}

	private Config() {}

	private String description;

	private List<com.boaglio.appmon.domain.Service> services;

	private Boolean debug;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<com.boaglio.appmon.domain.Service> getServices() {
		return services;
	}

	public void setServices(List<com.boaglio.appmon.domain.Service> services) {
		this.services = services;
	}

	public Boolean getDebug() {
		return debug;
	}

	public void setDebug(Boolean debug) {
		this.debug = debug;
	}

	@Override
	public String toString() {
		return "Config [description=" + description + ", services=" + services + ", debug=" + debug + "]";
	}

}
