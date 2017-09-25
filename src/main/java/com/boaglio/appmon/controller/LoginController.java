package com.boaglio.appmon.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.boaglio.appmon.dto.Config;
import com.boaglio.appmon.dto.FullStats;
import com.boaglio.appmon.dto.ServiceStats;
import com.boaglio.appmon.dto.ServiceStatsWithTime;
import com.boaglio.appmon.dto.Stats;

@Controller
public class LoginController {

	@Autowired
	public LoginController(FullStats fullStats) {
		this.fullStats = fullStats;
	}

	private FullStats fullStats;

	@GetMapping("/login")
	public String login() {
		return "/login";
	}

	@GetMapping("/logout")
	public String logoutPage(HttpServletRequest request,HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request,response,auth);
		}
		return "redirect:/home";
	}

	@GetMapping("/")
	public ModelAndView homeRoot() {
		return new ModelAndView("/home","description",Config.getInstance().getDescription());
	}

	@GetMapping("/home")
	public ModelAndView home() {
		return new ModelAndView("/home","description",Config.getInstance().getDescription());
	}

	@GetMapping("/detail/{service}")
	public ModelAndView detail(@PathVariable("service") String service) {

		List<ServiceStatsWithTime> list = new ArrayList<ServiceStatsWithTime>();
		for (Stats s : fullStats.getAllStats()) {
			for (ServiceStats ss : s.getServiceStats()) {
				if (ss.getService().getName().equalsIgnoreCase(service)) {
					list.add(new ServiceStatsWithTime(ss.getService(),ss.getStatus(),s.getDate()));
				}
			}
		}
		return new ModelAndView("/detail","list",list);

	}

	@GetMapping("/403")
	public String error403() {
		return "/error/403";
	}

}
