package it.univpm.FindWorkApp.controller;

import org.springframework.web.bind.annotation.RestController;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Arrays;

import it.univpm.FindWorkApp.Manager.Manager;
import it.univpm.FindWorkApp.model.Pref;

/**
 * 
 * <p>
 *La classe <b>APICallController</b> gestisce le richieste di dati da parte dell'utente
 *tramite l'inserimento, o meno, di parametri di interesse.
 *
 *</p>
 *
 * @author Paci Matteo
 * @author Ciucciovè Leonardo
 *
 */
@RestController
public class APICallController {
	private Manager manager = Manager.getInstance();
	/**
	 * Questo metodo restituisce cinque città predefinite come suggerimento di ricerca per l'utente.
	 * 
	 * @return <code>Pref</code>
	 * 
	 */

	
	@GetMapping("/preferences")
	public Pref preferences(@RequestParam(name="nome", defaultValue="none") String nome) {
		String[] cittas= {"London","Berlin","New York","Amsterdam","Paris"};
		return new Pref(cittas);
	}
		




	/**
	 * Questo metodo restituisce i lavori presenti nelle città richieste.
	 * Se non vengono inserite locazioni verrà visualizzato un messaggio di errore mentre se vengono inserite più di 5 locazioni, verrano visualizzati
	 * solo i lavori delle prime 5 città inserite mentre le altre non saranno prese in considerazione.
	 * 
	 *
     *
	 * @param location  indica il nome di una o più città in cui il cliente vuole cercare
	 * 
	 * @return <code>CitiesJO</code>
	 * 
	 */
	@GetMapping("/cities")
	public JSONObject city(
			@RequestParam(name="location") String location) {
		//qui bisogna mettere un messaggio di errore quando non inserisce il param location
		if (location=="") {
			//qui bisogna fare un throw per dire che non sono state inserite città
			JSONObject test = new JSONObject();
			test.put("errore", "nessuna location inserita");
			return test;
		}
			String[] cityArray = location.split(", |&|,");
			String[] cities;
			// Prendo la parte di array che mi interessa copiando i valori fino all termine
			// dello stesso
			if (cityArray.length <= 5) {
				cities = Arrays.copyOfRange(cityArray, 0, cityArray.length);
			} else {
				cities = Arrays.copyOfRange(cityArray, 0, 5);
			}
			return manager.getCities(cities, null); //return manager.getCities(sCityArray)
		
	}
	/**
	 * Questo metodo mi permette di filtrare i lavori per solo la tipologia di lavoro full time o part time.
	 * Il parametro non è obbligatorio, se non si inserisce il funzionamento del metodo è identico a quello precedente e riporterà lo stesso risultato.
	 * 
	 * @param location  indica il nome di una o più città di ricerca
	 * @param employment_type  indica se il lavoro è full time o part time/contratto
	 * 
	 * 
	 * @return <code>CitiesJO</code>
	 * 
	 */
	@GetMapping("/cities/filter")
	public JSONObject cityFilter(@RequestParam(name = "location") String location,
			@RequestParam(name = "employment_type", required=false) String employment_type) {
		if (location == "") {
			// qui ci va un throw che indica che non sono state inserite città su cui fare
			// la ricerca;
			JSONObject test = new JSONObject();
			test.put("errore", "nessuna location inserita");
			return test;
		}
		String[] cityArray = location.split(", |&|,");
		String[] cities;

		if (cityArray.length < 5) {
			cities = Arrays.copyOfRange(cityArray, 0, cityArray.length);
		} else {
			cities = Arrays.copyOfRange(cityArray, 0, 5);
		}
		if (employment_type != null) {
			if (employment_type.contains("full time") || employment_type.contains("part time")) {
				return manager.getCities(cities, employment_type); //return manager.getCities(cities,employment_type)
			} else {
				// ritorna un errore dove dice che si è inserito un valore non accettato dal
				// paramentro
				// con anche una descrizione di cosa bisogna inserire
				JSONObject test = new JSONObject();
				test.put("errore", "inserito un valore non supportato da employment_type");
				return test;
			}
		}

		return manager.getCities(cities,employment_type); // return manager.getCities(sCityArray2);

	}
	
	/**
	 * Questo metodo restituisce i lavori presenti nelle città inserite per 2 possibili parametri di cui uno obbligatorio, remote.
	 * è possibile aggiungere anche il parametro per la tipologia di lavoro per avere una ricerca completa tramite filtri. 
	 * 
	 * @param location indica le città in cui cercare i lavori
	 * @param employment_type indica la tipologia di lavoro full time o part time
	 * @param remote indica la tipologia di lavoro remoto o non
	 * 
	 * @return
	 */
	@GetMapping("/cities/filter/remote")
	public JSONObject cityFilterRemote(@RequestParam(name = "location") String location,
			@RequestParam(name = "emplyment_type", required = false) String employment_type,
			@RequestParam(name="remote",  required = false) boolean remote) {
		if (location == "") {
			// qui ci va un throw che indica che non sono state inserite città su cui fare
			// la ricerca;
			JSONObject test = new JSONObject();
			test.put("errore", "nessuna location inserita");
			return test;
		}
		String[] cityArray = location.split(", |&|,");
		String[] cities;

		if (cityArray.length < 5) {
			cities = Arrays.copyOfRange(cityArray, 0, cityArray.length);
		} else {
			cities = Arrays.copyOfRange(cityArray, 0, 5);
		}
		if (employment_type != null) {
			if (employment_type.contains("full time") || employment_type.contains("part time")) {
				return manager.getCities(cities, remote, employment_type); //return manager.getCities(cities,remote,employment_type)
			} else {
				// ritorna un errore dove dice che si è inserito un valore non accettato dal
				// paramentro
				// con anche una descrizione di cosa bisogna inserire
				JSONObject test = new JSONObject();
				test.put("errore", "inserito un valore non supportato da employment_type");
				return test;
			}
		}

		return manager.getCities(cities, remote); // return manager.getCities(sCityArray2,remote);
	}
}
