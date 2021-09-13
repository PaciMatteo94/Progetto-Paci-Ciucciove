package it.univpm.FindWorkApp.Parser;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import it.univpm.FindWorkApp.Model.City;

public class FindWorkParser {
	private static FindWorkParser instance = null;

	private FindWorkParser() {
	}

	public static FindWorkParser getInstance() {
		if (instance == null) {
			instance = new FindWorkParser();
		}
		return instance;
	}

	public JSONObject getJSON(ArrayList<City> cities) {
		JSONObject JSON = new JSONObject();
		JSONArray js = new JSONArray();
		for (City obj : cities) {
			Map<String, Object> json = new LinkedHashMap<String, Object>();
			json.put("Location", obj.getLocation());
			json.put("Count", obj.getCount());
			json.put("Works", obj.getWork());

			js.add(json);

		}
		JSON.put("CityCount", cities.size());
		JSON.put("Results", js);
		return JSON;

	}

	public JSONObject getJSONStats(ArrayList<City> cities) {
		JSONObject JSON = new JSONObject();
		JSONArray js = new JSONArray();
		for (City city : cities) {
			Map<String, Object> json = new LinkedHashMap();
			json.put("Location", city.getLocation());
			json.put("Count", city.getCount());
			json.put("stats", city.getCityStats());
			js.add(json);
		}
		JSON.put("CityEntered", cities.size());
		JSON.put("Results", js);
		return JSON;
	}
}
