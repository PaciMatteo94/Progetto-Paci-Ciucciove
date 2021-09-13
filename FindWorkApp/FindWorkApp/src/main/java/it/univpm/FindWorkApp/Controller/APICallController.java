package it.univpm.FindWorkApp.Controller;

import org.springframework.web.bind.annotation.RestController;
import org.json.simple.JSONObject;
import java.util.Arrays;
import java.util.HashMap;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import it.univpm.FindWorkApp.Exception.NoLocationException;
import it.univpm.FindWorkApp.Exception.UnsupportedValueException;

import it.univpm.FindWorkApp.Manager.Manager;
import it.univpm.FindWorkApp.Model.Preference;

/**
 * <p>
 * La classe <b>APICallController</b> gestisce le richieste di dati da parte
 * dell'utente tramite l'inserimento, o meno, di parametri di interesse.
 *
 * @author Paci Matteo
 * @author Ciucciovè Leonardo
 */
@RestController
public class APICallController {
	private static enum Remote {
		yes, no
	}

	private Manager manager = Manager.getInstance();

	/**
	 * <p>
	 * Questo metodo restituisce cinque città predefinite come suggerimento di
	 * ricerca per l'utente.
	 *
	 * @return <code>JSONObject</code>
	 */

	@GetMapping("/preferences")
	public JSONObject preferences(@RequestParam(name = "nome", defaultValue = "none") String nome) {
		HashMap<String,Object> map = new HashMap<String,Object>();
		Preference pref = new Preference();
		map.put("Città di preferenza", pref.getPreference());
		JSONObject obj = new JSONObject(map);
		return obj;
	}

	// @PostMapping("/preferences")
	@RequestMapping(value = "/preferences", method = RequestMethod.POST)
	public @ResponseBody JSONObject suggested(@RequestBody(required = false) String body) {
		/*
		 * try { if(body==null) throw new EmptyBodyException(); } catch
		 * (EmptyBodyException e) { JSONObject noBody = new JSONObject();
		 * noBody.put("Errore 400", e.getMessage()); return noBody; }
		 */
		String[] cityArray = body.split(", |&|,");
		String[] cities;

		if (cityArray.length < 5) {
			cities = Arrays.copyOfRange(cityArray, 0, cityArray.length);
		} else {
			cities = Arrays.copyOfRange(cityArray, 0, 5);
		}
		JSONObject js = new JSONObject();
		js.put("Città inserite", cities);
		return js;
	}

	/**
	 * <p>
	 * Questo metodo permette di ricercare i lavori presenti in una o più città con
	 * la possibilità di inserire dei filtri di ricerca.
	 *
	 * @param location        indica il nome di una o più città di ricerca.
	 * @param employment_type indica se il lavoro è full time o part time/contratto.
	 * @return <code>JSONObject</code> oggetto di tipo JSON che contiene tutte le
	 *         informazioni richieste dall'utente.
	 */
	@GetMapping("/cities")
	public JSONObject cityFilter(@RequestParam(name = "location", required = false) String location,
			@RequestParam(name = "employment_type", required = false) String employment_type,
			@RequestParam(name = "remote", required = false) Remote remote)
			throws NoLocationException, UnsupportedValueException {
		String[] cities = null;
		if (location != null) {
			if (location == "")
				throw new NoLocationException();

			String[] cityArray = location.split(", |&|,");

			if (cityArray.length < 5) {
				cities = Arrays.copyOfRange(cityArray, 0, cityArray.length);
			} else {
				cities = Arrays.copyOfRange(cityArray, 0, 5);
			}
		} else {
			Preference pref = new Preference();
			cities = pref.getPreference();
		}
		if (employment_type != null) {
			if (employment_type.contains("full time") || employment_type.contains("contract")) {
				if (remote != null) {
					switch (remote) {
					case yes:
						return manager.getCities(cities, employment_type, true);
					case no:
						return manager.getCities(cities, employment_type, false);
					}
				}
				return manager.getCities(cities, employment_type, null);

			} else {
				throw new UnsupportedValueException();

			}
		} else {
			if (remote != null) {
				switch (remote) {
				case yes:
					return manager.getCities(cities, null, true);
				case no:
					return manager.getCities(cities, null, false);
				}

			}
		}

		return manager.getCities(cities, null, null);

	}

	/**
	 * Il metodo <b>NoLocation</b> gestisce l'eccezione che si viene a creare nel
	 * metodo <b>getCities</b> quando si lascia vuoto il parametro delle location.
	 * 
	 * @param e eccezione
	 * @return <code>JSONObject</code> Oggetto dove viene descritto l'errore.
	 */
	@ExceptionHandler(NoLocationException.class)
	public static JSONObject NoLocation(NoLocationException e) {
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("errore 400", e.getMessage());
		JSONObject noLocation = new JSONObject(map);
		return noLocation;
	}

	/**
	 * Il metodo <b>UnsupportedValue</b> gestisce l'eccezione che si viene a creare
	 * nel metodo <b>getCities</b> quando si inserisce un valore inaspettato nel
	 * parametro <b>employment_type</b>.
	 * 
	 * @param e eccezione
	 * @return <code>JSONObject</code> Oggetto dove viene descritto l'errore.
	 */
	@ExceptionHandler(UnsupportedValueException.class)
	public static JSONObject UnsupportedValue(UnsupportedValueException e) {
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("errore 400", e.getMessage());
		JSONObject unsupportedValue = new JSONObject(map);
		return unsupportedValue;
	}

}
