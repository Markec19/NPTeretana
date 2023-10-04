/**
 * 
 */
package domain;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.text.DateFormatter;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * @author Luka
 *
 */
class ClanarinaTest {
	
	Clanarina c;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		c = new Clanarina();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
		c = null;
	}

	/**
	 * Test method for {@link domain.Clanarina#setId(long)}.
	 */
	@Test
	void testSetId() {
		c.setId(10);
		
		assertEquals(10, c.getId());
	}

	/**
	 * Test method for {@link domain.Clanarina#setCena(java.math.BigDecimal)}.
	 */
	@Test
	void testSetCena() {
		c.setCena(BigDecimal.valueOf(2500));
		
		assertEquals(BigDecimal.valueOf(2500), c.getCena());
	}
	
	@Test
	void testSetCenaNull() {
		Exception e = assertThrows(IllegalArgumentException.class,
				() -> c.setCena(null)  );
		
		assertEquals("Cena ne moze biti null ili manja ili jednaka 0", e.getMessage());
	}
	
	@ParameterizedTest
	@CsvSource({
		"0", "-10"
	})
	void testSetCenaNiska(int cena) {
		Exception e = assertThrows(IllegalArgumentException.class,
				() -> c.setCena(BigDecimal.valueOf(cena))  );
		
		assertEquals("Cena ne moze biti null ili manja ili jednaka 0", e.getMessage());
	}

	/**
	 * Test method for {@link domain.Clanarina#setNalog(domain.Nalog)}.
	 */
	@Test
	void testSetNalog() {
		Nalog n = new Nalog();
		c.setNalog(n);
		
		assertEquals(n, c.getNalog());
	}
	
	@Test
	void testSetNalogNull() {
		Exception e = assertThrows(IllegalArgumentException.class,
				() -> c.setNalog(null));
		
		assertEquals("Nalog ne moze biti null", e.getMessage());
	}

	/**
	 * Test method for {@link domain.Clanarina#setTeretana(domain.Teretana)}.
	 */
	@Test
	void testSetTeretana() {
		Teretana t = new Teretana();
		c.setTeretana(t);
		
		assertEquals(t, c.getTeretana());
	}
	
	@Test
	void testSetTeretanaNull() {
		Exception e = assertThrows(IllegalArgumentException.class,
				() -> c.setTeretana(null));
		
		assertEquals("Teretana ne moze biti null", e.getMessage());
	}

	/**
	 * Test method for {@link domain.Clanarina#setDatumOd(java.time.LocalDate)}.
	 */
	@Test
	void testSetDatumOd() {
		LocalDate ld = LocalDate.parse("2023-10-10", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		c.setDatumOd(ld);
		
		assertEquals(ld, c.getDatumOd());
	}
	
	@Test
	void testSetDatumOdNull() {
		Exception e = assertThrows(IllegalArgumentException.class,
				() -> c.setDatumOd(null));
		
		assertEquals("Datum od ne moze biti null", e.getMessage());
	}

	/**
	 * Test method for {@link domain.Clanarina#setDatumDo(java.time.LocalDate)}.
	 */
	@Test
	void testSetDatumDo() {
		LocalDate ld = LocalDate.parse("2023-09-10", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		LocalDate ld1 = LocalDate.parse("2023-10-10", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		c.setDatumOd(ld);
		c.setDatumDo(ld1);
		
		assertEquals(ld1, c.getDatumDo());
	}
	
	@Test
	void testSetDatumDoNull() {
		Exception e = assertThrows(IllegalArgumentException.class,
				() -> c.setDatumDo(null));
		
		assertEquals("Datum do ne moze biti null i ne moze biti pre datuma od", e.getMessage());
	}
	
	@Test
	void testSetDatumDoPrerano() {
		LocalDate ld = LocalDate.parse("2023-09-10", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		c.setDatumOd(LocalDate.now());
		Exception e = assertThrows(IllegalArgumentException.class,
				() -> c.setDatumDo(ld));
		
		assertEquals("Datum do ne moze biti null i ne moze biti pre datuma od", e.getMessage());
	}

	/**
	 * Test method for {@link domain.Clanarina#equals(java.lang.Object)}.
	 */
	@ParameterizedTest
	@CsvSource({
		"Pera, Pera, Peric, pera, pera111, Teretana 1, Teretana 1, Adresa 1, Beograd, 2023-10-10, 2023-10-10, 2023-11-10, 2023-11-10, true",
		
		"Pera, Mika, Peric, pera, pera111, Teretana 1, Teretana 1, Adresa 1, Beograd, 2023-10-10, 2023-10-10, 2023-11-10, 2023-11-10, false",
		"Pera, Pera, Peric, pera, pera111, Teretana 1, Teretana 2, Adresa 1, Beograd, 2023-10-10, 2023-10-10, 2023-11-10, 2023-11-10, false",
		"Pera, Pera, Peric, pera, pera111, Teretana 1, Teretana 1, Adresa 1, Beograd, 2023-10-10, 2023-10-11, 2023-11-10, 2023-11-10, false",
		"Pera, Pera, Peric, pera, pera111, Teretana 1, Teretana 1, Adresa 1, Beograd, 2023-10-10, 2023-10-10, 2023-11-10, 2023-11-11, false",
		
		"Pera, Mika, Peric, pera, pera111, Teretana 1, Teretana 2, Adresa 1, Beograd, 2023-10-10, 2023-10-10, 2023-11-10, 2023-11-10, false",
		"Pera, Mika, Peric, pera, pera111, Teretana 1, Teretana 1, Adresa 1, Beograd, 2023-10-10, 2023-10-11, 2023-11-10, 2023-11-10, false",
		"Pera, Mika, Peric, pera, pera111, Teretana 1, Teretana 1, Adresa 1, Beograd, 2023-10-10, 2023-10-10, 2023-11-10, 2023-11-11, false",
		
		"Pera, Pera, Peric, pera, pera111, Teretana 1, Teretana 2, Adresa 1, Beograd, 2023-10-11, 2023-10-10, 2023-11-10, 2023-11-10, false",
		"Pera, Pera, Peric, pera, pera111, Teretana 1, Teretana 2, Adresa 1, Beograd, 2023-10-10, 2023-10-10, 2023-11-10, 2023-11-11, false",
		
		"Pera, Pera, Peric, pera, pera111, Teretana 1, Teretana 1, Adresa 1, Beograd, 2023-10-10, 2023-10-11, 2023-11-10, 2023-11-11, false",
		
		"Pera, Mika, Peric, pera, pera111, Teretana 1, Teretana 2, Adresa 1, Beograd, 2023-10-10, 2023-10-11, 2023-11-10, 2023-11-10, false",
		"Pera, Mika, Peric, pera, pera111, Teretana 1, Teretana 2, Adresa 1, Beograd, 2023-10-10, 2023-10-10, 2023-11-10, 2023-11-11, false",
		"Pera, Mika, Peric, pera, pera111, Teretana 1, Teretana 1, Adresa 1, Beograd, 2023-10-10, 2023-10-11, 2023-11-10, 2023-11-11, false",
		
		"Pera, Pera, Peric, pera, pera111, Teretana 1, Teretana 2, Adresa 1, Beograd, 2023-10-11, 2023-10-11, 2023-11-10, 2023-11-11, false",
		
		"Pera, Mika, Peric, pera, pera111, Teretana 1, Teretana 2, Adresa 1, Beograd, 2023-10-10, 2023-10-11, 2023-11-10, 2023-11-11, false"
	})
	void testEqualsObject(String ime1, String ime2, String prezime, String korisnickoIme, String sifra,
			String teretana1, String teretana2, String adresa, String grad, 
			String datOd1, String datOd2, String datDo1, String datDo2, boolean isti) {
		DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		Nalog n1 = new Nalog(ime1, prezime, korisnickoIme, sifra);
		Nalog n2 = new Nalog(ime2, prezime, korisnickoIme, sifra);
		
		Teretana t1 = new Teretana(teretana1, adresa, new Grad(grad));
		Teretana t2 = new Teretana(teretana2, adresa, new Grad(grad));
		
		LocalDate datumOd1 = LocalDate.parse(datOd1, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		LocalDate datumOd2 = LocalDate.parse(datOd2, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		
		LocalDate datumDo1 = LocalDate.parse(datDo1, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		LocalDate datumDo2 = LocalDate.parse(datDo2, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		
		Clanarina c1 = new Clanarina(BigDecimal.valueOf(5000), n1, t1, datumOd1, datumDo1);
		Clanarina c2 = new Clanarina(BigDecimal.valueOf(5000), n2, t2, datumOd2, datumDo2);
		
		assertEquals(isti, c1.equals(c2));
	}
	
	@Test
	void testEqualsNull() {
		assertFalse(c.equals(null));
	}
	
	@Test
	void testEqualsIsti() {
		assertTrue(c.equals(c));
	}
	
	@Test
	void testEqualsDrugaKlasa() {
		assertFalse(c.equals(new Exception()));
	}

}
