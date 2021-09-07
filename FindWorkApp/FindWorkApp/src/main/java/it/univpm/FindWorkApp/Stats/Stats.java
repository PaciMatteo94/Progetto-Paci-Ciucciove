package it.univpm.FindWorkApp.Stats;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.apache.commons.math3.util.Precision;
import it.univpm.FindWorkApp.Model.City;
import it.univpm.FindWorkApp.Model.StatsProgrammingLenguage;
import it.univpm.FindWorkApp.Model.WorkInformation;

public class Stats {

	public static City statsCalculate(City city) {
		int cnt = 0, cnt2 = 0;
		int pythonCnt = 0, phpCnt = 0, springCnt = 0, typescriptCnt = 0, sqlCnt = 0;
		for (WorkInformation w : city.getWork()) {
			if (w.getEmployementType() != null) {
				if (w.getEmployementType().equals("full time")) {
					cnt++;
				} else if (w.getEmployementType().equals("part time") || w.getEmployementType().equals("contract")) {
					cnt2++;
				}
			}
			if (w.getKeywords() != null) {
				for (String s : w.getKeywords()) {
					for (int i = 0; i < StatsProgrammingLenguage.getCommonLanguages().length; i++) {
						switch (i) {
						case 0:
							if (s.equals(StatsProgrammingLenguage.getCommonLanguages()[i]))
								pythonCnt++;
							break;
						case 1:
							if (s.equals(StatsProgrammingLenguage.getCommonLanguages()[i]))
								phpCnt++;
							break;
						case 2:
							if (s.equals(StatsProgrammingLenguage.getCommonLanguages()[i]))
								springCnt++;
							break;
						case 3:
							if (s.equals(StatsProgrammingLenguage.getCommonLanguages()[i]))
								typescriptCnt++;
							break;
						case 4:
							if (s.equals(StatsProgrammingLenguage.getCommonLanguages()[i]))
								sqlCnt++;
							break;
						}
					}

				}
			}
		}
		double pPython = (double) pythonCnt / city.getWork().size() * 100;
		double pPhp = (double) phpCnt / city.getWork().size() * 100;
		double pSpring = (double) springCnt / city.getWork().size() * 100;
		double pTypescript = (double) typescriptCnt / city.getWork().size() * 100;
		double pSql = (double) sqlCnt / city.getWork().size() * 100;
		double percPython = Precision.round(pPython, 2);
		double percPhp = Precision.round(pPhp, 2);
		double percSpring = Precision.round(pSpring, 2);
		double percTypescript = Precision.round(pTypescript, 2);
		double percSql = Precision.round(pSql, 2);
		StatsProgrammingLenguage calcs = new StatsProgrammingLenguage();
		calcs.setPercPython(percPython);
		calcs.setPercPhp(percPhp);
		calcs.setPercSpring(percSpring);
		calcs.setPercTypescript(percTypescript);
		calcs.setPercSql(percSql);

		city.setFullTimeAmount(cnt);
		city.setPartTimeAmount(cnt2);
		double pFTA = (double) cnt / city.getWork().size() * 100;
		double pPTA = (double) cnt2 / city.getWork().size() * 100;
		double percFTA = Precision.round(pFTA, 2);
		double percPTA = Precision.round(pPTA, 2);
		city.setFullTimePerc(percFTA);
		city.setPartTimePerc(percPTA);
		city.setLenguageStats(calcs);
		return city;
	}

	public static ArrayList<City> statsFiltered(ArrayList<City> cities, String date, Boolean remote) {
		ArrayList<City> match = new ArrayList<City>(cities.size());
		City tempCity;
		ArrayList<WorkInformation> tempWork;
		if (date != null) {
			DateTimeFormatter userFormat= DateTimeFormatter.ofPattern("uuuu-MM-dd");
			DateTimeFormatter apiFormat = DateTimeFormatter.ofPattern("uuuu-MM-dd'T'HH:mm:ss'Z'");
			LocalDateTime start = LocalDateTime.parse(date, userFormat);
			for (City city : cities) {
				tempWork = new ArrayList<WorkInformation>();
				tempCity = new City(city.getLocation());
				for (WorkInformation work : city.getWork()) {
					LocalDateTime workDate = LocalDateTime.parse(work.getDataPosted(), apiFormat);
					if(remote !=null) {
						if (start.isBefore(workDate) && work.getRemote() == remote) {
							tempWork.add(work);
						}
					}else {
						if (start.isBefore(workDate)) {
							tempWork.add(work);
						}
					}

				}
				tempCity.setWork(tempWork);
				match.add(Stats.statsCalculate(tempCity));
			}

		}
		if (remote != null) {
			for (City city : cities) {
				tempWork = new ArrayList<WorkInformation>();
				tempCity = new City(city.getLocation());
				for (WorkInformation work : city.getWork()) {
					if (work.getRemote() == remote) {
						tempWork.add(work);
					}
				}
				tempCity.setWork(tempWork);
				match.add(Stats.statsCalculate(tempCity));
			}
		}
		
		return match;
	}

}