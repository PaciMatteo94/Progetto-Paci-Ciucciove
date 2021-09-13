package it.univpm.FindWorkApp.Exception;

/**
 * <p>
 * Questa classe è un estensione della classe <b>Exception</b>. Viene generata
 * quando l'utente inserisce inserisce il parametro location ma lasciando vuoto
 * il suo valore.
 * 
 * @author Paci Matteo
 * @author Ciucciovè Leonardo
 *
 */
public class NoLocationException extends Exception {
	private static final long serialVersionUID = 2;

	public NoLocationException() {
		super("Non è stata inserita nessuna locazione di ricerca");
	}
}
