package it.univpm.FindWorkApp.Exception;

/**
 * <p>
 * Questa classe è un estensione della classe <b>Exception</b>. Viene generata
 * quando si inseriscono più di 5 città nella rotta delle statistiche.
 * 
 * @author Paci Matteo
 * @author Ciucciovè Leonardo
 *
 */
public class OverflowCityException extends Exception {
	private static final long serialVersionUID = 4;

	public OverflowCityException() {
		super("Sono state inserite troppe città di ricerca");
	}
}
