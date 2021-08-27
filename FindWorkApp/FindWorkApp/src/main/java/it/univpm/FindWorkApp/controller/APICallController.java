package it.univpm.FindWorkApp.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.univpm.FindWorkApp.model.Pref;
import it.univpm.FindWorkApp.model.Cities;

@RestController
public class APICallController {
	@GetMapping("/preferences")
	public Pref City(@RequestParam(name="nome", defaultValue="none") String nome) {
		String[] cittas= {"London","Berlin","Rome","Moscow","Paris"};
		
		return new Pref(cittas);
	}
	
	@GetMapping("/cities")
	public Cities City(@RequestParam(name="location", defaultValue="none") String location,
			@RequestParam(name="search", defaultValue="none") String search,
			@RequestParam(value="remote", required=false) boolean remote,
			@RequestParam(value="htime", required=false) boolean htime) {
		
		return new Cities(location,search,remote,htime);
	}
	
	@GetMapping("/cities/filter")
	public Cities Cityilter(@RequestParam(name="location", defaultValue="none") String location,
			@RequestParam(name="search", defaultValue="none") String search,
			@RequestParam(value="remote", required=false) boolean remote,
			@RequestParam(value="htime", required=false) boolean htime) {
		
		return new Cities(location,search,remote,htime);
	}
}
