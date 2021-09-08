package it.univpm.FindWorkApp.Controller;

import org.springframework.web.bind.annotation.RestController;

import it.univpm.FindWorkApp.Exception.IncompatibilityRemoteException;
import it.univpm.FindWorkApp.Exception.NoCityException;
import it.univpm.FindWorkApp.Exception.OverflowCityException;
import it.univpm.FindWorkApp.Manager.Manager;
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
	 * Questo metodo visualizza le statistiche delle città chiamate dalla precedente
	 * chiamata <b>/cities</b> con la possibilità di inserire dei filtri su cui
	 * calcolarle. Se vengono inserite delle locazioni, si stamperanno le statische,
	 * filtrate o non, solamente nel caso in cui tali locazioni sono presenti in
	 * memoria e cioè se sono state inserite nell'ultima chiamata di tipo
	 * <b>/cities</b>.
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
			@RequestParam(name = "remote", required = false) Remote remote)
			throws NoCityException, IncompatibilityRemoteException {
		String[] locationArray = null;
		if (location != null) {
			locationArray = location.split("&");
			try {
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
			} catch (OverflowCityException e) {
				JSONObject overFlowCity = new JSONObject();
				overFlowCity.put("Errore 400", e.getMessage());
				return overFlowCity;
			}
		}
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

	@ExceptionHandler(NoCityException.class)
	public static JSONObject error(NoCityException e) {
		JSONObject noCity = new JSONObject();
		noCity.put("Errore 400", e.getMessage());
		noCity.put("Descrizione",
				"Prima di chiamare la rotta stats bisogna concludere con successo una richiesta /cities");
		return noCity;
	}

	@ExceptionHandler(IncompatibilityRemoteException.class)
	public static JSONObject errorCall(IncompatibilityRemoteException e) {
		JSONObject remoteInc = new JSONObject();
		remoteInc.put("Errore 400", e.getMessage());
		return remoteInc;
	}
}