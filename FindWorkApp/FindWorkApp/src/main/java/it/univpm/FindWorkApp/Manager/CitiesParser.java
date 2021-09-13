package it.univpm.FindWorkApp.Manager;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import it.univpm.FindWorkApp.Model.City;

/**
 * <p>
 * La classe CitiesParser serve per restituire un risultato in forma JSON ordinata
 * alla chiamata della rotta '/cities' con o senza filtri prendendo i dati passati dal Manager
 * </p>
 * 
 * @author Paci Matteo
 * @author Ciucciovè Leonardo
 *
 */


public class CitiesParser {
	private static CitiesParser instance=null;
	private CitiesParser() {}
	public static CitiesParser getInstance() {
		if(instance==null) {
			instance = new CitiesParser();
		}
		return instance;
	}
	/**
	 * <p>
	 * Questo metodo organizza tutte le tuple corrispondenti ad ogni città inserita, 
	 * salvandole dentro un JSONArray. Restituisce poi il conteggio delle città inserite e il conteggio
	 * dei lavori trovati per città
	 * </p>
	 * @param cities è l'ArrayList di tipo City contenente tutti i risultati delle ricerche e dei filtri applicati
	 * @return <code>JSON<code>
	 */
	public static JSONObject getJSON(ArrayList<City> cities) {
		JSONObject JSON = new JSONObject();
		JSONArray js = new JSONArray();
		for(int i=0;i<cities.size();i++) {
			Map<String,Object> json = new LinkedHashMap();
			json.put("Location",cities.get(i).getLocation());
			json.put("Count",cities.get(i).getCount());
			json.put("Works",cities.get(i).getWork());
			
			js.add(json);
		
		}
			JSON.put("CityCount", cities.size());
			JSON.put("Results", js);
	           return JSON;
		
	}

}
