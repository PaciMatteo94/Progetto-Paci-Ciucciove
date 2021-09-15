package it.univpm.FindWorkApp.Stats;


import java.time.format.DateTimeParseException;

import it.univpm.FindWorkApp.Model.City;

/**
 * <p>
 * L'interfaccia <b>StatsService</b> racchiude i metodi per calcolare le
 * statistiche.
 * 
 * @author Paci Matteo
 * @author Ciucciov&egrave; Leonardo
 *
 */
public interface StatsService {
	/**
	 * Il metodo <b>statsCalculate</b> calcola le statistiche dei lavori in una
	 * citt&aacute;.
	 * 
	 * @param city Oggetto che rappresenta la citt&aacute; con i suoi lavori e statistiche.
	 * @return <code>City</code>
	 */
	public City statsCalculate(City city);

	/**
	 * Il metodo <b>statsFiltered</b> filtra i lavori per data per poi calcolare le
	 * statistiche della citt&aacute; con i lavori che soddisfano i filtri inseriti
	 * dall'utente.
	 * 
	 * @param city Oggetto che rappresenta la citt&aacute; con i suoi lavori e statistiche.
	 * @param date indica la data con cui filtrare i lavori
	 * @return <code>City</code>
	 */
	public City statsFiltered(City city, String date)throws DateTimeParseException;
}
