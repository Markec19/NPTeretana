/**
 * 
 */
package domain;

import static org.junit.jupiter.api.Assertions.*;

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
class IndividualniTreningTest {

	IndividualniTrening it;
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		it = new IndividualniTrening();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
		it = null;
	}

	/**
	 * Test method for {@link domain.IndividualniTrening#setNalog(domain.Nalog)}.
	 */
	@Test
	void testSetNalog() {
		Nalog n = new Nalog();
		it.setNalog(n);
		
		assertEquals(n, it.getNalog());
	}
	
	@Test
	void testSetNalogNull() {
		Exception e = assertThrows(IllegalArgumentException.class,
				() -> it.setNalog(null));
		
		assertEquals("Nalog ne sme biti null", e.getMessage());
	}

	/**
	 * Test method for {@link domain.IndividualniTrening#setTrener(domain.Trener)}.
	 */
	@Test
	void testSetTrener() {
		Trener t = new Trener();
		it.setTrener(t);
		
		assertEquals(t, it.getTrener());
	}
	
	@Test
	void testSetTrenerNull() {
		Exception e = assertThrows(IllegalArgumentException.class,
				() -> it.setTrener(null));
		
		assertEquals("Trener ne sme biti null", e.getMessage());
	}

	/**
	 * Test method for {@link domain.IndividualniTrening#setTermin(java.time.LocalDate)}.
	 */
	@Test
	void testSetTermin() {
		LocalDate ld = LocalDate.parse("2024-10-10", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		it.setTermin(ld);
		
		assertEquals(ld, it.getTermin());
	}
	
	@Test
	void testSetTerminNull() {
		Exception e = assertThrows(IllegalArgumentException.class,
				() -> it.setTermin(null));
		
		assertEquals("Termin ne moze biti null i ne moze biti u proslosti", e.getMessage());
	}
	
	@Test
	void testSetTerminUProslosti() {
		LocalDate ld = LocalDate.parse("2022-10-10", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		Exception e = assertThrows(IllegalArgumentException.class,
				() -> it.setTermin(ld));
		
		assertEquals("Termin ne moze biti null i ne moze biti u proslosti", e.getMessage());
	}

	/**
	 * Test method for {@link domain.IndividualniTrening#equals(java.lang.Object)}.
	 */
	@ParameterizedTest
	@CsvSource({
		"Pera, Pera, Peric, pera, pera111, Marko, Marko, Markovic, 2023-10-10, 2023-10-10, true",
		
		"Pera, Mika, Peric, pera, pera111, Marko, Marko, Markovic, 2023-10-10, 2023-10-10, false",
		"Pera, Pera, Peric, pera, pera111, Marko, Aca, Markovic, 2023-10-10, 2023-10-10, false",
		"Pera, Pera, Peric, pera, pera111, Marko, Marko, Markovic, 2023-10-10, 2023-10-11, false",
		
		"Pera, Mika, Peric, pera, pera111, Marko, Aca, Markovic, 2023-10-10, 2023-10-10, false",
		"Pera, Mika, Peric, pera, pera111, Marko, Marko, Markovic, 2023-10-10, 2023-10-11, false",
		"Pera, Pera, Peric, pera, pera111, Marko, Aca, Markovic, 2023-10-10, 2023-10-11, false",
		
		"Pera, Mika, Peric, pera, pera111, Marko, Aca, Markovic, 2023-10-10, 2023-10-11, false"
	})
	void testEqualsObject(String imeN1, String imeN2, String prezimeN, String korIme, String sifra, 
			String imeT1, String imeT2, String prezimeT, String termin1, String termin2, boolean isti) {
		Nalog n1 = new Nalog(imeN1, prezimeN, korIme, sifra);
		Nalog n2 = new Nalog(imeN2, prezimeN, korIme, sifra);
		
		Teretana tr = new Teretana();		
		Trener t1 = new Trener(imeT1, prezimeT, tr);
		Trener t2 = new Trener(imeT2, prezimeT, tr);
		
		LocalDate ld1 = LocalDate.parse(termin1, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		LocalDate ld2 = LocalDate.parse(termin2, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		
		IndividualniTrening it1 = new IndividualniTrening(n1, t1, ld1);
		IndividualniTrening it2 = new IndividualniTrening(n2, t2, ld2);
		
		assertEquals(isti, it1.equals(it2));
	}
	
	@Test
	void testEqualsNull() {
		assertFalse(it.equals(null));
	}
	
	@Test
	void testEqualsIsti() {
		assertTrue(it.equals(it));
	}
	
	@Test
	void testEqualsDrugaKlasa() {
		assertFalse(it.equals(new Exception()));
	}

	/**
	 * Test method for {@link domain.IndividualniTrening#toString()}.
	 */
	@Test
	void testToString() {
		LocalDate ld = LocalDate.parse("2024-10-10", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		Nalog n = new Nalog("Pera", "Peric", "pera", "pera123");
		Teretana tr = new Teretana();
		Trener t = new Trener("Mika", "Mikic", tr);
		IndividualniTrening it = new IndividualniTrening(n, t, ld);
		
		assertEquals("nalog=pera, trener=Mika Mikic, termin=2024-10-10", it.toString());
	}

}
