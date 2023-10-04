/**
 * 
 */
package domain;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * @author Luka
 *
 */
class TeretanaTest {

	Teretana t;
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		t = new Teretana();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
		t = null;
	}

	/**
	 * Test method for {@link domain.Teretana#setId(long)}.
	 */
	@Test
	void testSetId() {
		t.setId(10);
		
		assertEquals(10, t.getId());
	}

	/**
	 * Test method for {@link domain.Teretana#setNaziv(java.lang.String)}.
	 */
	@Test
	void testSetNaziv() {
		t.setNaziv("Teretana 1");
		
		assertEquals("Teretana 1", t.getNaziv());
	}
	
	@Test
	void testSetNazivNull() {
		Exception e = assertThrows(IllegalArgumentException.class,
				() -> t.setNaziv(null)  );
		
		assertEquals("Naziv teretane ne sme biti null ili prazan string", e.getMessage());
	}
	
	@Test
	void testSetPrezimePrazan() {
		Exception e = assertThrows(IllegalArgumentException.class,
				() -> t.setNaziv("")  );
		
		assertEquals("Naziv teretane ne sme biti null ili prazan string", e.getMessage());
	}

	/**
	 * Test method for {@link domain.Teretana#setAdresa(java.lang.String)}.
	 */
	@Test
	void testSetAdresa() {
		t.setAdresa("Adresa 1");
		
		assertEquals("Adresa 1", t.getAdresa());
	}
	
	@Test
	void testSetAdresaNull() {
		Exception e = assertThrows(IllegalArgumentException.class,
				() -> t.setAdresa(null)  );
		
		assertEquals("Adresa teretane ne sme biti null ili prazan string", e.getMessage());
	}
	
	@Test
	void testSetAdresaPrazan() {
		Exception e = assertThrows(IllegalArgumentException.class,
				() -> t.setAdresa("")  );
		
		assertEquals("Adresa teretane ne sme biti null ili prazan string", e.getMessage());
	}

	/**
	 * Test method for {@link domain.Teretana#setProsecnaOcena(java.math.BigDecimal)}.
	 */
	@Test
	void testSetProsecnaOcena() {
		t.setProsecnaOcena(BigDecimal.valueOf(5));
		
		assertEquals(BigDecimal.valueOf(5), t.getProsecnaOcena());
	}
	
	@Test
	void testSetOcenaNiska() {
		Exception e = assertThrows(IllegalArgumentException.class,
				() -> t.setProsecnaOcena(BigDecimal.valueOf(0)));
		
		assertEquals("Prosecna ocena mora biti jednaka ili veca od 1", e.getMessage());
	}

	/**
	 * Test method for {@link domain.Teretana#setGrad(domain.Grad)}.
	 */
	@Test
	void testSetGrad() {
		Grad g = new Grad("Beograd");		
		t.setGrad(g);
		
		assertEquals(new Grad("Beograd"), t.getGrad());
	}
	
	@Test
	void testSetGradNull() {
		Exception e = assertThrows(IllegalArgumentException.class,
				() -> t.setGrad(null));
		
		assertEquals("Grad ne sme biti null", e.getMessage());
	}

	/**
	 * Test method for {@link domain.Teretana#setOpreme(java.util.List)}.
	 */
	@Test
	void testSetOpreme() {
		Oprema o1 = new Oprema();
		Oprema o2 = new Oprema();
		List<Oprema> opreme = new ArrayList<>();
		opreme.add(o1);
		opreme.add(o2);
		t.setOpreme(opreme);
		
		assertEquals(opreme, t.getOpreme());		
	}

	/**
	 * Test method for {@link domain.Teretana#setTreneri(java.util.List)}.
	 */
	@Test
	void testSetTreneri() {
		Trener t1 = new Trener();
		Trener t2 = new Trener();
		List<Trener> treneri = new ArrayList<>();
		treneri.add(t1);
		treneri.add(t2);
		t.setTreneri(treneri);
		
		assertEquals(treneri, t.getTreneri());
	}

	/**
	 * Test method for {@link domain.Teretana#equals(java.lang.Object)}.
	 */
	@ParameterizedTest
	@CsvSource ({
		"Teretana 1, Adresa 1, Beograd, Teretana 1, Adresa 1, Beograd, true",
		
		"Teretana 1, Adresa 1, Beograd, Teretana 2, Adresa 2, Nis, false",
		"Teretana 1, Adresa 1, Beograd, Teretana 2, Adresa 2, Beograd, false",
		"Teretana 1, Adresa 1, Beograd, Teretana 2, Adresa 1, Nis, false",
		"Teretana 1, Adresa 1, Beograd, Teretana 1, Adresa 2, Nis, false",
		
		"Teretana 1, Adresa 1, Beograd, Teretana 2, Adresa 1, Beograd, false",
		"Teretana 1, Adresa 1, Beograd, Teretana 1, Adresa 2, Beograd, false",
		"Teretana 1, Adresa 1, Beograd, Teretana 1, Adresa 1, Nis, false"
	})
	void testEqualsObjectGreska(String teretana1, String adresa1, String grad1,
			String teretana2, String adresa2, String grad2, boolean isti) {
		Grad g1 = new Grad(grad1);
		Teretana t1 = new Teretana(teretana1, adresa1, g1);
		
		Grad g2 = new Grad(grad2);
		Teretana t2 = new Teretana(teretana2, adresa2, g2);
		
		assertEquals(isti, t1.equals(t2));
	}
	
	@Test
	void testEqualsNull() {
		assertFalse(t.equals(null));
	}
	
	@Test
	void testEqualsIsti() {
		assertTrue(t.equals(t));
	}
	
	@Test
	void testEqualsDrugaKlasa() {
		assertFalse(t.equals(new Exception()));
	}

	/**
	 * Test method for {@link domain.Teretana#toString()}.
	 */
	@Test
	void testToString() {
		t.setNaziv("Teretana 1");
		
		assertEquals("Teretana 1", t.toString());
	}

}
