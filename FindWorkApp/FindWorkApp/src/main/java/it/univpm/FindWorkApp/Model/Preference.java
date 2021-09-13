package it.univpm.FindWorkApp.Model;
/**
 * Classe che rappresenta gli oggetti di tipo <b>Preference</b>.
 *
 * @author Paci Matteo
 * @author Ciucciovè Leonardo
 */
public class Preference {
	private String[] preference = { "London", "Berlin", "New York", "Amsterdam", "Paris" };


	public String[] getPreference() {
		return preference;
	}

	public void setPreference(String[] preference) {
		this.preference = preference;
	}

}
