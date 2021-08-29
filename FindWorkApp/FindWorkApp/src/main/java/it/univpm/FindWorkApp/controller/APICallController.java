package it.univpm.FindWorkApp.controller;

import org.springframework.web.bind.annotation.RestController;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.univpm.FindWorkApp.model.Pref;

@RestController
public class APICallController {
	//APIObjectConverter può essere un singleton per cui posso fare la dichirazione dell'oggeto qui in modo da usare sempre quell'unico oggetto per 
	//convertire il json dell'api e salvarlo in un array.
	
	@GetMapping("/preferences")
	public Pref City(@RequestParam(name="nome", defaultValue="none") String nome) {
		String[] cittas= {"London","Berlin","New York","Amsterdam","Paris"};
		
		return new Pref(cittas);
	}
	
	@GetMapping("/cities")
	public JSONObject Cities(@RequestParam(name="location", defaultValue="none") String location){
		//ArrayList<String> cities = new ArrayList<String>();
		//qui chiamiamo il metodo passandogli l'array cities e ci restituirà il file json che andiamo a ritornare
		//il json deve contenere il nome della città, il numero dei lavori trovari e un array con oggetti json che descrive le informazioni dei lavori
		//return json;
		return new JSONObject();
	}
	
	@GetMapping("/cities/filter")
	public JSONObject Cityilter(@RequestParam(name="location", defaultValue="none") String location,
			@RequestParam(name="search", defaultValue="none") String search,
			@RequestParam(value="remote", required=false) boolean remote,
			@RequestParam(value="htime", required=false) boolean htime) {
		//ArrayList<String> cities = new ArrayList<String>();
		//boolean remote
		//full time o part time
		//qui chiamiamo il metodo passandogli l'array cities, remote e il fulltime/parttime e ci restituirà il file json che andiamo a ritornare
		//il json deve contenere il nome della città, il numero dei lavori trovari e un array con oggetti json che descrive le informazioni dei lavori
		//return json;
		return new JSONObject();
		
	}
}
