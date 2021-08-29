package it.univpm.FindWorkApp.controller;

import org.springframework.web.bind.annotation.RestController;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Arrays;   
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
	/**
	 * Questo metodo restituisce cinque città predefinite come suggerimento di ricerca per l'utente.
	 * 
	 * @return <code>Pref</code>
	 * 
	 */

	
	@GetMapping("/preferences")
	public Pref city(@RequestParam(name="nome", defaultValue="none") String nome) {
		String[] cittas= {"London","Berlin","New York","Amsterdam","Paris"};
		return new Pref(cittas);
	}
		




	/**
	 * Questo metodo filtra in modo generale tutti i lavori dividendoli per città e con essi tutte le caratteristiche annesse allo stesso.
     *
	 * @param location  indica il nome delle città inserite
	 * 
	 * @return <code>CitiesJO</code>
	 * 
	 */
	@GetMapping("/cities")
	public JSONObject City(
			@RequestParam(name="location", defaultValue="") String location) {
		if (location =="") {
			//qui bisogna fare un throw
			return new JSONObject();
		}
			String[] cityArrayList = location.split("&");
			String[] sCityArray;
			// Prendo la parte di array che mi interessa copiando i valori fino all termine
			// dello stesso
			if (cityArrayList.length < 5) {
				sCityArray = Arrays.copyOfRange(cityArrayList, 0, cityArrayList.length);
			} else {
				sCityArray = Arrays.copyOfRange(cityArrayList, 0, 4);
			}
			return new JSONObject(); //return manager.getCities(sCityArray)
		
	}
	/**
	 * Questo metodo mi permette di filtrare le caratteristiche dei lavori più significative per una ricerca mirata.
	 * Qui le variabili aggiungono un '2' per semplificarne l'origine per una lettura più chiara
	 * 
	 * @param location  indica il nome delle città inserite
	 * @param employment_type  indica se il lavoro è full time o part time/contratto
	 * @param remote indica se il lavoro si svolge da remoto o non 
	 * 
	 * @return <code>CitiesJO</code>
	 * 
	 */
	@GetMapping("/cities/filter")
	public JSONObject Cityfilter(
			@RequestParam(name="location", defaultValue="") String location,
			@RequestParam(name="emplyment_type", defaultValue="") String employment_type,
			@RequestParam(name="remote", required=false) boolean remote) {
		if (location == "") {
			// qui ci va un throw;
			return new JSONObject(); 
		}
		String[] CityArrayList2= location.split(", |&|,",5);
		String et2=employment_type;
		Boolean rem2=remote;
		String[] SCityArray2;
		
		if (CityArrayList2.length<5) {
			SCityArray2 = Arrays.copyOfRange(CityArrayList2, 0, CityArrayList2.length);}
			else {
				SCityArray2 = Arrays.copyOfRange(CityArrayList2, 0, 4);
			}
		
		
		return new JSONObject(); //return manager.getCities(sCityArray2,remote, employment_type);

	}
}
