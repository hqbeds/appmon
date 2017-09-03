package com.boaglio.appmon.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boaglio.appmon.dto.Config;

@RestController
public class ConfigController {

	@GetMapping("/config")
	public Config config() {
		return Config.getInstance();
	}

}
