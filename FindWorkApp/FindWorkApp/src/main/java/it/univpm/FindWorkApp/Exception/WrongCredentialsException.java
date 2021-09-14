package it.univpm.FindWorkApp.Exception;

/**
 * <p>
 * L'eccezione <b>WrongCredentialsException</b> estende <b>Exception</b>. Questa
 * viene generata quando l'utente alla rotta /preference (POST) inserisce nel
 * body delle credenziali che non corrispondono a quelle della risposta a tale
 * richiesta.
 * </p>
 *
 * @author Paci Matteo
 * @author Ciucciovè Leonardo
 * 
 */

public class WrongCredentialsException extends Exception {
	private static final long serialVersionUID = 6;

	/**
	 * Costruttore della classe <b>WrongCredentialsException</b>
	 */

	public WrongCredentialsException() {
		super("- Accesso negato -");
	}
}
