package it.univpm.FindWorkApp.Manager;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import it.univpm.FindWorkApp.Model.City;

public class StatParser {
	public static JSONObject getJSON(ArrayList<City> cities) {
		JSONObject JSON = new JSONObject();
		JSONArray js = new JSONArray();
			for(City city : cities) {
			Map<String,Object> json = new LinkedHashMap();
			json.put("Location",city.getLocation());
			json.put("Count",city.getCount());
			json.put("empNullAmount", city.getEmpNullAmount()); //provvisorio
			json.put("FullTimeAmount",city.getFullTimeAmount());
			json.put("PartTimeAmount",city.getPartTimeAmount());
			json.put("FullTimePerc",city.getFullTimePerc()+"%");
			json.put("PartTimePerc",city.getPartTimePerc()+"%");
			json.put("LanguagesStats (in %)", city.getLenguageStats());
			js.add(json);
		}
		JSON.put("CityEntered", cities.size());
		JSON.put("Results", js);
           return JSON;
	}
}
