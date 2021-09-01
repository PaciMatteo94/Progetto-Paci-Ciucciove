package it.univpm.FindWorkApp.Manager;

import java.util.ArrayList;

import org.json.simple.JSONObject;

import it.univpm.FindWorkApp.model.City;

public class CitiesParser {
	private static CitiesParser instance=null;
	private CitiesParser() {}
	public static CitiesParser getInstance() {
		if(instance==null) {
			instance = new CitiesParser();
		}
		return instance;
	}
	public JSONObject getJSON(ArrayList<City> cities) {
		
		
		
		return new JSONObject();
		
	}

}
