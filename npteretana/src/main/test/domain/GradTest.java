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
class GradTest {

	Grad g;
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		g = new Grad();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
		g = null;
	}

	/**
	 * Test method for {@link domain.Grad#setId(long)}.
	 */
	@Test
	void testSetId() {
		g.setId(10);
		
		assertEquals(10, g.getId());
	}

	/**
	 * Test method for {@link domain.Grad#setNaziv(java.lang.String)}.
	 */
	@Test
	void testSetNaziv() {
		g.setNaziv("Beograd");
		
		assertEquals("Beograd", g.getNaziv());
	}
	
	@Test
	void testSetNazivNull() {
		Exception e = assertThrows(IllegalArgumentException.class,
				() -> g.setNaziv(null)  );
		
		assertEquals("Naziv grada ne sme biti null ili prazan string", e.getMessage());
	}
	
	@Test
	void testSetNazivPrazan() {
		Exception e = assertThrows(IllegalArgumentException.class,
				() -> g.setNaziv("")  );
		
		assertEquals("Naziv grada ne sme biti null ili prazan string", e.getMessage());
	}

	/**
	 * Test method for {@link domain.Grad#equals(java.lang.Object)}.
	 */
	@ParameterizedTest
	@CsvSource ({
		"Beograd, Beograd, true",
		"Beograd, Nis, false"
	})
	void testEqualsObject(String naziv1, String naziv2, boolean isti) {
		Grad g1 = new Grad(naziv1);
		Grad g2 = new Grad(naziv2);
		
		assertEquals(isti, g1.equals(g2));
	}
	
	@Test
	void testEqualsNull() {
		assertFalse(g.equals(null));
	}
	
	@Test
	void testEqualsIsti() {
		assertTrue(g.equals(g));
	}
	
	@Test
	void testEqualsDrugaKlasa() {
		assertFalse(g.equals(new Exception()));
	}

	/**
	 * Test method for {@link domain.Grad#toString()}.
	 */
	@Test
	void testToString() {
		g.setNaziv("Beograd");
		
		assertEquals("Beograd", g.toString());
	}

}
