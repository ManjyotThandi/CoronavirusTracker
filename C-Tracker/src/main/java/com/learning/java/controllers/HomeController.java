package com.learning.java.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.learning.java.models.LocationStats;
import com.learning.java.repository.LocationStatsRepository;
import com.learning.java.services.CVirusService;

// @Restcontroller would render JSON. but we want to render a UI. Use Controller
@Controller
public class HomeController {

	// We can autowire service class into here
	@Autowired
	CVirusService cVirusService;
	

	@Autowired 
	LocationStatsRepository locationStatsRepository;
	
	@GetMapping("/")
	public String home(Model model) {
		//List<LocationStats> allStats = cVirusService.getAllStats();
		List<LocationStats> allStats = locationStatsRepository.findAll();
		int totalNumberOfCases = allStats.stream().mapToInt(stat -> stat.getTotalNumber()).sum();
		model.addAttribute("cVirus", allStats);
		model.addAttribute("totalCases", totalNumberOfCases);
		return "home";
	}
}
