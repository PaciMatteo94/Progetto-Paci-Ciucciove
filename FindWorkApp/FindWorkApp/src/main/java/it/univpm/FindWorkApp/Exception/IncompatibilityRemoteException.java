package it.univpm.FindWorkApp.Exception;

public class IncompatibilityRemoteException extends Exception{
	private static final long serialVersionUID = 6;
	public IncompatibilityRemoteException() {
		super("Le città salvate in memoria presentano tutte valore remote diverso da quello inserito per il filtraggio delle statistiche");
		
	}
	

}
