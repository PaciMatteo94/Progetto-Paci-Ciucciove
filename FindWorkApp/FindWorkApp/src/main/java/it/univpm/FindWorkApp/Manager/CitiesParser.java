package it.univpm.FindWorkApp.Manager;

import java.util.ArrayList;

import org.json.simple.JSONObject;

import it.univpm.FindWorkApp.Model.City;

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
		
		JSONObject jsObj = new JSONObject();

		for (int i = 0; i < cities.size(); i++) {

			jsObj.put("Location", cities.get(i).getLocation());

		}

		//return jsObj;
		
		return new JSONObject();
		
	}

}
