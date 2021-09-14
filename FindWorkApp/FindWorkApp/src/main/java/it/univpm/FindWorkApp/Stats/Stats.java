package it.univpm.FindWorkApp.Stats;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.apache.commons.math3.util.Precision;
import it.univpm.FindWorkApp.Model.City;
import it.univpm.FindWorkApp.Model.CityStats;
import it.univpm.FindWorkApp.Model.WorkInformation;

/**
 * <p>
 * La Classe <b>Stats</b> implementa l'interfaccia <b>StatsService</b>.
 * 
 * @author Paci Matteo
 * @author Ciucciovè Leonardo
 *
 */
public class Stats implements StatsService {
	/**
	 * <p>
	 * la classe è implementata come singleton semplice. Si creerà una singola
	 * instanza che verrà poi usata dagli altri metodi per tutta l'esecuzione del
	 * programma.
	 */
	private static Stats instance = null;

	private Stats() {
	}

	public static Stats getInstance() {
		if (instance == null) {
			instance = new Stats();

		}
		return instance;
	}

	/**
	 * <p>
	 * Questo metodo inizializza contatori (cnt) per il conteggio di: lavori
	 * part-time/contratto, full-time o dove non è specificato quanti di essi
	 * contengono i linguaggi predefiniti del python, php, spring, typescript, sql
	 *
	 * calcola poi le percentuali di tutti questi dati (le percentuali dei linguaggi
	 * sono sul totoale)
	 *
	 * @param city contiene i lavori relativi ad una città
	 * @return city, un oggetto di tipo City che contiene tutte le informazioni dei
	 *         lavori a cui sono appena stati riempiti i campi relativi alle
	 *         statistiche
	 */
	public City statsCalculate(City city) {
		CityStats stats = new CityStats();
		int cnt = 0, cnt2 = 0, cnt3 = 0, cnt4 = 0, cnt5 = 0;
		int pythonCnt = 0, phpCnt = 0, springCnt = 0, typescriptCnt = 0, sqlCnt = 0;
		for (WorkInformation w : city.getWork()) {
			if (w.getEmployementType() == null) {
				if (w.getText().contains("full-time") || w.getText().contains("full time")) {
					cnt3++;
				} else if (w.getText().contains("part-time") || w.getText().contains("part time")) {
					cnt4++;
				} else {
					cnt5++;
				}
			} else {
				if (w.getEmployementType().equals("full time")) {
					cnt++;
				} else if (w.getEmployementType().equals("part time") || w.getEmployementType().equals("contract")) {
					cnt2++;
				}
			}
			if (w.getKeywords() != null) {
				for (String s : w.getKeywords()) {
					for (int i = 0; i < stats.getCommonLanguages().length; i++) {
						switch (i) {
						case 0:
							if (s.equals(stats.getCommonLanguages()[i]))
								pythonCnt++;
							break;
						case 1:
							if (s.equals(stats.getCommonLanguages()[i]))
								phpCnt++;
							break;
						case 2:
							if (s.equals(stats.getCommonLanguages()[i]))
								springCnt++;
							break;
						case 3:
							if (s.equals(stats.getCommonLanguages()[i]))
								typescriptCnt++;
							break;
						case 4:
							if (s.equals(stats.getCommonLanguages()[i]))
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
		stats.setPercPython(percPython);
		stats.setPercPhp(percPhp);
		stats.setPercSpring(percSpring);
		stats.setPercTypescript(percTypescript);
		stats.setPercSql(percSql);

		cnt = cnt + cnt3;
		cnt2 = cnt2 + cnt4;
		stats.setFullTimeAmount(cnt);
		stats.setPartTimeAmount(cnt2);
		stats.setNotSpecifiedAmount(cnt5);
		double pFTA = (double) cnt / city.getWork().size() * 100;
		double pPTA = (double) cnt2 / city.getWork().size() * 100;
		double pNTA = (double) cnt5 / city.getWork().size() * 100;
		double percFTA = Precision.round(pFTA, 2);
		double percPTA = Precision.round(pPTA, 2);
		double percNTA = Precision.round(pNTA, 2);
		stats.setFullTimePerc(percFTA);
		stats.setPartTimePerc(percPTA);
		stats.setNotSpecifiedPerc(percNTA);
		city.setCityStats(stats);
		return city;
	}

	/**
	 * Il metodo <b>statsFiltered</b> ha l'obbiettivo di calcolare le statistiche
	 * filtrate per data. Converte le date in un formato comune per fare un
	 * confronto tra di loro per capire quali lavori soddisfano il filtro inserito e
	 * li salva in una lista. Successivamente questa lista viente salvata in un
	 * oggetto di tipo <b>City</b> che viene passato al metoto <b>statsCalculate</b>
	 * che calcola le statistiche dei lavori della città.
	 *
	 * @param city oggetto di tipo City.
	 * @param date filtro inserito dell'utente con cui calcolare le statistiche.
	 * @return <code>City</code> oggetto dove sono salvate le informazioni e le
	 *         statistiche della città.
	 */
	public City statsFiltered(City city, String date) {
		ArrayList<WorkInformation> tempWork = new ArrayList<WorkInformation>();
		DateTimeFormatter userFormat = DateTimeFormatter.ofPattern("uuuu-MM-dd");
		DateTimeFormatter apiFormat = DateTimeFormatter.ofPattern("uuuu-MM-dd'T'HH:mm:ss'Z'");
		LocalDate start = LocalDate.parse(date, userFormat);
		for (WorkInformation work : city.getWork()) {
			LocalDate workDate = LocalDate.parse(work.getDataPosted(), apiFormat);
			if (start.isBefore(workDate) || start.equals(workDate)) {
				tempWork.add(work);
			}
		}
		city.setCount((long) tempWork.size());
		city.setWork(tempWork);
		statsCalculate(city);
		return city;
	}

}
