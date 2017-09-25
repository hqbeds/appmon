package com.boaglio.appmon.dto;

import java.time.LocalDateTime;

import com.boaglio.appmon.domain.Service;
import com.boaglio.appmon.type.ServiceStatus;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ServiceStatsWithTime {

	private Service service;

	private ServiceStatus status;

	private LocalDateTime date;

	public ServiceStatsWithTime() {}

	public ServiceStatsWithTime(Service service,ServiceStatus status) {
		super();
		this.status = status;
		this.service = service;
		this.date = LocalDateTime.now();
	}

	public ServiceStatsWithTime(Service service,ServiceStatus status,LocalDateTime date) {
		super();
		this.status = status;
		this.service = service;
		this.date = date;
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

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "ServiceStatsWithTime [service=" + service + ", status=" + status + ", date=" + date + "]";
	}

}
