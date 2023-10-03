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
class NalogTest {

	Nalog n;
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		n = new Nalog();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
		n = null;
	}

	/**
	 * Test method for {@link domain.Nalog#setId(long)}.
	 */
	@Test
	void testSetId() {
		n.setId(10);
		
		assertEquals(10, n.getId());
	}

	/**
	 * Test method for {@link domain.Nalog#setIme(java.lang.String)}.
	 */
	@Test
	void testSetIme() {
		n.setIme("Marko");
		
		assertEquals("Marko", n.getIme());
	}
	
	@Test
	void testSetImeNull() {
		Exception e = assertThrows(IllegalArgumentException.class,
				() -> n.setIme(null)  );
		
		assertEquals("Ime korisnika ne moze biti null ili prazan string", e.getMessage());
	}
	
	@Test
	void testSetImePrazan() {
		Exception e = assertThrows(IllegalArgumentException.class,
				() -> n.setIme("")  );
		
		assertEquals("Ime korisnika ne moze biti null ili prazan string", e.getMessage());
	}

	/**
	 * Test method for {@link domain.Nalog#setPrezime(java.lang.String)}.
	 */
	@Test
	void testSetPrezime() {
		n.setPrezime("Markovic");
		
		assertEquals("Markovic", n.getPrezime());
	}
	
	@Test
	void testSetPrezimeNull() {
		Exception e = assertThrows(IllegalArgumentException.class,
				() -> n.setPrezime(null)  );
		
		assertEquals("Prezime korisnika ne moze biti null ili prazan string", e.getMessage());
	}
	
	@Test
	void testSetPrezimePrazan() {
		Exception e = assertThrows(IllegalArgumentException.class,
				() -> n.setPrezime("")  );
		
		assertEquals("Prezime korisnika ne moze biti null ili prazan string", e.getMessage());
	}

	/**
	 * Test method for {@link domain.Nalog#setKorisnickoIme(java.lang.String)}.
	 */
	@Test
	void testSetKorisnickoIme() {
		n.setKorisnickoIme("marko123");
		
		assertEquals("marko123", n.getKorisnickoIme());
	}
	
	@Test
	void testSetKorisnickoImeNull() {
		Exception e = assertThrows(IllegalArgumentException.class,
				() -> n.setKorisnickoIme(null)  );
		
		assertEquals("Korisnicko ime ne moze biti null ili prazan string", e.getMessage());
	}
	
	@Test
	void testSetKorisnickoImePrazan() {
		Exception e = assertThrows(IllegalArgumentException.class,
				() -> n.setKorisnickoIme("")  );
		
		assertEquals("Korisnicko ime ne moze biti null ili prazan string", e.getMessage());
	}

	/**
	 * Test method for {@link domain.Nalog#setSifra(java.lang.String)}.
	 */
	@Test
	void testSetSifra() {
		n.setSifra("marko111");
		
		assertEquals("marko111", n.getSifra());
	}
	
	@Test
	void testSetSifraNull() {
		Exception e = assertThrows(IllegalArgumentException.class,
				() -> n.setSifra(null)  );
		
		assertEquals("Sifra ne moze biti null ili prazan string", e.getMessage());
	}
	
	@Test
	void testSetSifraPrazan() {
		Exception e = assertThrows(IllegalArgumentException.class,
				() -> n.setSifra("")  );
		
		assertEquals("Sifra ne moze biti null ili prazan string", e.getMessage());
	}

	/**
	 * Test method for {@link domain.Nalog#equals(java.lang.Object)}.
	 */
	@ParameterizedTest
	@CsvSource ({
		"Pera, Peric, pera, pera111, Pera, Peric, pera, pera111, true",
		"Pera, Peric, pera, pera111, Mika, Mikic, mika, mika111, false",
		"Pera, Peric, pera, pera111, Pera, Mikic, mika, mika111, false",
		"Pera, Peric, pera, pera111, Mika, Peric, mika, mika111, false",
		"Pera, Peric, pera, pera111, Mika, Mikic, pera, mika111, false",
		"Pera, Peric, pera, pera111, Mika, Mikic, mika, pera111, false",
		
		"Pera, Peric, pera, pera111, Pera, Peric, mika, mika111, false",
		"Pera, Peric, pera, pera111, Pera, Mikic, pera, mika111, false",
		"Pera, Peric, pera, pera111, Pera, Mikic, mika, pera111, false",
		
		"Pera, Peric, pera, pera111, Pera, Peric, pera, mika111, false",
		"Pera, Peric, pera, pera111, Pera, Peric, mika, pera111, false",		
		"Pera, Peric, pera, pera111, Pera, Mikic, pera, pera111, false",
		
		"Pera, Peric, pera, pera111, Mika, Peric, pera, mika111, false",
		"Pera, Peric, pera, pera111, Mika, Peric, mika, pera111, false",
		"Pera, Peric, pera, pera111, Mika, Peric, pera, pera111, false",
		
		"Pera, Peric, pera, pera111, Mika, Mikic, pera, pera111, false",
		
		"Pera, Peric, pera, pera111, Mika, Peric, pera, pera111, false",
		"Pera, Peric, pera, pera111, Pera, Mikic, pera, pera111, false",
		"Pera, Peric, pera, pera111, Pera, Peric, mika, pera111, false",
		"Pera, Peric, pera, pera111, Pera, Peric, pera, mika111, false",
	})
	void testEqualsObject(String ime1, String prezime1, String korisnickoIme1, String sifra1,
			String ime2, String prezime2, String korisnickoIme2, String sifra2, boolean isti) {
		
		Nalog n1 = new Nalog(ime1, prezime1, korisnickoIme1, sifra1);
		Nalog n2 = new Nalog(ime2, prezime2, korisnickoIme2, sifra2);

		assertEquals(isti, n1.equals(n2));
	}

	@Test
	void testEqualsNull() {
		assertFalse(n.equals(null));
	}
	
	@Test
	void testEqualsIsti() {
		assertTrue(n.equals(n));
	}
	
	@Test
	void testEqualsDrugaKlasa() {
		assertFalse(n.equals(new Exception()));
	}
	
	/**
	 * Test method for {@link domain.Nalog#toString()}.
	 */
	@Test
	void testToString() {
		n.setIme("Marko");
		n.setPrezime("Markovic");
		n.setKorisnickoIme("marko");
		
		assertEquals("ime=Marko, prezime=Markovic, korisnicko ime=marko", n.toString());
	}

}
