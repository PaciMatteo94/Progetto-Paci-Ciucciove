package it.univpm.FindWorkApp.Exception;

/**
 * <p>
 * Questa classe &eacute; un estensione della classe <b>Exception</b>. Viene generata
 * quando con la ricerca non si trovano lavori che soddisfano le richieste
 * dell'utente.
 * 
 * @author Paci Matteo
 * @author Ciucciov&eacute; Leonardo
 *
 */
public class NoResultsException extends Exception {
	private static final long serialVersionUID = 3;
	/**
	 * <p>
	 * Costruttore della classe<b>NoResultsException</b>.
	 */
	public NoResultsException() {
		super("Non sono stati trovati risultati con tali parametri");
	}

}
