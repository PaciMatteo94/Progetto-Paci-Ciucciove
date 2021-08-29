package it.univpm.FindWorkApp.Exception;

public class WrongURLException extends Exception {
	private static final long serialVersionUID = 1L;
	public WrongURLException() {
		super("é stato inserito un URL non corretto");
	}
	public WrongURLException(String message) {
		super(message);
	}
}
