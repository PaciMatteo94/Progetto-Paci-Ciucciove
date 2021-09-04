package it.univpm.FindWorkApp.Exception;

public class OverflowCityException extends Exception {
	private static final long serialVersionUID = 1;
	public OverflowCityException() {
		super("Sono state inserite troppe città di ricerca");
	}
}
