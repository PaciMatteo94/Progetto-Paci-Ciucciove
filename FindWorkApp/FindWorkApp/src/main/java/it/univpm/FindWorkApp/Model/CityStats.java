package it.univpm.FindWorkApp.Model;

/**
 * Classe che rappresenta gli oggetti di tipo <b>CityStats</b>.
 * 
 * @author Paci Matteo
 * @author Ciucciovè Leonardo
 */
public class CityStats {
	private static String[] commonLanguages = { "python", "php", "spring", "typescript", "sql" };
	private int empNullAmount;
	private int fullTimeAmount;
	private int partTimeAmount;
	private double fullTimePerc;
	private double partTimePerc;
	private double percPyton;
	private double percPhp;
	private double percSpring;
	private double percTypescript;
	private double percSql;

	public String[] getCommonLanguages() {
		return commonLanguages;
	}

	public static void setCommonLanguages(String[] commonLanguages) {
		CityStats.commonLanguages = commonLanguages;
	}

	public int getEmpNullAmount() {
		return empNullAmount;
	}

	public void setEmpNullAmount(int empNullAmount) {
		this.empNullAmount = empNullAmount;
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

	public double getPercPyton() {
		return percPyton;
	}

	public void setPercPyton(double percPyton) {
		this.percPyton = percPyton;
	}

	public double getPercPhp() {
		return percPhp;
	}

	public void setPercPhp(double percPhp) {
		this.percPhp = percPhp;
	}

	public double getPercSpring() {
		return percSpring;
	}

	public void setPercSpring(double percSpring) {
		this.percSpring = percSpring;
	}

	public double getPercTypescript() {
		return percTypescript;
	}

	public void setPercTypescript(double percTypescript) {
		this.percTypescript = percTypescript;
	}

	public double getPercSql() {
		return percSql;
	}

	public void setPercSql(double percSql) {
		this.percSql = percSql;
	}

}
