package com.boaglio.appmon.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Service {

	private Integer port;
	private String name;
	private Boolean status;
	private String testCommand;
	private String testExpectedResponse;

	public Service() {}

	public Service(String name,Integer port,Boolean status,String testCommand,String testExpectedResponse) {
		super();
		this.name = name;
		this.port = port;
		this.status = status;
		this.testCommand = testCommand;
		this.testExpectedResponse = testExpectedResponse;
	}

	public Service(Integer port,Boolean status) {
		super();
		this.port = port;
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getTestCommand() {
		return testCommand;
	}

	public void setTestCommand(String testCommand) {
		this.testCommand = testCommand;
	}

	public String getTestExpectedResponse() {
		return testExpectedResponse;
	}

	public void setTestExpectedResponse(String testExpectedResponse) {
		this.testExpectedResponse = testExpectedResponse;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ( (port == null) ? 0 : port.hashCode());
		result = prime * result + ( (status == null) ? 0 : status.hashCode());
		result = prime * result + ( (testCommand == null) ? 0 : testCommand.hashCode());
		result = prime * result + ( (testExpectedResponse == null) ? 0 : testExpectedResponse.hashCode());
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
		Service other = (Service) obj;
		if (port == null) {
			if (other.port != null)
				return false;
		} else if (!port.equals(other.port))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (testCommand == null) {
			if (other.testCommand != null)
				return false;
		} else if (!testCommand.equals(other.testCommand))
			return false;
		if (testExpectedResponse == null) {
			if (other.testExpectedResponse != null)
				return false;
		} else if (!testExpectedResponse.equals(other.testExpectedResponse))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Service [port=" + port + ", status=" + status + ", testCommand=" + testCommand + ", testExpectedResponse=" + testExpectedResponse + "]";
	}

}
