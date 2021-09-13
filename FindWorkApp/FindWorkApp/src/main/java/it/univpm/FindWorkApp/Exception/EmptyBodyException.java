package it.univpm.FindWorkApp.Exception;

public class EmptyBodyException extends Exception {
	private static final long serialVersionUID = 5;

	public EmptyBodyException() {
		super("Non hai inserito nulla del Body");
	}
}
