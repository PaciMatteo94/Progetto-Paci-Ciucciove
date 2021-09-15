package it.univpm.FindWorkApp.Manager;

import java.time.format.DateTimeParseException;

import it.univpm.FindWorkApp.Exception.NoResultsException;

/**
 * 
 * <p>
 * L'interfaccia <b>ManagerService</b> racchiude i metodi che restituiscono
 * l'oggetto contenenti i dati richiesti dall'utente
 * 
 * @author Paci Matteo
 * @author Ciucciovè Leonardo
 */
public interface ManagerService {
	/**
	 * <p>
	 * Il metodo <b>getCities</b> ha l'obbiettivo di fare una chiamata all'API per
	 * poi restituire un oggetto contente tutti i dati richiesti.
	 * 
	 * @param location        lista di città dove effettuare la ricerca
	 * @param employment_type parametro che indica il filtro per la tipologia di
	 *                        lavoro, full time o part time
	 * @param remote          parametro che indica il filtro per la tipologia di
	 *                        lavoro, in remoto o non
	 * @throws NoResultsException eccezione generata quando non ci sono risultati
	 *                            nella ricerca.
	 * @return <code>Object</code> oggetto che contiene tutte le città con i
	 *         rispettivi lavori trovati in essa
	 * 
	 */

	public Object getCities(String[] location, String employment_type, Boolean remote) throws NoResultsException;

	/**
	 * <p>
	 * Il metodo<b>getStats</b> ha l'obbiettivo di fare una chiamata all'API per poi
	 * restituire un oggetto che contiene le statistiche, eventualmente filtrate,
	 * delle città richieste.
	 * 
	 * @param location lista delle città di cui si vuole visualizzare le
	 *                 statistiche.
	 * @param date     parametro usato come filtro che indica la data di inizio da
	 *                 cui prendere i lavori per calcolare le statistiche.
	 * @param remote   parametro che indica il filtro della tipologia di lavoro, in
	 *                 remoto o non
	 * @return <code>Object</code> oggetto che contiene tutte le città richieste con
	 *         le rispettive statistiche.
	 */

	public Object getStats(String[] location, String date, Boolean remote) throws DateTimeParseException;

}
