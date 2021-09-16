package it.univpm.FindWorkApp.Model;

/**
 * Classe che rappresenta gli oggetti di tipo <b>CityStats</b>.
 * 
 * @author Paci Matteo
 * @author Ciucciov&eacute; Leonardo
 */
public class CityStats {
	private static String[] commonLanguages = { "python", "php", "spring", "typescript", "sql" };
	private int fullTimeAmount;
	private int partTimeAmount;
	private int notSpecifiedAmount;
	private double fullTimePerc;
	private double partTimePerc;
	private double notSpecifiedPerc;
	private double percPython;
	private double percPhp;
	private double percSpring;
	private double percTypescript;
	private double percSql;

	/**
	 * Metodo per ottenere <b>notSpecifiedAmount</b>.
	 * 
	 * @return notSpecifiedAmount indica i lavori in una citt&aacute; che non hanno
	 *         specificato la tipologia di lavoro full time o part time.
	 */
	public int getNotSpecifiedAmount() {
		return notSpecifiedAmount;
	}

	/**
	 * Metodo per settare <b>notSpecifiedAmount</b>.
	 * 
	 * @param notSpecifiedAmount indica i lavori in una citt&aacute; che non hanno
	 *                           specificato la tipologia di lavoro full time o part
	 *                           time.
	 */
	public void setNotSpecifiedAmount(int notSpecifiedAmount) {
		this.notSpecifiedAmount = notSpecifiedAmount;
	}

	/**
	 * Metodo per ottenere <b>notSpecifiedPerc</b>.
	 * 
	 * @return notSpecifiedPerc indica la percentuale di lavori in una citt&aacute;
	 *         che non hanno specificato la tipologia di lavoro full time o part
	 *         time.
	 */
	public double getNotSpecifiedPerc() {
		return notSpecifiedPerc;
	}

	/**
	 * Metodo per settare <b>notSpecifiedPerc</b>.
	 * 
	 * @param notSpecifiedPerc indica la percentuale di lavori in una citt&aacute;
	 *                         che non hanno specificato la tipologia di lavoro full
	 *                         time o part time.
	 */
	public void setNotSpecifiedPerc(double notSpecifiedPerc) {
		this.notSpecifiedPerc = notSpecifiedPerc;
	}

	/**
	 * Metodo per ottenere <b>commonLanguages</b>.
	 * 
	 * @return commonLanguanges array dei linguaggi su cui si calcolano le
	 *         statistiche.
	 */
	public String[] getCommonLanguages() {
		return commonLanguages;
	}

	/**
	 * Metodo per ottenere <b>fullTimeAmount</b>.
	 * 
	 * @return fullTimeAmount numero di lavori che sono full time.
	 */
	public int getFullTimeAmount() {
		return fullTimeAmount;
	}

	/**
	 * Metodo per settare <b>fullTimeAmount</b>.
	 * 
	 * @param fullTimeAmount numero di lavori che sono full time.
	 */
	public void setFullTimeAmount(int fullTimeAmount) {
		this.fullTimeAmount = fullTimeAmount;
	}

	/**
	 * Metodo per ottenere <b>partTimeAmount</b>.
	 * 
	 * @return partTimeAmount numero di lavori che sono part time.
	 */
	public int getPartTimeAmount() {
		return partTimeAmount;
	}

	/**
	 * Metodo per settare <b>partTimeAmount</b>.
	 * 
	 * @param partTimeAmount numero di lavori che sono part time.
	 */
	public void setPartTimeAmount(int partTimeAmount) {
		this.partTimeAmount = partTimeAmount;
	}

	/**
	 * Metodo per ottenere <b>fullTimePerc</b>.
	 * 
	 * @return fullTimePerc percentuale dei lavori full time.
	 */
	public double getFullTimePerc() {
		return fullTimePerc;
	}

	/**
	 * Metodo per settare <b>fullTimePerc</b>.
	 * 
	 * @param fullTimePerc percentuale dei lavori full time.
	 */
	public void setFullTimePerc(double fullTimePerc) {
		this.fullTimePerc = fullTimePerc;
	}

	/**
	 * Metodo per ottenere <b>partTimePerc</b>.
	 * 
	 * @return partTimePerc percentuale dei lavori part time.
	 */
	public double getPartTimePerc() {
		return partTimePerc;
	}

	/**
	 * Metodo per settare <b>partTimePerc</b>.
	 * 
	 * @param partTimePerc percentuale dei lavori part time.
	 */
	public void setPartTimePerc(double partTimePerc) {
		this.partTimePerc = partTimePerc;
	}

	/**
	 * Metodo per ottenere <b>percPython</b>.
	 * 
	 * @return percPython percentuale dei lavori che richiedono anche la conoscenza
	 *         di Python come linguaggio.
	 */
	public double getPercPython() {
		return percPython;
	}

	/**
	 * Metodo per settare <b>percPython</b>.
	 * 
	 * @param percPython percentuale dei lavori che richiedono anche la conoscenza
	 *                   di Python come linguaggio.
	 */
	public void setPercPython(double percPython) {
		this.percPython = percPython;
	}

	/**
	 * Metodo per ottenere <b>percPhp</b>.
	 * 
	 * @return percPhp percentuale dei lavori che richiedono anche la conoscenza di
	 *         Php come linguaggio.
	 */
	public double getPercPhp() {
		return percPhp;
	}

	/**
	 * Metodo per settare <b>percPhp</b>.
	 * 
	 * @param perctPhp percentuale dei lavori che richiedono anche la conoscenza di
	 *                 Php come linguaggio.
	 */
	public void setPercPhp(double percPhp) {
		this.percPhp = percPhp;
	}

	/**
	 * Metodo per ottenere <b>percSpring</b>.
	 * 
	 * @return percSpring percentuale dei lavori che richiedono anche la conoscenza
	 *         di Spring come linguaggio.
	 */
	public double getPercSpring() {
		return percSpring;
	}

	/**
	 * Metodo per settare <b>percSpring</b>.
	 * 
	 * @param percSpring percentuale dei lavori che richiedono anche la conoscenza
	 *                   di Spring come linguaggio.
	 */
	public void setPercSpring(double percSpring) {
		this.percSpring = percSpring;
	}

	/**
	 * Metodo per ottenere <b>percTypescript</b>.
	 * 
	 * @return percTypescript percentuale dei lavori che richiedono anche la
	 *         conoscenza di Typescript come linguaggio.
	 */
	public double getPercTypescript() {
		return percTypescript;
	}

	/**
	 * Metodo per settare <b>percTypescript</b>.
	 * 
	 * @param percTypescript percentuale dei lavori che richiedono anche la
	 *                       conoscenza di Typescript come linguaggio.
	 */
	public void setPercTypescript(double percTypescript) {
		this.percTypescript = percTypescript;
	}

	/**
	 * Metodo per ottenere <b>percSql</b>.
	 * 
	 * @return percSql percentuale dei lavori che richiedono anche la conoscenza di
	 *         Sql come linguaggio.
	 */
	public double getPercSql() {
		return percSql;
	}

	/**
	 * Metodo per settare <b>percSql</b>.
	 * 
	 * @param percSql percentuale dei lavori che richiedono anche la conoscenza di
	 *                Sql come linguaggio.
	 */
	public void setPercSql(double percSql) {
		this.percSql = percSql;
	}

}
