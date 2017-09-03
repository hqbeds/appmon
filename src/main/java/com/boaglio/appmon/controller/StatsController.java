package com.boaglio.appmon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boaglio.appmon.dto.FullStats;
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

	@GetMapping("/stats-online")
	public Stats statsOnline() {
		Stats stats = new Stats();
		stats.setFileSystem(fileSystemUtil.refresh());
		return stats;
	}

	@GetMapping("/stats-full")
	public FullStats statsFull() {
		return fullStats;
	}

	@GetMapping("/stats")
	public Stats stats() {
		return fullStats.getLastStats();
	}
}
