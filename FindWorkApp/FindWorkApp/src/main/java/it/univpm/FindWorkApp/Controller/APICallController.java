package it.univpm.FindWorkApp.Controller;

import org.springframework.web.bind.annotation.RestController;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Arrays;

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
		obj.put("Città di preferenza", pref);
		return obj;
	}

	/**
	 * <p>
	 * Questo metodo restituisce i lavori presenti fino ad un massimo di 5 città. Se
	 * non vengono inserite città, verrà visualizzato un messaggio di errore.
	 * 
	 * @param location indica il nome di una o più città in cui il cliente vuole
	 *                 cercare
	 * @return <code>JSONObject</code>oggetto di tipo JSON che contiene tutte le
	 *         informazioni richieste dall'utente.
	 */
	@GetMapping("/cities")
	public JSONObject city(@RequestParam(name = "location") String location) {
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
		// Prendo la parte di array che mi interessa copiando i valori fino all termine
		// dello stesso
		if (cityArray.length <= 5) {
			cities = Arrays.copyOfRange(cityArray, 0, cityArray.length);
		} else {
			cities = Arrays.copyOfRange(cityArray, 0, 5);
		}
		return manager.getCities(cities, null); // return manager.getCities(sCityArray)

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
	@GetMapping("/cities/filter")
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
					return manager.getCities(cities, employment_type); // return
																		// manager.getCities(cities,employment_type)
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
		return manager.getCities(cities, null); // return manager.getCities(sCityArray2);

	}
}
