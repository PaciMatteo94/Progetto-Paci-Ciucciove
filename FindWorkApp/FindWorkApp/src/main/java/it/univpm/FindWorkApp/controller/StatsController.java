package it.univpm.FindWorkApp.controller;

import org.springframework.web.bind.annotation.RestController;

import it.univpm.FindWorkApp.Exception.WrongURLException;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
/**
 * 
 * @author Paci Matteo
 * @author Ciucciovè Leonardo
 * Questa classe gestisce le chiamate postman per quanto riguarda le statistiche delle città.
 *
 * 
 */
@RestController
public class StatsController {
/**
 * Questo metodo permette di visualizzare le statistiche delle città chiamate dalla precedente chiamata "/cities".
 * Possibilità:
 * 1) con la semplice chiamata /stats verrano visualizzate le statistiche di tutte le città chiamate dall'ultima richiesta di tipo "/cities"
 *    se prima non è stata effettuata nessuna richiesta "/cities", verrà visualizzato un messaggio di errore che descrive il caso in questione.
 * 2) con la chimata /stats può essere inserito un parametro di tipo location dove vengono inserite le città delle quali si vuole visualizzare le statistiche.
 *    il programma accetta al massimo 5 città che estrarrà dalla stringa di location, se vengono inserite più di 5 città verrà visualizzato un messaggio di errore che descrive il caso presente.
 *    se viene inserito un numero città compreso tra 1 e 5 il metodo visualizzerà le statistiche delle città che sono state richieste a patto che siano state inserite nell'ultima richiesta di tipo "/cities".
 *
 * 
 * @param location indica le città richieste dall'utente. 
 * 
 * @return <code>JSONObject</code> Un JSONObject con le statistiche delle varie città. 
 */
	@GetMapping("/stats")
	public JSONObject stats(@RequestParam(name = "location", required = false) String location) {
		if (location != null) {
			String[] locationArray = location.split("&");
			try {
				if (locationArray.length > 5) {
					throw new WrongURLException("Sono state inserite più di 5 città nei parametri");
				} else {
					return new JSONObject(); // return manager.getStats(locationArray);
				}
			} catch (WrongURLException e) {
				JSONObject error = new JSONObject();
				error.put("Errore 400", e.getMessage());
				return error;
			}
		}
		return new JSONObject(); // return manager.getStats();
	}

/**
 * Questo metodo permette di visualizzare le statistiche delle città inserite nell'ultima chimata "/cities" filtrate tramite alcuni parametri.
 * Possibilità:
 * 1) con la semplice chiamata /stats/filter senza aggiunta di nessun parametro vengono visualizzate le statistiche in modo uguale alla richista /stats.
 * 2) con l'inserimento delle location e nessun altro parametro, verranno visualizzate le statistiche nello stesso modo in cui lavora il precedente metodo.
 * 3) con l'inserimento di uno o di entrambi i parametri "remote" e "date" verranno visualizzate le statistiche di tutte le città dell'ultima chiamata "/cities" filtrate per i parametri inseriti.
 * 4) con l'inserimento delle città e dei parametri verranno visualizzate le statistiche filtrate delle città richieste a patto che siano state inserite nell'ultima richiesta "/cities", anche qui il numero massimo di città è 5.
 * 
 * @param location indica le città richieste dall'utente
 * @param remote indica il filtro di lavoro in remoto
 * @param date indica la data di filtraggio
 * 
 * @return <code>JSONObject</code> Un JSONObject che contiene le varie statistiche delle varie città.
 */
	@GetMapping("/stats/filter")
	public JSONObject statsFilter(@RequestParam(name = "location", required=false) String location,
			@RequestParam(value = "remote", required = false) boolean remote,
			@RequestParam(name = "date", required=false) String date) {
		if (location != null) {
			String[] locationArray = location.split("&");
			try {
				if (locationArray.length > 5) {
					throw new WrongURLException("Sono state inserite più di 5 città nei parametri");
				} else {
					return new JSONObject(); // return manager.getStats(locationArray,remote,date);
				}
			} catch (WrongURLException e) {
				JSONObject error = new JSONObject();
				error.put("Errore 400", e.getMessage());
				return error;
			}
		}
		return new JSONObject(); //return manager.getStats(remote,date);
	}
}
