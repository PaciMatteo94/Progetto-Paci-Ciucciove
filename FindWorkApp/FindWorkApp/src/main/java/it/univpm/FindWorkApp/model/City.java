package it.univpm.FindWorkApp.model;

import org.json.simple.JSONArray;

public class City {
	private String location;
	private int fullTimeAmount;
	private int partTimeAmount;
	private double fullTimePerc;
	private double partTimePerc;
	private StatsProgrammingLenguage lenguageStats;
	private JSONArray work;
	
	public StatsProgrammingLenguage getLenguageStats() {
		return lenguageStats;
	}

	public void setLenguageStats(StatsProgrammingLenguage lenguageStats) {
		this.lenguageStats = lenguageStats;
	}

	public JSONArray getWorkArray() {
		return WorkArray;
	}

	public void setWorkArray(JSONArray workArray) {
		WorkArray = workArray;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	private JSONArray WorkArray;
	public int getFullTimeAmount() {
		return fullTimeAmount;
	}

	public void setFullTimeAmount(int fullTimeAmount) {
		this.fullTimeAmount = fullTimeAmount;
	}

	public int getPartTimeAmount() {
		return partTimeAmount;
	}

	public void setPartTimeAmount(int partTimeAmount) {
		this.partTimeAmount = partTimeAmount;
	}

	public double getFullTimePerc() {
		return fullTimePerc;
	}

	public void setFullTimePerc(double fullTimePerc) {
		this.fullTimePerc = fullTimePerc;
	}

	public double getPartTimePerc() {
		return partTimePerc;
	}

	public void setPartTimePerc(double partTimePerc) {
		this.partTimePerc = partTimePerc;
	}

	public  City(String location) {
		this.location = location;
	}

	public String getLocation() {
		return location;
	}


}