package it.univpm.FindWorkApp.Exception;

/**
 * <p>
 * L'eccezione <b>EmptyBodyException</b> estende <b>Exception</b>. Questa viene
 * generata quando l'utente alla rotta /preference (POST) non inserisce nulla
 * nel body della richiesta
 * </p>
 *
 * @author Paci Matteo
 * @author Ciucciov&eacute; Leonardo
 *
 */

public class EmptyBodyException extends Exception {
	private static final long serialVersionUID = 1;

	/**
	 * Costruttore della classe <b>EmptyBodyException</b>
	 */

	public EmptyBodyException() {
		super("Non hai inserito nulla nel Body");
	}
}
