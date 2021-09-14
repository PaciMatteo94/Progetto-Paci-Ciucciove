package it.univpm.FindWorkApp.Controller;

import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import it.univpm.FindWorkApp.Exception.EmptyBodyException;
import it.univpm.FindWorkApp.Exception.NoLocationException;
import it.univpm.FindWorkApp.Exception.NoResultsException;
import it.univpm.FindWorkApp.Exception.UnsupportedValueException;
import it.univpm.FindWorkApp.Exception.WrongCredentialsException;
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
	Preference pref = Preference.getInstance();

	private static enum Remote {
		yes, no
	}

	private Manager manager = Manager.getInstance();

	/**
	 * <p>
	 * Questo metodo restituisce cinque città predefinite come suggerimento di
	 * ricerca per l'utente.
	 *
	 * @return <code>Object</code>
	 */

	@GetMapping("/preferences")
	public Object preferences() {
		Map<String, Object> preference = new LinkedHashMap<String, Object>();
		preference.put("città di preferenza", pref.getPreference());
		return preference;
	}

	/**
	 * <p>
	 * Questo metodo sfrutta l'HttpServletResponse per creare un facsimile di
	 * autenticazione creando prima degli headers durante la risposta del server
	 * all'invio della richiesta per la rotta '/preferences'. Vengono poi
	 * confrontati con il contenuto del body e se corrette, l'header AUTHENTICATION
	 * verrà settato a 'YES'. Quando il body non sarà vuoto né conterra delle
	 * credenziali errate, allora il metodo prenderà le città che l'utente avrà
	 * inserito nella scheda parametri e le restituirà allo stesso modo di una
	 * richiesta fatta allo stesso indirizzo utilizzando il metodo GET. L'utente qui
	 * ha scelta, potrà inserire le città che vuole.
	 *
	 * @param body     dove sono contenute le credenziali inserite che vengono
	 *                 confrontate
	 * @param response elemento di tipo 'HttpServletResponse' utile per generare
	 *                 specifiche funzionalità HTTP, in questo caso l'aggiunte
	 *                 diìegli headers di autenticazione
	 * @param location dove vengono salvate le località scelte dall'utente
	 * @throws EmptyBodyException
	 * @throws WrongCredentialsException
	 * 
	 * @return <code>Object</code>
	 * 
	 */

	@RequestMapping(value = "/preferences", method = RequestMethod.POST)
	@ResponseBody
	public Object suggested(@RequestBody(required = false) String body, HttpServletResponse response,
			HttpServletRequest request, @RequestParam(name = "Location", required = false) String location)
			throws EmptyBodyException, WrongCredentialsException {
		response.addHeader("Username", "admin");
		response.addHeader("Password", "root");
		response.addHeader("Authenticate", "NO");
		boolean r = true;
		do {
			if (body == null)
				throw new EmptyBodyException();
			if (!(body.contains(response.getHeader("Username")) && body.contains(response.getHeader("Password"))))
				throw new WrongCredentialsException();
			r = false;
		} while (r);
		response.setHeader("Authenticate", "YES");
		if (response.getHeader("Authenticate").equals("YES")) {
			try {
				String s;
				s = request.getParameter("Location");
				if (location == null && s == null)
					throw new NoLocationException();
			} catch (NoLocationException e) {
				HashMap<String, String> noLocation = new HashMap<String, String>();
				noLocation.put("Errore 400", e.getMessage() + ". Oppure hai sbagliato ad inserire il parametro");
				return noLocation;
			}
			String[] cityArray = location.split(", |&|,");
			String[] cities;

			if (cityArray.length < 5) {
				cities = Arrays.copyOfRange(cityArray, 0, cityArray.length);
			} else {
				cities = Arrays.copyOfRange(cityArray, 0, 5);
			}

			pref.setPreference(cities);
		}
		String conferma = "Le città sono state inserite correttamente";
		return conferma;

	}

	/**
	 * <p>
	 * Questo metodo permette di ricercare i lavori presenti in una o più città con
	 * la possibilità di inserire dei filtri di ricerca.
	 *
	 * @param location        indica il nome di una o più città di ricerca.
	 * @param employment_type indica se il lavoro è full time o part time/contratto.
	 * @throws NoLocationException, UnsupportedValueException, NoResultsException.
	 * 
	 * @return <code>Object</code> oggetto che contiene tutte le informazioni
	 *         richieste dall'utente.
	 */
	@GetMapping("/cities")
	public Object cityFilter(@RequestParam(name = "location", required = false) String location,
			@RequestParam(name = "employment_type", required = false) String employment_type,
			@RequestParam(name = "remote", required = false) Remote remote)
			throws NoLocationException, UnsupportedValueException, NoResultsException {
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
	 * @return <code>Object</code> Oggetto dove viene descritto l'errore.
	 */
	@ExceptionHandler(NoLocationException.class)
	public static Object noLocation(NoLocationException e) {
		HashMap<String, String> noLocationError = new HashMap<String, String>();
		noLocationError.put("errore 400", e.getMessage());
		return noLocationError;
	}

	/**
	 * Il metodo <b>UnsupportedValue</b> gestisce l'eccezione che si viene a creare
	 * nel metodo <b>getCities</b> quando si inserisce un valore inaspettato nel
	 * parametro <b>employment_type</b>.
	 *
	 * @param e eccezione
	 * @return <code>Object</code> Oggetto dove viene descritto l'errore.
	 */
	@ExceptionHandler(UnsupportedValueException.class)
	public static Object unsupportedValue(UnsupportedValueException e) {
		HashMap<String, String> unsupportedValueError = new HashMap<String, String>();
		unsupportedValueError.put("errore 400", e.getMessage());
		return unsupportedValueError;
	}

	/**
	 * <p>
	 * Cattura esterna dell'eccezione relativa ad un contenuto vuoto del body
	 *
	 * @param e eccezione
	 * @return <code>Object</code> contiene il messaggio di errore della relativa
	 *         eccezione
	 */
	@ExceptionHandler(EmptyBodyException.class)
	public static Object emptyBody(EmptyBodyException e) {
		HashMap<String, String> emptyBodyError = new HashMap<String, String>();
		emptyBodyError.put("Errore 400", e.getMessage());
		return emptyBodyError;

	}

	/**
	 * <p>
	 * Cattura esterna dell'eccezione prodotta dall'inserimento errato delle
	 * credenziali di accesso
	 *
	 * @param e eccezione
	 * @return <code>Object</code> contiene il messaggio di errore della relativa
	 *         eccezione
	 */
	@ExceptionHandler(WrongCredentialsException.class)
	public static Object wrongCredentials(WrongCredentialsException e) {
		HashMap<String, String> wCredentialsError = new HashMap<String, String>();
		wCredentialsError.put("Errore 401", e.getMessage() + " Credenziali errate");
		return wCredentialsError;
	}

	/**
	 * <p>
	 * Cattura l'eccezione prodotta nel caso in cui non si trovano lavori che
	 * soddisfano i parametri inseriti dall'utente.
	 * 
	 * @param e eccezione
	 * @return <code>Object</code> contiene il messaggio di errore della relativa
	 *         eccezione
	 */
	@ExceptionHandler(NoResultsException.class)
	public static Object noResults(NoResultsException e) {
		HashMap<String, String> noResultsError = new HashMap<String, String>();
		noResultsError.put("Errore 400", e.getMessage());
		return noResultsError;
	}
}
