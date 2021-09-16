package it.univpm.FindWorkApp.Model;

import java.util.ArrayList;

/**
 * Classe che rappresenta gli oggetti di tipo <b>WorkInformation</b>.
 * 
 * @author Paci Matteo
 * @author Ciucciov&eacute; Leonardo
 */
public class WorkInformation {
	private String role;
	private String companyName;
	private String employmentType;
	private Boolean remote;
	private ArrayList<String> keywords;
	private String dataPosted;
	private String text;

	/**
	 * ritorna il ruolo del lavoro
	 * 
	 * @return una <code>String</code> con il nome del ruolo del lavoro corrente
	 */

	public String getRole() {
		return role;
	}

	/**
	 * permette di scegliere il ruolo
	 * 
	 * @param role indica il ruolo
	 */

	public void setRole(String role) {
		this.role = role;
	}

	/**
	 * ritorna il valore companyName
	 * 
	 * @return una <code>String</code> con il nome del companyName
	 * 
	 */

	public String getCompanyName() {
		return companyName;
	}

	/**
	 * permette di prendere il companyName
	 * 
	 * @param companyName indica il nome della compagnia che offre il lavoro
	 */

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	/**
	 * ritorna il valore employmentType
	 * 
	 * @return una <code>String</code> con le opzioni null, full time, part
	 *         time/contract
	 * 
	 */

	public String getEmploymentType() {
		return employmentType;
	}

	/**
	 * permette di scegliere il valore employmentType
	 * 
	 * @param employmentType rappresenta il valore null, full time, part
	 *                       time/contract
	 */

	public void setEmploymentType(String employmentType) {
		this.employmentType = employmentType;
	}

	/**
	 * ritorna il valore del campo remote
	 * 
	 * @return un <code>Boolean</code> con il valore true o false a seconda se il
	 *         lavoro si svolger&agrave; da remoto o meno
	 * 
	 */

	public Boolean getRemote() {
		return remote;
	}

	/**
	 * permette di settare quanto vale il campo remote
	 * 
	 * @param remote corrisponde al valore di lavoro da remoto o non
	 */

	public void setRemote(Boolean remote) {
		this.remote = remote;
	}

	/**
	 * ritorna il valore del campo keywords
	 * 
	 * @return un <code>ArrayList</code> con il valore delle parole chiave per quel
	 *         lavoro
	 * 
	 */

	public ArrayList<String> getKeywords() {
		return keywords;
	}

	/**
	 * permette di scegliere le parole chiave del lavoro corrente
	 * 
	 * @param keywords indica le parole chiave
	 */

	public void setKeywords(ArrayList<String> keywords) {
		this.keywords = keywords;
	}

	/**
	 * ritorna il valore del campo datePosted
	 * 
	 * @return una <code>String</code> con il valore della data
	 * 
	 */

	public String getDataPosted() {
		return dataPosted;
	}

	/**
	 * permette di scegliere il la data di pubblicazione
	 * 
	 * @param dataPosted indica la data di pubblicazione dell'annuncio
	 */

	public void setDataPosted(String dataPosted) {
		this.dataPosted = dataPosted;
	}

	/**
	 * ritorna il valore del campo description
	 * 
	 * @return una <code>String</code> con la descrizione del lavoro
	 * 
	 */

	public String getText() {
		return text;
	}

	/**
	 * permette di scegliere la descrizione
	 * 
	 * @param text indica la descrizione del lavoro corrente
	 */

	public void setText(String text) {
		this.text = text;
	}

}
