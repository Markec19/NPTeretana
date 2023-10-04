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
class VrstaOpremeTest {

	VrstaOpreme v;
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		v = new VrstaOpreme();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
		v = null;
	}

	/**
	 * Test method for {@link domain.VrstaOpreme#setId(long)}.
	 */
	@Test
	void testSetId() {
		v.setId(10);
		
		assertEquals(10, v.getId());
	}

	/**
	 * Test method for {@link domain.VrstaOpreme#setVrsta(java.lang.String)}.
	 */
	@Test
	void testSetVrsta() {
		v.setVrsta("Traka");
		
		assertEquals("Traka", v.getVrsta());
	}
	
	@Test
	void testSetVrstaNull() {
		Exception e = assertThrows(IllegalArgumentException.class,
				() -> v.setVrsta(null)  );
		
		assertEquals("Naziv vrste ne sme biti null ili prazan string", e.getMessage());
	}
	
	@Test
	void testSetVrstaPrazan() {
		Exception e = assertThrows(IllegalArgumentException.class,
				() -> v.setVrsta("")  );
		
		assertEquals("Naziv vrste ne sme biti null ili prazan string", e.getMessage());
	}

	/**
	 * Test method for {@link domain.VrstaOpreme#equals(java.lang.Object)}.
	 */
	@ParameterizedTest
	@CsvSource ({
		"Traka, Traka, true",
		"Traka, Benc, false"
	})
	void testEqualsObject(String vr1, String vr2, boolean isti) {
		VrstaOpreme v1 = new VrstaOpreme(vr1);
		VrstaOpreme v2 = new VrstaOpreme(vr2);
		
		assertEquals(isti, v1.equals(v2));
	}
	
	@Test
	void testEqualsNull() {
		assertFalse(v.equals(null));
	}
	
	@Test
	void testEqualsIsti() {
		assertTrue(v.equals(v));
	}
	
	@Test
	void testEqualsDrugaKlasa() {
		assertFalse(v.equals(new Exception()));
	}
	
	/**
	 * Test method for {@link domain.VrstaOpreme#toString()}.
	 */
	@Test
	void testToString() {
		v.setVrsta("Traka");
		
		assertEquals("Traka", v.toString());
	}
	
	

}
