package it.univpm.FindWorkApp.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import it.univpm.FindWorkApp.APICall.APICall;
import it.univpm.FindWorkApp.Model.City;

/**
 * La classe<b>TestAPICall</b> testa che la località che viene salvata
 * nell'oggetto di tipo <b>City</b> coincida con la località di ricerca
 * dell'API.
 * 
 * @author Paci Matteo
 * @author Ciucciovè Leonardo
 */
class TestAPICall {
	private APICall testApi;
	private City testCity;

	@BeforeEach
	void setUp() throws Exception {
		testApi = APICall.getInstance();
		testApi.setAPICall("Berlin", true);
		testCity = testApi.getData();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	@DisplayName("La località dell'oggetto coincide con la località di ricerca")
	void testLocationCity() {
		assertEquals("Berlin", testCity.getLocation());
	}
}
