package it.univpm.FindWorkApp.Stats;

import it.univpm.FindWorkApp.Model.City;

/**
 * <p>
 * L'interfaccia <b>StatsService</b> racchiude i metodi per calcolare le
 * statistiche.
 * 
 * @author Paci Matteo
 * @author Ciucciovè Leonardo
 *
 */
public interface StatsService {
	/**
	 * Il metodo <b>statsCalculate</b> calcola le stetistiche dei lavori in una
	 * città.
	 * 
	 * @param city Oggetto che rappresenta la città con i suoi lavori e statistiche.
	 * @return <code>City</code>
	 */
	public City statsCalculate(City city);

	/**
	 * Il metodo <b>statsFiltered</b> filtra i lavori per data per poi calcolare le
	 * statiche della città con i lavori che soddisfano i filtri inseriti
	 * dall'utente.
	 * 
	 * @param city Oggetto che rappresenta la città con i suoi lavori e statistiche.
	 * @param date indica la data con cui filtrare i lavori
	 * @return <code>City</code>
	 */
	public City statsFiltered(City city, String date);
}
