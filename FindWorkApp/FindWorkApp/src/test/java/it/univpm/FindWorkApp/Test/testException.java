package it.univpm.FindWorkApp.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.univpm.FindWorkApp.Controller.APICallController;
import it.univpm.FindWorkApp.Controller.StatsController;
import it.univpm.FindWorkApp.Exception.NoLocationException;
import it.univpm.FindWorkApp.Exception.OverflowCityException;
import it.univpm.FindWorkApp.Exception.UnsupportedValueException;

class testException {
	NoLocationException ecc1;
	OverflowCityException ecc2;
	UnsupportedValueException ecc3;
	APICallController c1;
	StatsController c2;

	@BeforeEach
	void setUp() throws Exception {
		c1 = new APICallController();
		c2 = new StatsController();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testNoLocationException() {
		ecc1 = assertThrows(NoLocationException.class, () -> {
			c1.cityFilter("", null, null);
		});
		assertEquals("Non è stata inserita nessuna locazione di ricerca", ecc1.getMessage());
	}

	@Test
	void testOverFlowCityException() {
		ecc2 = assertThrows(OverflowCityException.class, () -> {
			c2.statsFilter("Berlin&London&Paris&Rome&Amsterdarm&Paris&Moscow", null, null);
		});
		assertEquals("Sono state inserite troppe città di ricerca", ecc2.getMessage());
	}

	@Test
	void testUnsupportedValueException() {
		ecc3 = assertThrows(UnsupportedValueException.class, () -> {
			c1.cityFilter("Berlin", "error", null);
		});
		assertEquals("Il valore inserito in employment_type non è supportato", ecc3.getMessage());

	}

}
