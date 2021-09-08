package it.univpm.FindWorkApp.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import it.univpm.FindWorkApp.Exception.*;
import it.univpm.FindWorkApp.Manager.Manager;

class testNoCityException {
	NoCityException e;
	Manager c1;

	@BeforeEach
	void setUp() throws Exception {
		c1 = Manager.getInstance();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void NoCityExceptionTest() {
		e = assertThrows(NoCityException.class, () -> {
			c1.getStats(null, null, null);
		});
		assertEquals("Non ci sono salvate città in memoria.", e.getMessage());
	}

}
