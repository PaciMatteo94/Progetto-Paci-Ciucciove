package it.univpm.FindWorkApp.Manager;

import java.util.ArrayList;
import it.univpm.FindWorkApp.APICall.APICall;
import it.univpm.FindWorkApp.Exception.NoResultsException;
import it.univpm.FindWorkApp.Model.City;
import it.univpm.FindWorkApp.Parser.FindWorkParser;
import it.univpm.FindWorkApp.Stats.Stats;

/**
 * <p>
 * La classe <b>Manager</b> implementa l'interfaccia <b>ManagerService</b> e ha
 * l'obbiettivo di gestire le chiamate all'api e restituire un oggetto in cui
 * sono salvati tutti i dati richiesti.
 * 
 * @author Paci Matteo
 *
 */

public class Manager implements ManagerService {
	/**
	 * <p>
	 * la classe è implementata come singleton semplice. Si creerà una singola
	 * instanza che verrà poi usata dagli altri metodi per tutta l'esecuzione del
	 * programma.
	 */
	private Stats stats = Stats.getInstance();
	private FindWorkParser parser = FindWorkParser.getInstance();
	private APICall call = APICall.getInstance();
	private ArrayList<City> cities;
	private static Manager instance = null;
	private Manager() {
	}

	public static Manager getInstance() {
		if (instance == null) {
			instance = new Manager();
		}
		return instance;
	}

	/**
	 * Il metodo <b>getCities</b> ha l'obbiettivo di gestire le funzioni necessarie
	 * alla creazione dell'oggetto da restituire all'utente. In base ai parametri
	 * che gli vengono passati effettuerà le varie chiamate all'API, salvare gli
	 * oggetti <b>City</b> in un ArrayList che verrà poi passata al metodo
	 * <b>getCitiesObj</b> che impacchetterà le informazioni in un oggetto che verrà
	 * restituito all'utente.
	 */
	@Override
	public Object getCities(String[] location, String employment_type, Boolean remote) throws NoResultsException {

		City city = null;
		cities = new ArrayList<City>();
		if (employment_type != null) {
			for (String name : location) {
				call.setAPICall(name, employment_type, remote);
				city = call.getData();
				if (city.getWork().size() != 0) {
					cities.add(city);

				}
			}
			if (cities.size() == 0)
				throw new NoResultsException();
			return parser.getCitiesObj(cities);

		} else {
			for (String name : location) {
				call.setAPICall(name, remote);
				city = call.getData();
				if (city.getWork().size() != 0) {
					cities.add(city);

				}
			}
			if (cities.size() == 0)
				throw new NoResultsException();
			return parser.getCitiesObj(cities);

		}
	}

	/**
	 * Il metodo <b>getStats</b> ha l'obbiettivo di gestire le funzioni necessarie
	 * alla creazione dell'oggetto da restituire all'utente. In base ai parametri
	 * che gli vengono passati effettuerà le varie chiamate all'API, calcolerà le
	 * statistiche della città esalverà gli oggetti di tipo <b>City</b> in un
	 * ArrayList che verrà poi passata al metodo <b>getStatsObj</b> che impacchetterà le
	 * informazioni in un oggetto che verrà restituito all'utente.
	 */
	@Override
	public Object getStats(String[] location, String date, Boolean remote) {
		City city = null;
		cities = new ArrayList<City>();
		for (String name : location) {
			call.setAPICall(name, remote);
			city = call.getData();
			if (city.getWork().size() != 0) {
				if (date != null) {
					stats.statsFiltered(city, date);
					cities.add(city);
				} else {
					stats.statsCalculate(city);
					cities.add(city);
				}
			}
		}

		return parser.getStatsObj(cities);
	}
}
