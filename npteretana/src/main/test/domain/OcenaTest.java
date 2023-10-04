/**
 * 
 */
package domain;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * @author Luka
 *
 */
class OcenaTest {

	Ocena o;
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		o = new Ocena();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
		o = null;
	}

	/**
	 * Test method for {@link domain.Ocena#setNalog(domain.Nalog)}.
	 */
	@Test
	void testSetNalog() {
		Nalog n = new Nalog();
		o.setNalog(n);
		
		assertEquals(n, o.getNalog());
	}
	
	@Test
	void testSetNalogNull() {
		Exception e = assertThrows(IllegalArgumentException.class,
				() -> o.setNalog(null));
		
		assertEquals("Nalog ne moze biti null", e.getMessage());
	}

	/**
	 * Test method for {@link domain.Ocena#setTeretana(domain.Teretana)}.
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
		
		assertEquals("Teretana ne moze biti null", e.getMessage());
	}

	/**
	 * Test method for {@link domain.Ocena#setVrednost(int)}.
	 */
	@Test
	void testSetVrednost() {
		o.setVrednost(5);
		
		assertEquals(5, o.getVrednost());
	}
	
	@ParameterizedTest
	@CsvSource({
		"0", "10"
	})
	void testSetVrednostNull(int vrednost) {
		Exception e = assertThrows(IllegalArgumentException.class,
				() -> o.setVrednost(vrednost));
		
		assertEquals("Vrednost mora biti izmedju 1 i 5", e.getMessage());
	}

	/**
	 * Test method for {@link domain.Ocena#equals(java.lang.Object)}.
	 */
	@ParameterizedTest
	@CsvSource({
		"Pera, Pera, Peric, pera, pera111, Teretana 1, Teretana 1, Adresa 1, Beograd, 5, 5, true",
		
		"Pera, Mika, Peric, pera, pera111, Teretana 1, Teretana 1, Adresa 1, Beograd, 5, 5, false",
		"Pera, Pera, Peric, pera, pera111, Teretana 1, Teretana 2, Adresa 1, Beograd, 5, 5, false",
		"Pera, Pera, Peric, pera, pera111, Teretana 1, Teretana 1, Adresa 1, Beograd, 5, 3, false",
		
		"Pera, Mika, Peric, pera, pera111, Teretana 1, Teretana 2, Adresa 1, Beograd, 5, 5, false",
		"Pera, Mika, Peric, pera, pera111, Teretana 1, Teretana 1, Adresa 1, Beograd, 5, 3, false",		
		"Pera, Pera, Peric, pera, pera111, Teretana 1, Teretana 2, Adresa 1, Beograd, 5, 3, false",
		
		"Pera, Mika, Peric, pera, pera111, Teretana 1, Teretana 2, Adresa 1, Beograd, 5, 3, false"
	})
	void testEqualsObject(String ime1, String ime2, String prezime, String korisnickoIme, String sifra,
			String teretana1, String teretana2, String adresa, String grad, 
			int vrednost1, int vrednost2, boolean isti) {
		Nalog n1 = new Nalog(ime1, prezime, korisnickoIme, sifra);
		Nalog n2 = new Nalog(ime2, prezime, korisnickoIme, sifra);
		
		Teretana t1 = new Teretana(teretana1, adresa, new Grad(grad));
		Teretana t2 = new Teretana(teretana2, adresa, new Grad(grad));
		
		Ocena o1 = new Ocena(n1, t1, vrednost1);
		Ocena o2 = new Ocena(n2, t2, vrednost2);
		
		assertEquals(isti, o1.equals(o2));
	}
	
	@Test
	void testEqualsNull() {
		assertFalse(o.equals(null));
	}
	
	@Test
	void testEqualsIsti() {
		assertTrue(o.equals(o));
	}
	
	@Test
	void testEqualsDrugaKlasa() {
		assertFalse(o.equals(new Exception()));
	}

}
