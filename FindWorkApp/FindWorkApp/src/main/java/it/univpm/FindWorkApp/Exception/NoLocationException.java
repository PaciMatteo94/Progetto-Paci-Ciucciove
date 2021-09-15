package it.univpm.FindWorkApp.Exception;

/**
 * <p>
 * Questa classe &eacute; un estensione della classe <b>Exception</b>. Viene generata
 * quando l'utente inserisce inserisce il parametro location ma lasciando vuoto
 * il suo valore.
 * 
 * @author Paci Matteo
 * @author Ciucciov&eacute; Leonardo
 *
 */
public class NoLocationException extends Exception {
	private static final long serialVersionUID = 2;
/**
 * <p>
 * Costruttore della classe <b>NoLocationException</b>.
 */
	public NoLocationException() {
		super("Non è stata inserita nessuna locazione di ricerca");
	}
}
