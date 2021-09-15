package it.univpm.FindWorkApp.Test;

import static org.junit.jupiter.api.Assertions.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.easymock.EasyMock;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import it.univpm.FindWorkApp.Controller.APICallController;
import it.univpm.FindWorkApp.Controller.StatsController;
import it.univpm.FindWorkApp.Exception.EmptyBodyException;
import it.univpm.FindWorkApp.Exception.NoLocationException;
import it.univpm.FindWorkApp.Exception.OverflowCityException;
import it.univpm.FindWorkApp.Exception.UnsupportedValueException;

/**
 * La classe <b>TestException</b> testa alcune delle eccezioni presenti
 * nell'applicazione.
 * 
 * @author Paci Matteo
 * @author Ciucciovè Leonardo
 */

class TestException {

	HttpServletRequest mockRequest = EasyMock.createMock(HttpServletRequest.class);
	HttpServletResponse mockResponse = EasyMock.createMock(HttpServletResponse.class);
	HttpServletRequest httpServletRequest = new MockHttpServletRequest();
	HttpServletResponse httpServletResponse = new MockHttpServletResponse();
	private NoLocationException ecc1;
	private OverflowCityException ecc2;
	private UnsupportedValueException ecc3;
	private EmptyBodyException ecc4;
	private APICallController c1;
	private StatsController c2;

	@BeforeEach
	void setUp() throws Exception {
		c1 = new APICallController();
		c2 = new StatsController();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	/**
	 * Test dell'eccezione <b>testNoLocationException</b> prodotta dal metodo
	 * <b>cityFilter</b>
	 */
	@Test
	void testNoLocationException() {
		ecc1 = assertThrows(NoLocationException.class, () -> {
			c1.cityFilter("", null, null);
		});
		assertEquals("Non è stata inserita nessuna locazione di ricerca", ecc1.getMessage());
	}

	/**
	 * Test dell'eccezione<b>testOverFlowCityException</b>prodotta dal
	 * metodo<b>statsFilter</b>
	 */
	@Test
	void testOverFlowCityException() {
		ecc2 = assertThrows(OverflowCityException.class, () -> {
			c2.statsFilter("Berlin&London&Paris&Rome&Amsterdarm&Paris&Moscow", null, null);
		});
		assertEquals("Sono state inserite troppe città di ricerca", ecc2.getMessage());
	}

	/**
	 * Test dell'eccezione<b>testUnsupportedValueException</b>prodotta dal
	 * metodo<b>cityFilter</b>
	 */
	@Test
	void testUnsupportedValueException() {
		ecc3 = assertThrows(UnsupportedValueException.class, () -> {
			c1.cityFilter("Berlin", "error", null);
		});
		assertEquals("Il valore inserito in employment_type non è supportato", ecc3.getMessage());

	}

	/**
	 * Test dell'eccezione<b>testEmptyBodyException</b>prodotta dal
	 * metodo<b>suggested</b>
	 */
	@Test
	void testEmptyBodyException() {
		ecc4 = assertThrows(EmptyBodyException.class, () -> {
			c1.suggested(null, httpServletResponse, httpServletRequest, "");
		});

		String expectedMessage = "Non hai inserito nulla nel Body";
		String actualMessage = ecc4.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}

}
