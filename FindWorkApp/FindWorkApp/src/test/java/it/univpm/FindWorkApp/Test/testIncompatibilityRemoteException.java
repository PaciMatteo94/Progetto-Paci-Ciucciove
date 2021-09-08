package it.univpm.FindWorkApp.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.univpm.FindWorkApp.Exception.IncompatibilityRemoteException;
import it.univpm.FindWorkApp.Exception.NoCityException;
import it.univpm.FindWorkApp.Manager.Manager;

class testIncompatibilityRemoteException {
	IncompatibilityRemoteException e;
	Manager c1;
	String [] cities = {"Berlin"};

	@BeforeEach
	void setUp() throws Exception {
		c1 = Manager.getInstance();
		c1.getCities(cities, null, true);
		
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test() {
		e = assertThrows(IncompatibilityRemoteException.class, () -> {
			c1.getStats(null, null, false);
		});
		assertEquals("Le città salvate in memoria presentano tutte valore remote diverso da quello inserito per il filtraggio delle statistiche", e.getMessage());
	}

}
