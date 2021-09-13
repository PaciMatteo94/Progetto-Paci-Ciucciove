package it.univpm.FindWorkApp.Controller;

import org.springframework.web.bind.annotation.RestController;

import it.univpm.FindWorkApp.Exception.OverflowCityException;
import it.univpm.FindWorkApp.Manager.Manager;
import it.univpm.FindWorkApp.Model.Preference;

import java.util.HashMap;

import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <p>
 * Questa classe gestisce le chiamate postman per quanto riguarda le statistiche
 * delle città.
 * 
 * @author Paci Matteo
 * @author Ciucciovè Leonardo
 */
@RestController
public class StatsController {
	private static enum Remote {
		yes, no
	}

	private Manager manager = Manager.getInstance();

	/**
	 * <p>
	 * Questo metodo visualizza le statistiche delle città inserite con
	 * l'oppurtunità di inserire dei filtri. Se non vengono inserite locazioni,
	 * saranno restituite le statistiche delle città presenti nelle preferenze.
	 * 
	 * 
	 * @param location indica le città richieste dall'utente.
	 * @param date     indica la data di filtraggio.
	 * @param remote   indica il filtro per quanto riguarda la tipologia di lavoro
	 *                 in remoto o non.
	 * 
	 * @return <code>JSONObject</code> Un JSONObject che contiene le varie
	 *         statistiche delle varie città.
	 */
	@GetMapping("/stats")
	public JSONObject statsFilter(@RequestParam(name = "location", required = false) String location,
			@RequestParam(name = "date", required = false) String date,
			@RequestParam(name = "remote", required = false) Remote remote) throws OverflowCityException {
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
			Preference prefStats = new Preference();
			if (remote != null) {
				switch (remote) {
				case yes:
					return manager.getStats(prefStats.getPreference(), date, true);
				case no:
					return manager.getStats(prefStats.getPreference(), date, false);
				}

			}
			return manager.getStats(prefStats.getPreference(), date, null);
		}
	}
	
/**
 * Il metodo <b>OverflowCity</b> gestisce l'eccezione che si viene a creare nel
	 * metodo <b>getStats</b> quando si inseriscono più di 5 città.
	 * 
	 * @param e eccezione
	 * @return <code>JSONObject</code> Oggetto dove viene descritto l'errore.
 */
	@ExceptionHandler(OverflowCityException.class)
	public static JSONObject OverflowCity(OverflowCityException e) {
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("Errore 400", e.getMessage());
		JSONObject overFlowCity = new JSONObject();
		return overFlowCity;
	}
}