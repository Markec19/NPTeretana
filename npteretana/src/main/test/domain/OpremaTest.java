/**
 * 
 */
package domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * @author Luka
 *
 */
class OpremaTest {

	Oprema o;
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		o = new Oprema();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
		o = null;
	}

	/**
	 * Test method for {@link domain.Oprema#setId(long)}.
	 */
	@Test
	void testSetId() {
		o.setId(10);
		
		assertEquals(10, o.getId());
	}

	/**
	 * Test method for {@link domain.Oprema#setStanjeOpreme(java.lang.String)}.
	 */
	@Test
	void testSetStanjeOpreme() {
		o.setStanjeOpreme("Dobro");
		
		assertEquals("Dobro", o.getStanjeOpreme());
	}
	
	@Test
	void testSetStanjeOpremeNull() {
		Exception e = assertThrows(IllegalArgumentException.class,
				() -> o.setStanjeOpreme(null)  );
		
		assertEquals("Stanje opreme ne sme biti null ili prazan string", e.getMessage());
	}
	
	@Test
	void testSetStanjeOpremePrazan() {
		Exception e = assertThrows(IllegalArgumentException.class,
				() -> o.setStanjeOpreme("")  );
		
		assertEquals("Stanje opreme ne sme biti null ili prazan string", e.getMessage());
	}

	/**
	 * Test method for {@link domain.Oprema#setVrsta(domain.VrstaOpreme)}.
	 */
	@Test
	void testSetVrsta() {
		VrstaOpreme v = new VrstaOpreme();
		o.setVrsta(v);
		
		assertEquals(v, o.getVrsta());
	}
	
	@Test
	void testSetNalogNull() {
		Exception e = assertThrows(IllegalArgumentException.class,
				() -> o.setVrsta(null));
		
		assertEquals("Vrsta ne sme biti null", e.getMessage());
	}

	/**
	 * Test method for {@link domain.Oprema#setTeretana(domain.Teretana)}.
	 */
	@Test
	void testSetTeretana() {
		Teretana t = new Teretana();
		o.setTeretana(t);
		
		assertEquals(t, o.getTeretana());
	}
	
	@Test
	void testSetTeretanaNull() {
		Exception e = assertThrows(IllegalArgumentException.class,
				() -> o.setTeretana(null));
		
		assertEquals("Teretana ne sme biti null", e.getMessage());
	}

	/**
	 * Test method for {@link domain.Oprema#equals(java.lang.Object)}.
	 */
	@ParameterizedTest
	@CsvSource({
		"Dobro, Dobro, Traka, Traka, Teretana 1, Teretana 1, Adresa 1, Beograd, true",
		
		"Dobro, Lose, Traka, Traka, Teretana 1, Teretana 1, Adresa 1, Beograd, false",
		"Dobro, Dobro, Traka, Benc, Teretana 1, Teretana 1, Adresa 1, Beograd, false",
		"Dobro, Dobro, Traka, Traka, Teretana 1, Teretana 2, Adresa 1, Beograd, false",
		
		"Dobro, Lose, Traka, Benc, Teretana 1, Teretana 1, Adresa 1, Beograd, false",
		"Dobro, Lose, Traka, Traka, Teretana 1, Teretana 2, Adresa 1, Beograd, false",
		"Dobro, Dobro, Traka, Benc, Teretana 1, Teretana 2, Adresa 1, Beograd, false",		
		
		"Dobro, Lose, Traka, Benc, Teretana 1, Teretana 2, Adresa 1, Beograd, false"
	})
	void testEqualsObject(String stanje1, String stanje2, String vrsta1, String vrsta2, 
			String teretana1, String teretana2, String adresa, String grad, boolean isti) {
		Teretana t1 = new Teretana(teretana1, adresa, new Grad(grad));
		Teretana t2 = new Teretana(teretana2, adresa, new Grad(grad));
		
		Oprema o1 = new Oprema(stanje1, new VrstaOpreme(vrsta1), t1);
		Oprema o2 = new Oprema(stanje2, new VrstaOpreme(vrsta2), t2);
		
		assertEquals(isti, o1.equals(o2));
	}

	/**
	 * Test method for {@link domain.Oprema#toString()}.
	 */
	@Test
	void testToString() {
		o.setStanjeOpreme("Dobro");
		o.setVrsta(new VrstaOpreme("Traka"));
		o.setTeretana(new Teretana("Teretana 1", "Adresa 1", new Grad("Beograd")));
		
		assertEquals("stanje=Dobro, vrsta=Traka, teretana=Teretana 1", o.toString());
	}

}
