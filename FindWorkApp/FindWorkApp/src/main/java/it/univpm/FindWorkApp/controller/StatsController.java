package it.univpm.FindWorkApp.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.univpm.FindWorkApp.model.Stats;

@RestController
public class StatsController {
	@GetMapping("/stats")
	public Stats Stat(@RequestParam(name="location", defaultValue="none") String location,
			@RequestParam(name="search", defaultValue="none") String search,
			@RequestParam(value="remote", required=false) boolean remote,
			@RequestParam(value="htime", required=false) boolean htime) {
		
		return new Stats("London","java",true,true);
	}
	
	@GetMapping("/stats/filter")
	public Stats StatFilter(@RequestParam(name="location", defaultValue="none") String location,
			@RequestParam(name="search", defaultValue="none") String search,
			@RequestParam(value="remote", required=false) boolean remote,
			@RequestParam(value="htime", required=false) boolean htime) {
		
		return new Stats("London","java",true,true);
	}
	
}
