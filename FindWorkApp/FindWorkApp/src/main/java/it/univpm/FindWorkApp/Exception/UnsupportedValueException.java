package it.univpm.FindWorkApp.Exception;

/**
 * <p>
 * Questa classe &eacute; un estensione della classe <b>Exception</b>. Viene
 * generata quando si inserisce un valore inaspettato nel parametro
 * employment_type nella rotta cities.
 * 
 * 
 * @author Paci Matteo
 * @author Ciucciov&eacute; Leonardo
 *
 */
public class UnsupportedValueException extends Exception {
	private static final long serialVersionUID = 5;

	/**
	 * <p>
	 * Costruttore della classe<b>UnsupportedValueException</b>.
	 */
	public UnsupportedValueException() {
		super("Il valore inserito in employment_type non è supportato");
	}
}
