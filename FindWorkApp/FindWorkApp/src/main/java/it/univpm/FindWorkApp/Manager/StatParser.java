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
		//for(int i=0;i<cities.size();i++) {
			for(City city : cities) {
			Map<String,Object> json = new LinkedHashMap();
			/*json.put("Location",cities.get(i).getLocation());
			json.put("Count",cities.get(i).getCount());
			json.put("FullTimeAmount",cities.get(i).getFullTimeAmount());
			json.put("PartTimeAmount",cities.get(i).getPartTimeAmount());
			json.put("FullTimePerc",cities.get(i).getFullTimePerc());
			json.put("PartTimePerc",cities.get(i).getPartTimePerc());*/
			json.put("Location",city.getLocation());
			json.put("Count",city.getCount());
			json.put("FullTimeAmount",city.getFullTimeAmount());
			json.put("PartTimeAmount",city.getPartTimeAmount());
			json.put("FullTimePerc",city.getFullTimePerc());
			json.put("PartTimePerc",city.getPartTimePerc());
			//json.put("",cities.get(i).get...()); Statistiche per i linguaggi
			
			js.add(json);
		}
		JSON.put("CityEntered", cities.size());
		JSON.put("Results", js);
           return JSON;
	}
}
