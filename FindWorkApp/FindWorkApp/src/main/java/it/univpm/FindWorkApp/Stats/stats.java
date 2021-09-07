package it.univpm.FindWorkApp.Stats;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.apache.commons.math3.util.Precision;
import it.univpm.FindWorkApp.Model.City;
import it.univpm.FindWorkApp.Model.WorkInformation;

public class stats {

	public static City statsCalculate(City city) {
		int cnt = 0, cnt2 = 0;
		for (WorkInformation w : city.getWork()) {
			if (w.getEmployementType() != null) {
				if (w.getEmployementType().equals("full time")) {
					cnt++;
				} else if (w.getEmployementType().equals("contract")) {
					cnt2++;
				}
			}
		}
		city.setFullTimeAmount(cnt);
		city.setPartTimeAmount(cnt2);

		double pFTA = (double) cnt / city.getWork().size() * 100;
		double pPTA = (double) cnt2 / city.getWork().size() * 100;
		double percFTA = Precision.round(pFTA, 2);
		double percPTA = Precision.round(pPTA, 2);
		city.setFullTimePerc(percFTA);
		city.setPartTimePerc(percPTA);
		return city;
	}

	public static ArrayList<City> statsFiltered(ArrayList<City> cities, String date) {
		DateTimeFormatter f = DateTimeFormatter.ofPattern("uuuu-MM-dd'T'HH:mm:ss'Z'");
		LocalDateTime start = LocalDateTime.parse(date, f);
		City tempCity;
		ArrayList<WorkInformation> tempWork;
		ArrayList<City> match = new ArrayList<City>(cities.size());
		for (City city : cities) {
			tempWork = new ArrayList<WorkInformation>();
			tempCity = new City(city.getLocation());
			for (WorkInformation work : city.getWork()) {
				LocalDateTime workDate = LocalDateTime.parse(work.getDataPosted(), f);
				if (start.isBefore(workDate)) {
					tempWork.add(work);
				}
			}
			tempCity.setWork(tempWork);
			match.add(stats.statsCalculate(tempCity));
		}
		return match;
	}

	public static ArrayList<City> statsFiltered(ArrayList<City> cities, String date, boolean remote) {
		ArrayList<City> match = new ArrayList<City>(cities.size());
		City tempCity;
		ArrayList<WorkInformation> tempWork;
		if (date != null) {
			DateTimeFormatter f = DateTimeFormatter.ofPattern("uuuu-MM-dd'T'HH:mm:ss'Z'");
			LocalDateTime start = LocalDateTime.parse(date, f);
			for (City city : cities) {
				tempWork = new ArrayList<WorkInformation>();
				tempCity = new City(city.getLocation());
				for (WorkInformation work : city.getWork()) {
					LocalDateTime workDate = LocalDateTime.parse(work.getDataPosted(), f);
					if (start.isBefore(workDate) && work.isRemote() == remote) {
						tempWork.add(work);
					}
				}
				tempCity.setWork(tempWork);
				match.add(stats.statsCalculate(tempCity));
			}
			for (City city : cities) {
				tempWork = new ArrayList<WorkInformation>();
				tempCity = new City(city.getLocation());
				for (WorkInformation work : city.getWork()) {
					if (work.isRemote() == remote) {
						tempWork.add(work);
					}
				}
				tempCity.setWork(tempWork);
				match.add(stats.statsCalculate(tempCity));
			}
		}
		return match;
	}

}