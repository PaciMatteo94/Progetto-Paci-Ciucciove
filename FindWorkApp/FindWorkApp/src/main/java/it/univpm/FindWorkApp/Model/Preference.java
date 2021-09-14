package it.univpm.FindWorkApp.Model;

/**
 * Classe che rappresenta gli oggetti di tipo <b>Preference</b>.
 *
 * @author Paci Matteo
 * @author Ciucciovè Leonardo
 */
public class Preference {
	private String[] preference;
	private static Preference instance = null;

	private Preference() {
	}

	public static Preference getInstance() {
		if (instance == null) {
			instance = new Preference();
			String[] p = { "London", "Berlin", "New York", "Amsterdam", "Paris" };
			instance.setPreference(p);
		}
		return instance;
	}

	public String[] getPreference() {
		return preference;
	}

	public void setPreference(String[] preference) {
		this.preference = preference;
	}

}
