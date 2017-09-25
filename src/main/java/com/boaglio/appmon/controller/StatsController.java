package com.boaglio.appmon.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.boaglio.appmon.dto.FullStats;
import com.boaglio.appmon.dto.ServiceStats;
import com.boaglio.appmon.dto.ServiceStatsWithTime;
import com.boaglio.appmon.dto.Stats;
import com.boaglio.appmon.util.FileSystemUtil;

@RestController
public class StatsController {

	private FileSystemUtil fileSystemUtil;
	private FullStats fullStats;

	@Autowired
	public StatsController(FileSystemUtil fileSystemUtil,FullStats fullStats) {
		this.fileSystemUtil = fileSystemUtil;
		this.fullStats = fullStats;
	}

	@GetMapping("/stats/{service}")
	public List<ServiceStatsWithTime> statsByService(@PathVariable("service") String service) {

		List<ServiceStatsWithTime> list = new ArrayList<ServiceStatsWithTime>();
		for (Stats s : fullStats.getAllStats()) {
			for (ServiceStats ss : s.getServiceStats()) {
				if (ss.getService().getName().equalsIgnoreCase(service)) {
					list.add(new ServiceStatsWithTime(ss.getService(),ss.getStatus(),s.getDate()));
				}
			}
		}
		return list;
	}

	@GetMapping("/stats")
	public Stats stats() {
		Stats stats = fullStats.getLastStats();
		stats.setFileSystem(fileSystemUtil.refresh());
		return stats;
	}
}
