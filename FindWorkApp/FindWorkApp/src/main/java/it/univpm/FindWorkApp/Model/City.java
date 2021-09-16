package it.univpm.FindWorkApp.Model;

import java.util.ArrayList;

/**
 * Classe che rappresenta gli oggetti di tipo <b>City</b>.
 *
 * @author Paci Matteo
 * @author Ciucciov&eacute; Leonardo
 *
 */
public class City {
	private String location;
	private Long count;
	private CityStats cityStats;
	private ArrayList<WorkInformation> work;

	/**
	 * Costruttore della classe <b>City</b>.
	 * 
	 * @param location nome della citt&aacute;.
	 */
	public City(String location) {
		this.location = location;
	}

	/**
	 * Metodo per ottenere <b>location</b>.
	 * 
	 * @return <code>String</code> nome della citt&aacute;.
	 */

	public String getLocation() {
		return location;
	}

	/**
	 * Metodo per settare <b>location</b>.
	 * 
	 * @param location nome della citt&aacute;.
	 */

	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * Metodo per ottenere <b>count</b>.
	 * 
	 * @return <code>Long</code> numero di lavori presenti nella citt&aacute;.
	 */

	public Long getCount() {
		return count;
	}

	/**
	 * Metodo per settare <b>count</b>.
	 * 
	 * @param count numero di lavori presenti nella citt&aacute;
	 */

	public void setCount(Long count) {
		this.count = count;
	}

	/**
	 * Metodo per ottenere <b>cityStats</b>.
	 * 
	 * @return <code>CityStats</code> oggetto dove sono salvate le statistiche dei
	 *         lavori presenti in citt&aacute;
	 */

	public CityStats getCityStats() {
		return cityStats;
	}

	/**
	 * Metodo per settare <b>cityStats</b>.
	 * 
	 * @param cityStats oggetto sove sono salvate le statistiche dei lavori presenti
	 *                  in citt&aacute;
	 */

	public void setCityStats(CityStats cityStats) {
		this.cityStats = cityStats;
	}

	/**
	 * Metodo per ottenere <b>work</b>.
	 * 
	 * @return <code>ArrayList</code> lista di lavori presenti in una citt&aacute;
	 */

	public ArrayList<WorkInformation> getWork() {
		return work;
	}

	/**
	 * Metodo per settare <b>work</b>.
	 * 
	 * @param work lista di lavori presenti in una citt&aacute;
	 */

	public void setWork(ArrayList<WorkInformation> work) {
		this.work = work;
	}

}
