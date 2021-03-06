package it.univpm.FindWorkApp.Parser;

import java.util.ArrayList;

import it.univpm.FindWorkApp.Model.City;

/**
 * <p>
 * L'interfaccia <b>FindWorkParserService</b> racchiude i metodi necessari a
 * formare l'oggetto con tutti i dati che poi saranno restituiti all'utente.
 * 
 * @author Paci Matteo
 * @author Ciucciov&eacute; Leonardo
 *
 */
public interface FindWorkParserService {
	/**
	 * <p>
	 * Il metedo <b>getCitiesObj</b> produce l'oggetto con i lavori presenti nelle
	 * varie citt&aacute; di richiesta filtrati per gli opportuni parametri.
	 * 
	 * @param cities lista di citt&aacute; dove sono presenti i lavori che
	 *               soddisfano le richieste dell'utente.
	 * 
	 * @return <code>Object</code>
	 */
	public Object getCitiesObj(ArrayList<City> cities);

	/**
	 * Il metedo <b>getStatsObj</b> produce l'oggetto racchiude le statistiche,
	 * oppurtamente filtrate, dei lavori presenti nelle citt&aacute; di ricerca.
	 * 
	 * @param cities lista di citt&aacute; su cui sono state calcolate le
	 *               statistiche.
	 * @return <code>Object</code>
	 */
	public Object getStatsObj(ArrayList<City> cities);
}
