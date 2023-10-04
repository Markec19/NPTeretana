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
class TrenerTest {

	Trener t;
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		t = new Trener();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
		t = null;
	}

	/**
	 * Test method for {@link domain.Trener#setId(long)}.
	 */
	@Test
	void testSetId() {
		t.setId(10);
		
		assertEquals(10, t.getId());
	}

	/**
	 * Test method for {@link domain.Trener#setIme(java.lang.String)}.
	 */
	@Test
	void testSetIme() {
		t.setIme("Marko");
		
		assertEquals("Marko", t.getIme());
	}
	
	@Test
	void testSetImeNull() {
		Exception e = assertThrows(IllegalArgumentException.class,
				() -> t.setIme(null)  );
		
		assertEquals("Ime trenera ne moze biti null ili prazan string", e.getMessage());
	}
	
	@Test
	void testSetImePrazan() {
		Exception e = assertThrows(IllegalArgumentException.class,
				() -> t.setIme("")  );
		
		assertEquals("Ime trenera ne moze biti null ili prazan string", e.getMessage());
	}

	/**
	 * Test method for {@link domain.Trener#setPrezime(java.lang.String)}.
	 */
	@Test
	void testSetPrezime() {
		t.setPrezime("Markovic");
		
		assertEquals("Markovic", t.getPrezime());
	}
	
	@Test
	void testSetPrezimeNull() {
		Exception e = assertThrows(IllegalArgumentException.class,
				() -> t.setPrezime(null)  );
		
		assertEquals("Prezime trenera ne moze biti null ili prazan string", e.getMessage());
	}
	
	@Test
	void testSetPrezimePrazan() {
		Exception e = assertThrows(IllegalArgumentException.class,
				() -> t.setPrezime("")  );
		
		assertEquals("Prezime trenera ne moze biti null ili prazan string", e.getMessage());
	}

	/**
	 * Test method for {@link domain.Trener#setTeretana(domain.Teretana)}.
	 */
	@Test
	void testSetTeretana() {
		Teretana tr = new Teretana();
		t.setTeretana(tr);
		
		assertEquals(tr, t.getTeretana());
	}
	
	@Test
	void testSetTeretanaNull() {
		Exception e = assertThrows(IllegalArgumentException.class,
				() -> t.setTeretana(null));
		
		assertEquals("Teretana ne moze biti null", e.getMessage());
	}

	/**
	 * Test method for {@link domain.Trener#equals(java.lang.Object)}.
	 */
	@ParameterizedTest
	@CsvSource({
		"Pera, Peric, Pera, Peric, Teretana 1, Teretana 1, Adresa 1, Beograd, true",
		
		"Pera, Peric, Mika, Peric, Teretana 1, Teretana 1, Adresa 1, Beograd, false",
		"Pera, Peric, Pera, Mikic, Teretana 1, Teretana 1, Adresa 1, Beograd, false",
		"Pera, Peric, Pera, Peric, Teretana 1, Teretana 2, Adresa 1, Beograd, false",
		
		"Pera, Peric, Mika, Mikic, Teretana 1, Teretana 1, Adresa 1, Beograd, false",
		"Pera, Peric, Mika, Peric, Teretana 1, Teretana 2, Adresa 1, Beograd, false",
		"Pera, Peric, Pera, Mikic, Teretana 1, Teretana 2, Adresa 1, Beograd, false",
		
		"Pera, Peric, Mika, Mikic, Teretana 1, Teretana 2, Adresa 1, Beograd, false"
	})
	void testEqualsObject(String ime1, String prezime1, String ime2, String prezime2,
			String teretana1, String teretana2, String adresa, String grad, boolean isti) {
		
		Teretana tr1 = new Teretana(teretana1, adresa, new Grad(grad));
		Teretana tr2 = new Teretana(teretana2, adresa, new Grad(grad));
		
		Trener t1 = new Trener(ime1, prezime1, tr1);
		Trener t2 = new Trener(ime2, prezime2, tr2);
		
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
	 * Test method for {@link domain.Trener#toString()}.
	 */
	@Test
	void testToString() {
		t.setIme("Marko");
		t.setPrezime("Markovic");
		
		assertEquals("Marko Markovic", t.toString());
	}

}
