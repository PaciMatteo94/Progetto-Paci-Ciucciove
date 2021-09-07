package it.univpm.FindWorkApp.Model;

import java.util.ArrayList;

public class City {
	private String location;
	private Long count;
	

	private int fullTimeAmount;
	private int partTimeAmount;
	private double fullTimePerc;
	private double partTimePerc;
	private StatsProgrammingLenguage lenguageStats;
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

	public StatsProgrammingLenguage getLenguageStats() {
		return lenguageStats;
	}

	public void setLenguageStats(StatsProgrammingLenguage lenguageStats) {
		this.lenguageStats = lenguageStats;
	}

	public ArrayList<WorkInformation> getWork() {
		return work;
	}

	public void setWork(ArrayList<WorkInformation> work) {
		this.work = work;
	}
	



}
