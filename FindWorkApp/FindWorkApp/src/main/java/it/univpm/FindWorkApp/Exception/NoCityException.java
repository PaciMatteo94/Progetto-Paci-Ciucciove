package it.univpm.FindWorkApp.Exception;

public class NoCityException extends Exception{
	private static final long serialVersionUID = 4;
	public NoCityException() {
		super("Non ci sono salvate città in memoria.");
	}

}
