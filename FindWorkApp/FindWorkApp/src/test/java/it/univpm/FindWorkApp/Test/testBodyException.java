package it.univpm.FindWorkApp.Test;

import static org.junit.jupiter.api.Assertions.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.univpm.FindWorkApp.Controller.APICallController;
import it.univpm.FindWorkApp.Exception.*;

import org.easymock.EasyMock;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

/**
 * *<p>
 *La classe è generata per testare l'eccezione 'Body Vuoto' nel metodo dell'APICallController 'suggested'
 *relativo alla rotta post di preferenze (POST).
 *Per farlo devo istanziare una 'HttpServletResponse response' qualsiasi, non nulla e allo stesso modo
 *una 'HttpServletRequest request', questo per far combaciare i metodi. 
 *Qui utilizzo EasyMock.
 *</p>
 *
 * @author Paci Matteo
 * @author Ciucciovè Leonardo
 *
 */

class testBodyException {
	EmptyBodyException exc;
	APICallController o;
	HttpServletRequest mockRequest = EasyMock.createMock(HttpServletRequest.class);
	HttpServletResponse mockResponse = EasyMock.createMock(HttpServletResponse.class);
	HttpServletRequest httpServletRequest = new MockHttpServletRequest();
	HttpServletResponse httpServletResponse = new MockHttpServletResponse();

	@BeforeEach
	void setUp() {
		o = new APICallController();
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	
	/**
	 * test dell' dell'eccezione cui viene passato il 'null', riferito al body
	 * e gli altri due parametri per completare il metodo corrispondente
	 * 
	 * @see it.univpm.FindWorkApp.Controller.APICallController#suggested()
	 */
	
	@Test
	void test(){
		exc = assertThrows(EmptyBodyException.class, () -> {
			o.suggested(null,httpServletResponse,httpServletRequest, "");
	    });

	    String expectedMessage = "Non hai inserito nulla nel Body";
	    String actualMessage = exc.getMessage();

	    assertTrue(actualMessage.contains(expectedMessage));
	}

}
