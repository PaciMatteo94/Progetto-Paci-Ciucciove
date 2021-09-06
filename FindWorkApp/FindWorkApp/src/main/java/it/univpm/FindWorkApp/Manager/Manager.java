package it.univpm.FindWorkApp.Manager;

import java.util.ArrayList;

import org.json.simple.JSONObject;

import it.univpm.FindWorkApp.APICall.APICall;

import it.univpm.FindWorkApp.Exception.NoCityException;
import it.univpm.FindWorkApp.Model.City;
import it.univpm.FindWorkApp.Stats.stats;


/**
 * <p>
 * La classe <b>Manager</b> implementa l'interfaccia <b>ManagerService</b> e ha
 * l'obbiettivo di gestire le chiamate all'api e restituire un oggetto JSON in
 * cui sono salvati tutti i dati richiesti.
 * 
 * @author Paci Matteo
 *
 */

public class Manager implements ManagerService {
	private APICall call = APICall.getInstance();
	private ArrayList<City> cities;
	/*
	 * la classe è implementata come singleton semplice. Si creerà una singola
	 * instanza che verrà poi usata dagli altri metodi per tutta l'esecuzione del
	 * programma.
	 */
	private static Manager instance = null;

	private Manager() {
	}

	public static Manager getInstance() {
		if (instance == null) {
			instance = new Manager();
		}
		return instance;
	}

	/**
	 * <p>
	 * Implementazione del metodo <b>getCities</b> dell'interfaccia
	 * <b>ManagerService</b>
	 */
	@Override
	public JSONObject getCities(String[] location, String employment_type) {
		City city = null;
		cities = new ArrayList<City>();
		if (employment_type == null) {
			for (String name : location) {
				call.setAPICall(name);
				city = call.getData();
				if (city.getWork().size() != 0) {
					 // chiamata al metodo che genera le stats della città passandogli la città
					cities.add(stats.statsCalculate(city));

				}
			}
			//JSONObject test = new JSONObject();
			//test.put("results", city.getWork());
			return CitiesParser.getJSON(cities);// return JsonParser.getCitiesJO(cities);
		} else {
			for (String name : location) {

				call.setAPICall(name, employment_type);
				city = call.getData();
				if (city.getWork().size() != 0) {
					stats.statsCalculate(city); // chiamata al metodo che genera le stats della città passandogli la città
					cities.add(city);

				}
			}
			//JSONObject test = new JSONObject();
			//test.put("results", city.getWork());
			return CitiesParser.getJSON(cities);// return JsonParser.getCitiesJO(cities);
		}
	}

	@Override
	public JSONObject getCities(String[] location, String employment_type, boolean remote) {
		City city = null;
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
			//JSONObject test = new JSONObject();
			//test.put("results", city.getWork());
			return CitiesParser.getJSON(cities);// return JsonParser.getCitiesJO(cities);
		} else {
			for (String name : location) {
				call.setAPICall(name, remote);
				city = call.getData();
				if (city.getWork().size() != 0) {
					// chiamata al metodo che genera le stats della città passandogli la città
					cities.add(city);

				}
			}
			//JSONObject test = new JSONObject();
			//test.put("results", city.getWork());
			return CitiesParser.getJSON(cities);// return JsonParser.getCitiesJO(cities);

		}
	}

	@Override
	public JSONObject getStats(String[] location, String date) {
		try {
			if (cities == null)
				throw new NoCityException();
		} catch (NoCityException e) {
			JSONObject noCity = new JSONObject();
			noCity.put("Errore 400", e.getMessage());
			noCity.put("Descrizione",
					"Prima di chiamare la rotta stats bisogna concludere con successo una richiesta /cities");
			return noCity;

		}

		if (location != null) {
			ArrayList<City> cityMatched = new ArrayList<City>(cities.size());
			for (City city : cities) {
				for (String name : location) {
					if (name == city.getLocation()) {
						cityMatched.add(city);
					}
				}
			}
			if (date != null) {
				return new JSONObject(); // return jsonParser.getStats(cityMatched,date);
			}
			return StatParser.getJSON(cityMatched); // return jsonParser.getStats(cityMatched);
		}
		if (date != null) {
			return new JSONObject();// return JsonParser.getStats(cities, date);
		}

		return StatParser.getJSON(cities);// return JsonParser.getStats(cities);

	}

	@Override
	public JSONObject getStats(String[] location, String date, boolean remote) {
		try {
			if (cities == null)
				throw new NoCityException();
		} catch (NoCityException e) {
			JSONObject noCity = new JSONObject();
			noCity.put("Errore 400", e.getMessage());
			noCity.put("Descrizione",
					"Prima di chiamare la rotta stats bisogna concludere con successo una richiesta /cities");
			return noCity;

		}
		if (location != null) {
			ArrayList<City> cityMatched = new ArrayList<City>(cities.size());
			for (City city : cities) {
				for (String name : location) {
					if (name == city.getLocation()) {
						cityMatched.add(city);
					}
				}
			}
			if (date != null) {
				return new JSONObject(); // return jsonParser.getStats(cityMatched,date,remote);
			}
			return new JSONObject(); // return jsonParser.getStats(cityMatched,remote);
		}
		if (date != null) {
			return new JSONObject();// return JsonParser.getStats(cities, date, remote);
		}
		return new JSONObject();// return JsonParser.getStats(cities, remote);
	}

}
