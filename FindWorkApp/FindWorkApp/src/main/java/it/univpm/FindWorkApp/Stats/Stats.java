package it.univpm.FindWorkApp.Stats;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.apache.commons.math3.util.Precision;
import it.univpm.FindWorkApp.Model.City;
import it.univpm.FindWorkApp.Model.StatsProgrammingLenguage;
import it.univpm.FindWorkApp.Model.WorkInformation;


public class Stats {
/**
 * <p>
 * Questo metodo inizializza contatori (cnt) per il conteggio di:
 * lavori part-time/contratto, full-time o dove non è specificato 
 * quanti di essi contengono i linguaggi predefiniti del python, php, spring, typescript, sql
 * 
 * calcola poi le percentuali di tutti questi dati
 * (le percentuali dei linguaggi sono sul totoale)
 * 
 * @param city contiene i lavori relativi ad una città
 * @return city, un oggetto di tipo City che contiene tutte le informazioni dei lavori a cui sono appena stati riempiti i campi relativi alle statistiche
 */
	public static City statsCalculate(City city) {
		int cnt = 0, cnt2 = 0, cnt3=0, cnt4=0, cnt5=0;
		int pythonCnt = 0, phpCnt = 0, springCnt = 0, typescriptCnt = 0, sqlCnt = 0;
		for (WorkInformation w : city.getWork()) {
			if(w.getEmployementType()== null) {
				if (w.getText().contains("full-time") || w.getText().contains("full time")) {
					cnt3++;
				} else if (w.getText().contains("part-time") || w.getText().contains("part time")) {
					cnt4++;
				}
				else {
					cnt5++;
				}
			}
			else {
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
		
		cnt=cnt+cnt3;
		cnt2=cnt2+cnt4;
		city.setFullTimeAmount(cnt);
		city.setPartTimeAmount(cnt2);
		city.setNotSpecifiedAmount(cnt5);
		double pFTA = (double) cnt / city.getWork().size() * 100;
		double pPTA = (double) cnt2 / city.getWork().size() * 100;
		double pNTA = (double) cnt5 / city.getWork().size() * 100;
		double percFTA = Precision.round(pFTA, 2);
		double percPTA = Precision.round(pPTA, 2);
		double percNTA = Precision.round(pNTA, 2);
		city.setFullTimePerc(percFTA);
		city.setPartTimePerc(percPTA);
		city.setNotSpecifiedPerc(percNTA);
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
			LocalDate start = LocalDate.parse(date, userFormat);
			for (City city : cities) {
				tempWork = new ArrayList<WorkInformation>();
				tempCity = new City(city.getLocation());
				for (WorkInformation work : city.getWork()) {
					LocalDate workDate = LocalDate.parse(work.getDataPosted(), apiFormat);
					if(remote !=null) {
						if ((start.isBefore(workDate)|| start.equals(workDate)) && work.getRemote() == remote) {
							tempWork.add(work);
							
						}
					}else {
						if (start.isBefore(workDate) || start.equals(workDate)) {
							tempWork.add(work);
						}
					}

				}
				tempCity.setCount((long)tempWork.size());
				tempCity.setWork(tempWork);
				match.add(Stats.statsCalculate(tempCity));
			}

		}else if (remote != null) {
			for (City city : cities) {
				tempWork = new ArrayList<WorkInformation>();
				tempCity = new City(city.getLocation());
				for (WorkInformation work : city.getWork()) {
					if (work.getRemote() == remote) {
						tempWork.add(work);
					}
				}
				tempCity.setCount((long)tempWork.size());
				tempCity.setWork(tempWork);
				match.add(Stats.statsCalculate(tempCity));
			}
		}
		
		return match;
	}

}