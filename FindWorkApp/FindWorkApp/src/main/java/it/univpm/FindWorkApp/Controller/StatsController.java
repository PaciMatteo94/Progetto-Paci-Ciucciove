package it.univpm.FindWorkApp.Controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import it.univpm.FindWorkApp.Exception.OverflowCityException;
import it.univpm.FindWorkApp.Manager.Manager;
import it.univpm.FindWorkApp.Model.Preference;

import java.time.format.DateTimeParseException;
import java.util.HashMap;


import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <p>
 * Questa classe gestisce le chiamate postman per quanto riguarda le statistiche
 * delle citt&aacute;.
 * 
 * @author Paci Matteo
 * @author Ciucciov&eacute; Leonardo
 */
@RestController
public class StatsController {
	Preference pref = Preference.getInstance();

	private static enum Remote {
		yes, no
	}

	private Manager manager = Manager.getInstance();

	/**
	 * <p>
	 * Questo metodo visualizza le statistiche delle citt&aacute; inserite con
	 * l'oppurtunit&aacute; di inserire dei filtri. Se non vengono inserite
	 * locazioni, saranno restituite le statistiche delle citt&aacute; presenti
	 * nelle preferenze.
	 * 
	 * 
	 * @param location indica le citt&aacute; richieste dall'utente.
	 * @param date     indica la data di filtraggio.
	 * @param remote   indica il filtro per quanto riguarda la tipologia di lavoro
	 *                 in remoto o non.
	 * @throws OverflowCityException eccezione generata quando si inserisco più di 5
	 *                               citt&aacute; nel campo location per la ricerca.
	 * 
	 * @return <code>Object</code> Un Object che contiene le varie statistiche delle
	 *         varie citt&aacute;.
	 */
	@GetMapping("/stats")
	public Object statsFilter(@RequestParam(name = "location", required = false) String location,
			@RequestParam(name = "date", required = false) String date,
			@RequestParam(name = "remote", required = false) Remote remote)
			throws OverflowCityException, DateTimeParseException, MethodArgumentTypeMismatchException {
		String[] locationArray = null;
		if (location != null) {
			locationArray = location.split("&");

			if (locationArray.length > 5) {
				throw new OverflowCityException();
			} else {
				if (remote != null) {
					switch (remote) {
					case yes:
						return manager.getStats(locationArray, date, true);
					case no:
						return manager.getStats(locationArray, date, false);
					}

				}
				return manager.getStats(locationArray, date, null);

			}

		} else {
			if (remote != null) {
				switch (remote) {
				case yes:
					return manager.getStats(pref.getPreference(), date, true);
				case no:
					return manager.getStats(pref.getPreference(), date, false);
				}

			}
			return manager.getStats(pref.getPreference(), date, null);
		}
	}

	/**
	 * Il metodo <b>OverflowCity</b> gestisce l'eccezione che si viene a creare nel
	 * metodo <b>getStats</b> quando si inseriscono più di 5 citt&aacute;.
	 * 
	 * @param e eccezione
	 * @return <code>Object</code> Oggetto dove viene descritto l'errore.
	 */
	@ExceptionHandler(OverflowCityException.class)
	public static Object OverflowCity(OverflowCityException e) {
		HashMap<String, String> overFlowCity = new HashMap<String, String>();
		overFlowCity.put("Errore 400", e.getMessage());
		return overFlowCity;
	}

	/**
	 * Il metodo <b>unsupportedValue</b> gestisce l'eccezione che viene generata dal
	 * parser della data nel metodo <b>statsFiltered</b>.
	 * 
	 * @param e eccezione
	 * @return <code>Object</code> Oggetto dove viene descritto l'errore.
	 */
	@ExceptionHandler(DateTimeParseException.class)
	public static Object unsupportedValue(DateTimeParseException e) {
		HashMap<String, String> unsupportedValueError = new HashMap<String, String>();
		unsupportedValueError.put("errore 400", "Si è inserita una data sbagliata");
		return unsupportedValueError;
	}

	/**
	 * <p>
	 * Cattura l'eccezione prodotta nel caso si inserisce un valore non corretto nel
	 * parametro remote.
	 * 
	 * @param e eccezione
	 * @return <code>Object</code> contiene il messaggio di errore della relativa
	 *         eccezione
	 */
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public static Object unsupportedValue(MethodArgumentTypeMismatchException e) {
		HashMap<String, String> noResultsError = new HashMap<String, String>();
		noResultsError.put("Errore 400", "è stato inserito un valore non corretto in remote");
		return noResultsError;
	}

}