package it.univpm.FindWorkApp.APICall;

import it.univpm.FindWorkApp.model.City;

/**
 * <p>
 * L'interfaccia <b>APICallService</b> gestisce le chiamate all'API.
 * 
 * @author Paci Matteo
 * @author Ciucciovè Leonardo
 *
 */
public interface APICallService {
	/**
	 * <p>
	 * Il metodo <b>getData</b> ha come obbiettivo quello di chiamare l'API e
	 * salvare i dati che riceve in un modello chiamato City.
	 * 
	 * @return <code>City</code> oggetto che racchiude le informazioni e i lavori di
	 *         una città.
	 */
	public City getData();

}
