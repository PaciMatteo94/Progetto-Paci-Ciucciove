package it.univpm.FindWorkApp.Manager;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import it.univpm.FindWorkApp.Model.City;
import it.univpm.FindWorkApp.Model.StatsProgrammingLenguage;

/**
 * <p>
 * La classe StatParser serve per restituire un risultato in forma JSON ordinata
 * alla chiamata della rotta '/stats' con o senza filtri prendendo i dati passati dal Manager
 * </p>
 * 
 * @author Paci Matteo
 * @author Ciucciovè Leonardo
 *
 */


/**
 * <p>
 * Questo metodo organizza per ogni città inserita, le statistiche della stessa 
 * salvandole dentro un JSONArray. 
 * Le stastistiche restituite sono:
 * Quanti lavori sono full-time e la loro percentuale
 * Quanti lavori sono part-time e la loro percentuale
 * Quanti non lo specificano e la loro percentuale
 * La percentuale della presenza dei linguaggi di programmazione più comuni (ognuno sul totoale)
 * </p>
 * 
 * @param cities è l'ArrayList di tipo City contenente tutti i risultati delle ricerche e dei filtri applicati
 * @param s contiene i dati relativi alle statistiche dei linguaggi di programmazione 
 * @return <code>JSON<code>
 */

public class StatParser {
	public static JSONObject getJSON(ArrayList<City> cities) {
		JSONObject JSON = new JSONObject();
		JSONArray js = new JSONArray();
		StatsProgrammingLenguage s = new StatsProgrammingLenguage();
		
			for(City city : cities) {
			Map<String,Object> json = new LinkedHashMap();
			Map<String,Object> json2 = new LinkedHashMap();
			Map<String,Object> json3 = new LinkedHashMap();
			Map<String,Object> json4 = new LinkedHashMap();
			json.put("Location",city.getLocation());
			json.put("Count",city.getCount());
			json2.put("FullTimeAmount",city.getFullTimeAmount());
			json2.put("PartTimeAmount",city.getPartTimeAmount());
			json2.put("FullTimePerc",city.getFullTimePerc()+"%");
			json2.put("PartTimePerc",city.getPartTimePerc()+"%");
			json2.put("NotSpecifiedAmount", city.getNotSpecifiedAmount());
			json2.put("NotSpecifiedPerc", city.getNotSpecifiedPerc()+"%");
			json3.put("WorkTime", json2);
			
			s=city.getLenguageStats();
			json4.put("percPython",s.getPercPython()+"%");
			json4.put("percPhp",s.getPercPhp()+"%");
			json4.put("percSpring",s.getPercSpring()+"%");
			json4.put("percPyton",s.getPercTypescript()+"%");
			json4.put("percSql",s.getPercSql()+"%");
			json3.put("LanguagesStats", json4);
			json.put("CityStats",json3);
			
			js.add(json);
		}
		JSON.put("CityEntered", cities.size());
		JSON.put("Results", js);
           return JSON;
	}
}
