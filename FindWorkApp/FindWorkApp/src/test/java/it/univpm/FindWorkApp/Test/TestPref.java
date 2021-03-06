package it.univpm.FindWorkApp.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.univpm.FindWorkApp.Model.Preference;

/**
 * *
 * <p>
 * La classe &eacute; generata per testare se le citt&aacute; restituite all richiesta della
 * rotta /preference (GET) in questo metodo dell'APICallController, sono le
 * stesse contenute nell'array della classe 'Preference'
 * </p>
 *
 * @author Paci Matteo
 * @author Ciucciov&eacute; Leonardo
 *
 */

class TestPref {
	private Preference p;
	private String[] pref = { "London", "Berlin", "New York", "Amsterdam", "Paris" };

	@BeforeEach
	void setUp() throws Exception {
		p = Preference.getInstance();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	/**
	 * test dell' dell'eccezione che confronta se le citt&aacute; restituite dal metodo
	 * sono effettivamente le stesse contenute nell'array istanziato dalla classe
	 * 'Preference'
	 *
	 * @see it.univpm.FindWorkApp.Controller.APICallController#preference()
	 */

	@Test
	void test() {
		for (int i = 0; i < pref.length; i++) {
			assertEquals(pref[i], p.getPreference()[i]);
		}
	}

}
