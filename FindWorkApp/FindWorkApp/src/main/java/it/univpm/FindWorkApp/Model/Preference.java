package it.univpm.FindWorkApp.Model;

/**
 * Classe che rappresenta gli oggetti di tipo <b>Preference</b>.
 *
 * @author Paci Matteo
 * @author Ciucciov&eacute; Leonardo
 */
public class Preference {
	/**
	 * <p>
	 * la classe &eacute; implementata come singleton semplice. Si creer&aacute; una
	 * singola instanza che verr&aacute; poi usata dagli altri metodi per tutta
	 * l'esecuzione del programma.
	 */
	private String[] preference;
	private static Preference instance = null;

	private Preference() {
	}

	/**
	 * Metodo necessario a creare il singleton. Se instance è null creerà l'oggetto,
	 * in caso contrario restituirà il riferimento all'oggetto
	 * 
	 * @return instance riferimento al singolo oggetto crato.
	 */
	public static Preference getInstance() {
		if (instance == null) {
			instance = new Preference();
			String[] p = { "London", "Berlin", "New York", "Amsterdam", "Paris" };
			instance.setPreference(p);
		}
		return instance;
	}

	/**
	 * Metodo per ottenere <b>preference</b>.
	 * 
	 * @return preference array di nomi di citt&aacute; di preferenza.
	 */

	public String[] getPreference() {
		return preference;
	}

	/**
	 * Metodo per ottenere <b>preference</b>.
	 * 
	 * @param preference array di nomi di citt&aacute; di preferenza.
	 */
	public void setPreference(String[] preference) {
		this.preference = preference;
	}

}
