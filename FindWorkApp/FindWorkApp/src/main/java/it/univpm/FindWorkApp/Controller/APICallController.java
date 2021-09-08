package it.univpm.FindWorkApp.Controller;

import org.springframework.web.bind.annotation.RestController;
import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

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
		Preference pref = new Preference();
		JSONObject obj = new JSONObject();
		obj.put("Città di preferenza", pref.getPreference());
		return obj;
	}
	//@PostMapping("/preferences")
	@RequestMapping(value = "/preferences", method = RequestMethod.POST)
	public @ResponseBody JSONObject suggested(@RequestBody (required=false) String body) {

		JSONObject js = new JSONObject();
		js.put("Città inserite", ...);
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
	public JSONObject cityFilter(@RequestParam(name = "location") String location,
			@RequestParam(name = "employment_type", required = false) String employment_type,
			@RequestParam(name = "remote", required = false) Remote remote) {
		try {
			if (location == "")
				throw new NoLocationException();
		} catch (NoLocationException e) {
			JSONObject noLocation = new JSONObject();
			noLocation.put("errore 400", e.getMessage());
			return noLocation;
		}
		String[] cityArray = location.split(", |&|,");
		String[] cities;

		if (cityArray.length < 5) {
			cities = Arrays.copyOfRange(cityArray, 0, cityArray.length);
		} else {
			cities = Arrays.copyOfRange(cityArray, 0, 5);
		}
		try {
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
		} catch (UnsupportedValueException e) {
			JSONObject unsupportedValue = new JSONObject();
			unsupportedValue.put("errore 400", e.getMessage());
			return unsupportedValue;
		}
		return manager.getCities(cities, null, null);

	}
}
