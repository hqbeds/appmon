package com.boaglio.appmon.dto;

import com.boaglio.appmon.domain.Service;
import com.boaglio.appmon.type.ServiceStatus;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ServiceStats {

	private Service service;

	private ServiceStatus status;

	public ServiceStats() {}

	public ServiceStats(Service service,ServiceStatus status) {
		super();
		this.status = status;
		this.service = service;
	}

	public ServiceStatus getStatus() {
		return status;
	}

	public void setStatus(ServiceStatus status) {
		this.status = status;
	}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	@Override
	public String toString() {
		return "ServiceStats [service=" + service + ", status=" + status + "]";
	}

}
