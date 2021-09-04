package it.univpm.FindWorkApp.Manager;

import org.json.simple.JSONObject;

/**
 * 
 * <p>
 * l'interfaccia <b>ManagerService</b> racchiude i metodi che restituiscono
 * l'oggetto JSON contenteti i dati richiesti dall'utente
 * 
 * @author Paci Matteo
 * 
 */
public interface ManagerService {
	/**
	 * <p>
	 * Il metodo <b>getCities</b> ha l'obbiettivo di fare una chiamata all'api per
	 * poi restituire un oggetto JSON contente tutti i dati richiesti.
	 * 
	 * @param location        lista di città dove effettuare la ricerca
	 * @param employment_type parametro che indica il filtro per la tipologia di
	 *                        lavoro, full time o part time
	 * @param remote          parametro che indica il filtro per la tipologia di
	 *                        lavoro, in remoto o non
	 * @return <code>JSONObject</code> oggetto che contiene tutte le città con i
	 *         rispettivi lavori trovati in essa
	 * 
	 */
	public JSONObject getCities(String[] location, String employment_type);

	public JSONObject getCities(String[] location, String employment_type, boolean remote);

	/**
	 * <p>
	 * Il metodo<b>getStats</b> restituisce un oggetto JSON in cui sono presenti le
	 * statistiche, eventualemente filtrate, delle varie città richieste, se
	 * presenti in memoria.
	 * 
	 * @param location lista delle città di cui si vuole visualizzare le
	 *                 statistiche.
	 * @param date     parametro usato come filtro che indica la data di inizio da
	 *                 cui prendere i lavori per calcolare le statistiche.
	 * @param remote   parametro che indica il filtro della tipologia di lavoro, in
	 *                 remoto o non
	 * @return <code>JSONObject</code> oggetto che contiene tutte le città richieste
	 *         con le rispettive statistiche.
	 */
	public JSONObject getStats(String[] location, String date);

	public JSONObject getStats(String[] location, String date, boolean remote);

}
