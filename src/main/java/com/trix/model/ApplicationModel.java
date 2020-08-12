package com.trix.model;

import java.time.Duration;
import java.time.LocalTime;

public class ApplicationModel{
	
	private String applicationName;
	private Long timeSpendInSeconds;
	private Long kontoId;
	
	public String getApplicationName() {
		return applicationName;
	}
	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}
	public Long getTimeSpendInSeconds() {
		return timeSpendInSeconds;
	}
	public void setTimeSpendInSeconds(Long timeSpendInSeconds) {
		this.timeSpendInSeconds = timeSpendInSeconds;
	}
	public Long getKontoId() {
		return kontoId;
	}
	public void setKontoId(Long kontoId) {
		this.kontoId = kontoId;
	}
	@Override
	public String toString() {
		return "ApplicationModel [applicationName=" + applicationName + ", timeSpendInSeconds=" + timeSpendInSeconds
				+ ", kontoId=" + kontoId + "]";
	}
	
	

}
