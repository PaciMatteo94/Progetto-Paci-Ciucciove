package it.univpm.FindWorkApp.Exception;

/**
 * <p>
 * Questa classe è un estensione della classe <b>Exception</b>. Viene generata
 * quando si inserisce un valore inaspettato nel parametro employment_type nella
 * rotta cities.
 * 
 * @author Paci Matteo
 * @author Ciucciovè Leonardo
 *
 */
public class UnsupportedValueException extends Exception {
	private static final long serialVersionUID = 3;

	public UnsupportedValueException() {
		super("Il valore inserito in employment_type non è supportato");
	}
}
