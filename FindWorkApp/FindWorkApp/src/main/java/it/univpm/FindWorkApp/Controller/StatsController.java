package it.univpm.FindWorkApp.Controller;

import org.springframework.web.bind.annotation.RestController;

import it.univpm.FindWorkApp.Exception.OverflowCityException;
import it.univpm.FindWorkApp.Manager.Manager;
import org.json.simple.JSONObject;
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
	 * Questo metodo visualizza le statistiche delle città chiamate dalla precedente
	 * chiamata <b>/cities</b>. Se non vengono inserite delle località verrano
	 * stampate le statistiche di tutte le città che sono state inserite nell'ultima
	 * richiesta di tipo <b>/cities</b>. Se invece vengono inserite delle località,
	 * verranno stampate le loro statistiche a patto che siano state inserite nella
	 * precedente richiesta di tipo <b>/cities</b>.
	 *
	 * 
	 * @param location indica le città richieste dall'utente.
	 * 
	 * @return <code>JSONObject</code> Un JSONObject con le statistiche delle varie
	 *         città.
	 */
	@GetMapping("/stats")
	public JSONObject stats(@RequestParam(name = "location", required = false) String location) {
		if (location != null) {
			String[] locationArray = location.split("&");
			try {
				if (locationArray.length > 5) {
					throw new OverflowCityException();
				} else {
					return manager.getStats(locationArray, null); // return manager.getStats(locationArray);
				}
			} catch (OverflowCityException e) {
				JSONObject overFlowCity = new JSONObject();
				overFlowCity.put("Errore 400", e.getMessage());
				return overFlowCity;
			}
		}
		return manager.getStats(null, null); // return manager.getStats();
	}

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
	@GetMapping("/stats/filter")
	public JSONObject statsFilter(@RequestParam(name = "location", required = false) String location,
			@RequestParam(name = "date", required = false) String date,
			@RequestParam(name = "remote", required = false) Remote remote) {
		String[] locationArray = null;
		if (location != null) {
			locationArray = location.split("&");
			try {
				if (locationArray.length > 5) {
					throw new OverflowCityException();
				} else {
					if (date != null) {
						if (remote != null) {
							switch (remote) {
							case yes:
								return manager.getStats(locationArray, date, true);
							case no:
								return manager.getStats(locationArray, date, false);
							}

						}
						return manager.getStats(locationArray, date);
					}
					if (remote != null) {
						switch (remote) {
						case yes:
							return manager.getStats(locationArray, date, true);
						case no:
							return manager.getStats(locationArray, date, false);
						}

					}
					return manager.getStats(locationArray, date);
				}
			} catch (OverflowCityException e) {
				JSONObject overFlowCity = new JSONObject();
				overFlowCity.put("Errore 400", e.getMessage());
				return overFlowCity;
			}
		}
		if (date != null) {
			if (remote != null) {
				switch (remote) {
				case yes:
					return manager.getStats(locationArray, date, true);
				case no:
					return manager.getStats(locationArray, date, false);
				}

			}
			return manager.getStats(locationArray, date);

		}
		if (remote != null) {
			switch (remote) {
			case yes:
				return manager.getStats(locationArray, date, true);
			case no:
				return manager.getStats(locationArray, date, false);
			}

		}
		return manager.getStats(locationArray, date);

	}
}
