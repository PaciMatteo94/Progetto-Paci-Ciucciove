package it.univpm.FindWorkApp.Manager;

import java.util.ArrayList;

import org.json.simple.JSONObject;

import it.univpm.FindWorkApp.APICall.APICall;
import it.univpm.FindWorkApp.model.City;

public class Manager implements ManagerService {
	private APICall call = APICall.getInstance();
	private String[] location;
	private ArrayList<City> cities;
	private static Manager instance = null;

	private Manager() {
	}

	public static Manager getInstance() {
		if (instance == null) {
			instance = new Manager();
		}
		return instance;
	}

	@Override
	public JSONObject getCities(String[] location, String employment_type) {
		City city = null;
		this.location = location;
		cities = new ArrayList<City>();
		if (employment_type == null) {
			for (String name : location) {
				call.setAPICall(name);
				city = call.getData();
				if (city.getWork().size() != 0) {
					// chiamata al metodo che genera le stats della città passandogli la città
					cities.add(city);

				}
			}
			JSONObject test = new JSONObject();
			test.put("results", city.getWork());
			return test;// return JsonParser.getCitiesJO(cities);
		} else {
			for (String name : location) {

				call.setAPICall(name, employment_type);
				city = call.getData();
				if (city.getWork().size() != 0) {
					// chiamata al metodo che genera le stats della città passandogli la città
					cities.add(city);

				}
			}
			JSONObject test = new JSONObject();
			test.put("results", city.getWork());
			return test;// return JsonParser.getCitiesJO(cities);
		}
	}

	@Override
	public JSONObject getCities(String[] location, String employment_type, boolean remote) {
		City city = null;
		this.location = location;
		cities = new ArrayList<City>();
		if (employment_type != null) {
			for (String name : location) {
				call.setAPICall(name, employment_type, remote);
				city = call.getData();
				if (city.getWork().size() != 0) {
					// chiamata al metodo che genera le stats della città passandogli la città
					cities.add(city);

				}
			}
			JSONObject test = new JSONObject();
			test.put("results", city.getWork());
			return test;// return JsonParser.getCitiesJO(cities);
		} else {
			for (String name : location) {
				call.setAPICall(name, remote);
				city = call.getData();
				if (city.getWork().size() != 0) {
					// chiamata al metodo che genera le stats della città passandogli la città
					cities.add(city);

				}
			}
			JSONObject test = new JSONObject();
			test.put("results", city.getWork());
			return test;// return JsonParser.getCitiesJO(cities);

		}
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
	public JSONObject getStats(String[] location, String date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONObject getStats(String[] location, String date, boolean remote) {
		// TODO Auto-generated method stub
		return null;
	}

}
