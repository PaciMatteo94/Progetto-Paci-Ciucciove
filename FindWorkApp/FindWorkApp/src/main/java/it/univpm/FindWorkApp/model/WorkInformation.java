package it.univpm.FindWorkApp.model;

import java.util.ArrayList;
//non è sicuro che sia necessaria
public class WorkInformation extends City{
	private String role;
	private String companyName;
	private String employementType;
	private boolean remote;
	private ArrayList<String> keywords;
	private String dataPosted;
	private String text;
	public WorkInformation (String location) {
		super(location);
	}
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
	public boolean isRemote() {
		return remote;
	}
	public void setRemote(boolean remote) {
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
