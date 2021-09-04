package it.univpm.FindWorkApp.Exception;

public class UnsupportedValueException extends Exception{
	private static final long serialVersionUID = 3;
	public UnsupportedValueException() {
		super("Il valore inserito non è supportato");
	}
}
