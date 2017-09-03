package com.boaglio.appmon.util;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

public class JVMUtil {

	private long uptimeSeconds;
	private long uptimeMinutes;
	private long uptimeHours;
	private long uptimeDays;

	public long getUptimeSeconds() {
		return uptimeSeconds;
	}

	public long getUptimeMinutes() {
		return uptimeMinutes;
	}

	public long getUptimeHours() {
		return uptimeHours;
	}

	public long getUptimeDays() {
		return uptimeDays;
	}

	public void setUptime() {

		RuntimeMXBean rb = ManagementFactory.getRuntimeMXBean();

		long uptime = rb.getUptime();

		long secondsInMilli = 1000;
		long minutesInMilli = secondsInMilli * 60;
		long hoursInMilli = minutesInMilli * 60;
		long daysInMilli = hoursInMilli * 24;

		uptimeDays = uptime / daysInMilli;
		uptime = uptime % daysInMilli;

		uptimeHours = uptime / hoursInMilli;
		uptime = uptime % hoursInMilli;

		uptimeMinutes = uptime / minutesInMilli;
		uptime = uptime % minutesInMilli;

		uptimeSeconds = uptime / secondsInMilli;

	}

}
