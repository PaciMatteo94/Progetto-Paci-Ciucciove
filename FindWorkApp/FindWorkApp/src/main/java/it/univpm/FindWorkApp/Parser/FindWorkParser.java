package it.univpm.FindWorkApp.Parser;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import it.univpm.FindWorkApp.Model.City;

/**
 * La classe <b>FindWorkParser</b> implementa l'intefaccia
 * <b>FindWorkParserService</b>.
 * 
 * @author Paci Matteo
 * @author Ciucciovè Leonardo
 *
 */
public class FindWorkParser implements FindWorkParserService {
	/**
	 * <p>
	 * la classe è implementata come singleton semplice. Si creerà una singola
	 * instanza che verrà poi usata dagli altri metodi per tutta l'esecuzione del
	 * programma.
	 */
	private static FindWorkParser instance = null;

	private FindWorkParser() {
	}

	public static FindWorkParser getInstance() {
		if (instance == null) {
			instance = new FindWorkParser();
		}
		return instance;
	}

	/**
	 * Il metodo <b>getCitiesObj</b> forma un oggetto dove sono presenti tutti i
	 * lavori, suddivisi per città, che soddisfano le richieste dell'utente.
	 * 
	 * @param cities lista di oggetti di tipo città dove sono presenti i lavori che
	 *               soddisfano le richieste dell'utente.
	 * @return <code>Object</code>
	 */
	public Object getCitiesObj(ArrayList<City> cities) {
		Map<String, Object> JSON = new LinkedHashMap<String, Object>();
		ArrayList<Object> js = new ArrayList<Object>();
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

	/**
	 * Il metodo <b>getStatsObj</b> forma un oggetto dove sono presenti le stastiche
	 * dei lavori presenti nelle città di ricerca.
	 * 
	 * @param cities lista di oggetti di tipo città dove sono salvate le statistiche
	 *               di ogni città.
	 * @return <code>Object</code>
	 */

	public Object getStatsObj(ArrayList<City> cities) {
		Map<String, Object> JSON = new LinkedHashMap<String, Object>();
		ArrayList<Object> js = new ArrayList<Object>();
		for (City city : cities) {
			Map<String, Object> json = new LinkedHashMap<String, Object>();
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
