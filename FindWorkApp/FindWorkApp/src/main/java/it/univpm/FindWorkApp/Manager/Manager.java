package it.univpm.FindWorkApp.Manager;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import it.univpm.FindWorkApp.APICall.APICall;
import it.univpm.FindWorkApp.model.City;

public class Manager implements ManagerService{
	private APICall call= APICall.getInstance();
	private String[] location;
	private ArrayList<City> cities;
	private static Manager instance =null;
	private Manager() {}
	public static Manager getInstance() {
		if(instance ==null) {
			instance = new Manager();
		}
		return instance;
	}

	@Override
	public JSONObject getCities(String[] location) {
		City city;
		JSONArray results;
		this.location = location;
		for (String name : location) {
			call.setAPICall(name);
			results = call.getData();
			if (results.size() != 0) {
				city = new City(name);
				city.setWork(results);
				// chiamata al metodo che genera le stats della città passandogli la città
				cities.add(city);
			}
		}

		return null; // return JsonParser.getCitiesJO(cities);
	}

	@Override
	public JSONObject getCities(String[] location, String employment_type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONObject getCities(String[] location, boolean remote) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONObject getCities(String[] location, boolean remote, String employment_type) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public JSONObject getStats() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public JSONObject getStats(String[] location) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public JSONObject getStats(String date) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public JSONObject getStats(boolean remote) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public JSONObject getStats(String[] location, String date) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public JSONObject getStats(String[] location, boolean remote) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public JSONObject getStats(boolean remote, String date) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public JSONObject getStats(String[] location, boolean remote, String date) {
		// TODO Auto-generated method stub
		return null;
	}

}
