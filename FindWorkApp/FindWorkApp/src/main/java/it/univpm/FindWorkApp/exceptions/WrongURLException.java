package it.univpm.FindWorkApp.exceptions;

public class WrongURLException extends Exception {
public WrongURLException() {
	super("èstato inserito un url non corretto");
}
public WrongURLException(String message) {
	super (message);
}
}
