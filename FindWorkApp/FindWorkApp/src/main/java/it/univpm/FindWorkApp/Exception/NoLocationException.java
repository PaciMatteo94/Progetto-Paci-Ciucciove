package it.univpm.FindWorkApp.Exception;

public class NoLocationException extends Exception {
	private static final long serialVersionUID = 2;
	public NoLocationException() {
		super("Non è stata inserita nessuna locazione di ricerca");
	}
}
