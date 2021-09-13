package it.univpm.FindWorkApp.Model;

import java.util.ArrayList;

/**
 * Classe che rappresenta gli oggetti di tipo <b>City</b>.
 * 
 * @author Paci Matteo
 * @author Ciucciovè Leonardo
 *
 */
public class City {
	private String location;
	private Long count;
	private CityStats cityStats;
	private ArrayList<WorkInformation> work;

	public City(String location) {
		this.location = location;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public CityStats getCityStats() {
		return cityStats;
	}

	public void setCityStats(CityStats cityStats) {
		this.cityStats = cityStats;
	}

	public ArrayList<WorkInformation> getWork() {
		return work;
	}

	public void setWork(ArrayList<WorkInformation> work) {
		this.work = work;
	}

}
