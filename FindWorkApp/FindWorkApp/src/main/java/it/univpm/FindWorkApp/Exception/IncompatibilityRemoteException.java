package it.univpm.FindWorkApp.Exception;

public class IncompatibilityRemoteException extends Exception{

	public IncompatibilityRemoteException() {
		super("Le città salvate in memoria presentano tutte valore remote diverso da quello inserito per il filtraggio delle statistiche");
		
	}
	

}
