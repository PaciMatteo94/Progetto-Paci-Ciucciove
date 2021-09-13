package it.univpm.FindWorkApp.Model;

import java.util.ArrayList;
/**
 * Classe che rappresenta gli oggetti di tipo <b>WorkInformation</b>.
 * 
 * @author Paci Matteo
 * @author Ciucciovè Leonardo
 */
public class WorkInformation {
	private String role;
	private String companyName;
	private String employementType;
	private Boolean remote;

	private ArrayList<String> keywords;
	private String dataPosted;
	private String text;

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getEmployementType() {
		return employementType;
	}

	public void setEmployementType(String employementType) {
		this.employementType = employementType;
	}

	public Boolean getRemote() {
		return remote;
	}

	public void setRemote(Boolean remote) {
		this.remote = remote;
	}

	public ArrayList<String> getKeywords() {
		return keywords;
	}

	public void setKeywords(ArrayList<String> keywords) {
		this.keywords = keywords;
	}

	public String getDataPosted() {
		return dataPosted;
	}

	public void setDataPosted(String dataPosted) {
		this.dataPosted = dataPosted;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
