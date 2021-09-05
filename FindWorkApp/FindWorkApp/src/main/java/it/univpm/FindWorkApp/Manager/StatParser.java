package it.univpm.FindWorkApp.Manager;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import it.univpm.FindWorkApp.model.City;

public class StatParser {
	public static JSONObject getJSON(ArrayList<City> cities) {
		JSONObject JSON = new JSONObject();
		JSONArray js = new JSONArray();
		for(int i=0;i<cities.size();i++) {
			Map json = new LinkedHashMap();
			json.put("Location",cities.get(i).getLocation());
			json.put("Count",cities.get(i).getCount());
			json.put("FullTimeAmount",cities.get(i).getFullTimeAmount());
			json.put("PartTimeAmount",cities.get(i).getFullTimeAmount());
			json.put("FullTimePerc",cities.get(i).getFullTimeAmount());
			json.put("PartTimePerc",cities.get(i).getFullTimeAmount());
			//json.put("",cities.get(i).get...()); Statistiche per i linguaggi
			
			js.add(json);
		}
		JSON.put("CityEntered", cities.size());
		JSON.put("Results", js);
           return JSON;
	}
}
