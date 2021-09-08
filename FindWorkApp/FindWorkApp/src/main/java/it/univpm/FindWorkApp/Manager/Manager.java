package it.univpm.FindWorkApp.Manager;

import java.util.ArrayList;

import org.json.simple.JSONObject;

import it.univpm.FindWorkApp.APICall.APICall;

import it.univpm.FindWorkApp.Exception.NoCityException;
import it.univpm.FindWorkApp.Model.City;
import it.univpm.FindWorkApp.Stats.Stats;

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

	@Override
	public JSONObject getCities(String[] location, String employment_type, Boolean remote) {
		City city = null;
		cities = new ArrayList<City>();
		if (employment_type != null) {
			for (String name : location) {
				call.setAPICall(name, employment_type, remote);
				city = call.getData();
				if (city.getWork().size() != 0) {
					Stats.statsCalculate(city);
					cities.add(city);

				}
			}
			return CitiesParser.getJSON(cities);

		} else {
			for (String name : location) {
				call.setAPICall(name, remote);
				city = call.getData();
				if (city.getWork().size() != 0) {
					Stats.statsCalculate(city);
					cities.add(city);

				}
			}
			return CitiesParser.getJSON(cities);

		}
	}

	@Override
	public JSONObject getStats(String[] location, String date, Boolean remote) {
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
					if (name.equals(city.getLocation())) {
						String temp = city.getLocation();
						cityMatched.add(city);
					}
				}
			}
			if (date == null & remote == null) {
				return StatParser.getJSON(cityMatched);
			} else
				return StatParser.getJSON(Stats.statsFiltered(cityMatched, date, remote));
		} else if (date == null & remote == null) {
			return StatParser.getJSON(cities);
		}
		return StatParser.getJSON(Stats.statsFiltered(cities, date, remote));
	}
}
