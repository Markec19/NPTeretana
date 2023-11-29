/**
 * 
 */
package controller;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domain.Clanarina;
import domain.Grad;
import domain.IndividualniTrening;
import domain.Nalog;
import domain.Ocena;
import domain.Oprema;
import domain.Teretana;
import domain.Trener;
import domain.VrstaOpreme;

/**
 * @author Luka
 *
 */
class ControllerTest {

	Controller c;
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		c = Controller.getInstance();
		c.getDbCon().getConn().setSufiks("_test");
		c.getDbCon().getConn().getConnection();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
		c.getDbCon().getConn().closeConnection();;
		c = null;
	}

	/**
	 * Test method for {@link controller.Controller#login(java.lang.String, java.lang.String)}.
	 * @throws Exception 
	 */
	@Test
	void testLogin() throws Exception {
		String korIme = "test123";
		String sifra = "test123";
		Nalog n = new Nalog(korIme, sifra);
		n.setIme("Test");
		n.setPrezime("Test");
		
		
		c.zapamtiNalog(n);
		
		assertEquals(n.getIme(), c.login(n).getIme());
	}

	/**
	 * Test method for {@link controller.Controller#vratiSveNaloge()}.
	 * @throws Exception 
	 */
	@Test
	void testVratiSveNaloge() throws Exception {
		
		
		assertTrue(c.vratiSveNaloge().size() > 0);
	}

	/**
	 * Test method for {@link controller.Controller#sacuvajNalog(domain.Nalog)}.
	 * @throws Exception 
	 */
	@Test
	void testSacuvajNalog() throws Exception {
		String korIme = "test123";
		String sifra = "test123";
		Nalog n = new Nalog(korIme, sifra);
		n.setIme("Test");
		n.setPrezime("Test");
		c.zapamtiNalog(n);
		
		n.setKorisnickoIme("test111");
		
		assertEquals(true, c.sacuvajNalog(n));
		//c.getDbCon().getConn().getConnection().rollback();
	}

	/**
	 * Test method for {@link controller.Controller#zapamtiNalog(domain.Nalog)}.
	 * @throws Exception 
	 */
	@Test
	void testZapamtiNalog() throws Exception {
		String korIme = "test123";
		String sifra = "test123";
		Nalog n = new Nalog(korIme, sifra);
		n.setIme("Test");
		n.setPrezime("Test");
		
		assertEquals(true, c.zapamtiNalog(n));
		//c.getDbCon().getConnection().rollback();
	}

	/**
	 * Test method for {@link controller.Controller#vratiSveGradove()}.
	 * @throws Exception 
	 */
	@Test
	void testVratiSveGradove() throws Exception {
		assertTrue(c.vratiSveGradove().size() > 0);
	}

	/**
	 * Test method for {@link controller.Controller#vratiSveTeretane()}.
	 * @throws Exception 
	 */
	@Test
	void testVratiSveTeretane() throws Exception {
		assertTrue(c.vratiSveTeretane().size() > 0);
	}

	/**
	 * Test method for {@link controller.Controller#zapamtiTeretanu(domain.Teretana)}.
	 * @throws Exception 
	 */
	@Test
	void testZapamtiTeretanu() throws Exception {
		Teretana t = new Teretana();
		Grad g = new Grad(1, "Beograd");
		
		t.setGrad(g);
		t.setNaziv("Teretana test");
		t.setAdresa("Adresa test");
		
		assertEquals(true, c.zapamtiTeretanu(t));
	}

	/**
	 * Test method for {@link controller.Controller#nadjiTeretane(domain.Grad)}.
	 * @throws Exception 
	 */
	@Test
	void testNadjiTeretane() throws Exception {
		Grad g = new Grad(1, "Beograd");
		Teretana t = new Teretana();
		
		t.setGrad(g);
		t.setNaziv("Teretana test");
		t.setAdresa("Adresa test");
		c.zapamtiTeretanu(t);
		
		assertTrue(c.nadjiTeretane(g).size() > 0);
	}

	/**
	 * Test method for {@link controller.Controller#ucitajTeretanu(domain.Teretana)}.
	 * @throws Exception 
	 */
	@Test
	void testUcitajTeretanu() throws Exception {
		Teretana t = new Teretana();
		Grad g = new Grad(1, "Beograd");
		
		t.setGrad(g);
		t.setNaziv("Teretana test");
		t.setAdresa("Adresa test");
		c.zapamtiTeretanu(t);
		
		Teretana t1 = c.ucitajTeretanu(t);
		
		assertEquals(t.getId(), t1.getId());		
	}

	/**
	 * Test method for {@link controller.Controller#vratiSveClanarine()}.
	 * @throws Exception 
	 */
	@Test
	void testVratiSveClanarine() throws Exception {
		assertTrue(c.vratiSveClanarine().size() > 0);
	}

	/**
	 * Test method for {@link controller.Controller#vratiSveClanarine(domain.Nalog)}.
	 * @throws Exception 
	 */
	@Test
	void testVratiSveClanarineNalog() throws Exception {
		String korIme = "test123";
		String sifra = "test123";
		Nalog n = new Nalog(korIme, sifra);
		n.setIme("Test");
		n.setPrezime("Test");			
		c.zapamtiNalog(n);
		
		Grad g = new Grad(1, "Beograd");
		Teretana t = new Teretana();		
		t.setGrad(g);
		t.setNaziv("Teretana test");
		t.setAdresa("Adresa test");
		c.zapamtiTeretanu(t);
		
		Clanarina cl = new Clanarina();
		LocalDate ld = LocalDate.parse("2023-09-10", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		LocalDate ld1 = LocalDate.parse("2023-10-10", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		cl.setNalog(n);
		cl.setTeretana(t);
		cl.setCena(BigDecimal.valueOf(2500));
		cl.setDatumOd(ld);
		cl.setDatumDo(ld1);
		c.zapamtiClanarinu(cl);
		
		assertTrue(c.vratiSveClanarine(n).size() > 0);
	}

	/**
	 * Test method for {@link controller.Controller#sacuvajClanarinu(domain.Clanarina)}.
	 * @throws Exception 
	 */
	@Test
	void testSacuvajClanarinu() throws Exception {
		String korIme = "test123";
		String sifra = "test123";
		Nalog n = new Nalog(korIme, sifra);
		n.setIme("Test");
		n.setPrezime("Test");			
		c.zapamtiNalog(n);
		
		Grad g = new Grad(1, "Beograd");
		Teretana t = new Teretana();		
		t.setGrad(g);
		t.setNaziv("Teretana test");
		t.setAdresa("Adresa test");
		c.zapamtiTeretanu(t);
		
		Clanarina cl = new Clanarina();
		LocalDate ld = LocalDate.parse("2023-09-10", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		LocalDate ld1 = LocalDate.parse("2023-10-10", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		cl.setNalog(n);
		cl.setTeretana(t);
		cl.setCena(BigDecimal.valueOf(2500));
		cl.setDatumOd(ld);
		cl.setDatumDo(ld1);
		c.zapamtiClanarinu(cl);
		
		cl.setCena(BigDecimal.valueOf(3000));
		
		assertEquals(true, c.sacuvajClanarinu(cl));
	}

	/**
	 * Test method for {@link controller.Controller#zapamtiClanarinu(domain.Clanarina)}.
	 * @throws Exception 
	 */
	@Test
	void testZapamtiClanarinu() throws Exception {
		String korIme = "test123";
		String sifra = "test123";
		Nalog n = new Nalog(korIme, sifra);
		n.setIme("Test");
		n.setPrezime("Test");			
		c.zapamtiNalog(n);
		
		Grad g = new Grad(1, "Beograd");
		Teretana t = new Teretana();		
		t.setGrad(g);
		t.setNaziv("Teretana test");
		t.setAdresa("Adresa test");
		c.zapamtiTeretanu(t);
		
		Clanarina cl = new Clanarina();
		LocalDate ld = LocalDate.parse("2023-09-10", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		LocalDate ld1 = LocalDate.parse("2023-10-10", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		cl.setNalog(n);
		cl.setTeretana(t);
		cl.setCena(BigDecimal.valueOf(2500));
		cl.setDatumOd(ld);
		cl.setDatumDo(ld1);
		assertEquals(true, c.zapamtiClanarinu(cl));
	}

	/**
	 * Test method for {@link controller.Controller#ucitajClanarinu(domain.Clanarina)}.
	 * @throws Exception 
	 */
	@Test
	void testUcitajClanarinu() throws Exception {
		String korIme = "test123";
		String sifra = "test123";
		Nalog n = new Nalog(korIme, sifra);
		n.setIme("Test");
		n.setPrezime("Test");			
		c.zapamtiNalog(n);
		
		Grad g = new Grad(1, "Beograd");
		Teretana t = new Teretana();		
		t.setGrad(g);
		t.setNaziv("Teretana test");
		t.setAdresa("Adresa test");
		c.zapamtiTeretanu(t);
		
		Clanarina cl = new Clanarina();
		LocalDate ld = LocalDate.parse("2023-09-10", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		LocalDate ld1 = LocalDate.parse("2023-10-10", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		cl.setNalog(n);
		cl.setTeretana(t);
		cl.setCena(BigDecimal.valueOf(2500));
		cl.setDatumOd(ld);
		cl.setDatumDo(ld1);
		c.zapamtiClanarinu(cl);
		
		Clanarina cl1 = c.ucitajClanarinu(cl);
		assertEquals(cl.getId(), cl1.getId());
	}

	/**
	 * Test method for {@link controller.Controller#vratiSveOcene(domain.Nalog)}.
	 * @throws Exception 
	 */
	@Test
	void testVratiSveOcene() throws Exception {
		String korIme = "test123";
		String sifra = "test123";
		Nalog n = new Nalog(korIme, sifra);
		n.setIme("Test");
		n.setPrezime("Test");			
		c.zapamtiNalog(n);
		
		Grad g = new Grad(1, "Beograd");
		Teretana t = new Teretana();		
		t.setGrad(g);
		t.setNaziv("Teretana test");
		t.setAdresa("Adresa test");
		c.zapamtiTeretanu(t);
		
		Ocena o = new Ocena();
		o.setNalog(n);
		o.setTeretana(t);
		o.setVrednost(5);
		c.dodajOcenu(o);
		
		assertTrue(c.vratiSveOcene(n).size() > 0);
	}

	/**
	 * Test method for {@link controller.Controller#urediOcenu(domain.Ocena)}.
	 * @throws Exception 
	 */
	@Test
	void testUrediOcenu() throws Exception {
		String korIme = "test123";
		String sifra = "test123";
		Nalog n = new Nalog(korIme, sifra);
		n.setIme("Test");
		n.setPrezime("Test");			
		c.zapamtiNalog(n);
		
		Grad g = new Grad(1, "Beograd");
		Teretana t = new Teretana();		
		t.setGrad(g);
		t.setNaziv("Teretana test");
		t.setAdresa("Adresa test");
		c.zapamtiTeretanu(t);
		
		Ocena o = new Ocena();
		o.setNalog(n);
		o.setTeretana(t);
		o.setVrednost(5);
		c.dodajOcenu(o);
		
		o.setVrednost(4);
		
		assertEquals(true, c.urediOcenu(o));
	}

	/**
	 * Test method for {@link controller.Controller#dodajOcenu(domain.Ocena)}.
	 * @throws Exception 
	 */
	@Test
	void testDodajOcenu() throws Exception {
		String korIme = "test123";
		String sifra = "test123";
		Nalog n = new Nalog(korIme, sifra);
		n.setIme("Test");
		n.setPrezime("Test");			
		c.zapamtiNalog(n);
		
		Grad g = new Grad(1, "Beograd");
		Teretana t = new Teretana();		
		t.setGrad(g);
		t.setNaziv("Teretana test");
		t.setAdresa("Adresa test");
		c.zapamtiTeretanu(t);
		
		Ocena o = new Ocena();
		o.setNalog(n);
		o.setTeretana(t);
		o.setVrednost(5);
		
		assertEquals(true, c.dodajOcenu(o));
	}

	/**
	 * Test method for {@link controller.Controller#vratiSveVrsteOpreme()}.
	 * @throws Exception 
	 */
	@Test
	void testVratiSveVrsteOpreme() throws Exception {
		assertTrue(c.vratiSveVrsteOpreme().size() > 0);
	}

	/**
	 * Test method for {@link controller.Controller#dodajOpremu(domain.Oprema)}.
	 * @throws Exception 
	 */
	@Test
	void testDodajOpremu() throws Exception {
		Grad g = new Grad(1, "Beograd");
		Teretana t = new Teretana();		
		t.setGrad(g);
		t.setNaziv("Teretana test");
		t.setAdresa("Adresa test");
		c.zapamtiTeretanu(t);
		
		VrstaOpreme vo = new VrstaOpreme(1, "Traka za trcanje");
		
		String stanje = "Dobro";
		Oprema o = new Oprema(stanje, vo, t);
		assertEquals(true, c.dodajOpremu(o));
	}

	/**
	 * Test method for {@link controller.Controller#urediOpremu(domain.Oprema)}.
	 * @throws Exception 
	 */
	@Test
	void testUrediOpremu() throws Exception {
		Grad g = new Grad(1, "Beograd");
		Teretana t = new Teretana();		
		t.setGrad(g);
		t.setNaziv("Teretana test");
		t.setAdresa("Adresa test");
		c.zapamtiTeretanu(t);
		
		VrstaOpreme vo = new VrstaOpreme(1, "Traka za trcanje");
		
		String stanje = "Dobro";
		Oprema o = new Oprema(stanje, vo, t);
		c.dodajOpremu(o);
		o.setStanjeOpreme("Srednje");
		
		assertEquals(true, c.urediOpremu(o));
	}

	/**
	 * Test method for {@link controller.Controller#nadjiOpreme(domain.Teretana)}.
	 * @throws Exception 
	 */
	@Test
	void testNadjiOpremeTeretana() throws Exception {
		Grad g = new Grad(1, "Beograd");
		Teretana t = new Teretana();		
		t.setGrad(g);
		t.setNaziv("Teretana test");
		t.setAdresa("Adresa test");
		c.zapamtiTeretanu(t);
		
		VrstaOpreme vo = new VrstaOpreme(1, "Traka za trcanje");
		
		String stanje = "Dobro";
		Oprema o = new Oprema(stanje, vo, t);
		c.dodajOpremu(o);
		
		assertTrue(c.nadjiOpreme(t).size() > 0);
	}

	/**
	 * Test method for {@link controller.Controller#nadjiOpreme(domain.VrstaOpreme)}.
	 * @throws Exception 
	 */
	@Test
	void testNadjiOpremeVrstaOpreme() throws Exception {
		Grad g = new Grad(1, "Beograd");
		Teretana t = new Teretana();		
		t.setGrad(g);
		t.setNaziv("Teretana test");
		t.setAdresa("Adresa test");
		c.zapamtiTeretanu(t);
		
		VrstaOpreme vo = new VrstaOpreme(1, "Traka za trcanje");
		
		String stanje = "Dobro";
		Oprema o = new Oprema(stanje, vo, t);
		c.dodajOpremu(o);
		
		assertTrue(c.nadjiOpreme(vo).size() > 0);
	}

	/**
	 * Test method for {@link controller.Controller#vratiSveOpreme()}.
	 * @throws Exception 
	 */
	@Test
	void testVratiSveOpreme() throws Exception {
		assertTrue(c.vratiSveOpreme().size() > 0);
	}

	/**
	 * Test method for {@link controller.Controller#dodajTrenera(domain.Trener)}.
	 * @throws Exception 
	 */
	@Test
	void testDodajTrenera() throws Exception {
		Grad g = new Grad(1, "Beograd");
		Teretana tr = new Teretana();		
		tr.setGrad(g);
		tr.setNaziv("Teretana test");
		tr.setAdresa("Adresa test");
		c.zapamtiTeretanu(tr);
		
		String ime = "Test";
		String prezime = "Test";
		Trener t = new Trener(ime, prezime, tr);
		
		assertEquals(true, c.dodajTrenera(t));
	}

	/**
	 * Test method for {@link controller.Controller#urediTrenera(domain.Trener)}.
	 * @throws Exception 
	 */
	@Test
	void testUrediTrenera() throws Exception {
		Grad g = new Grad(1, "Beograd");
		Teretana tr = new Teretana();		
		tr.setGrad(g);
		tr.setNaziv("Teretana test");
		tr.setAdresa("Adresa test");
		c.zapamtiTeretanu(tr);
		
		String ime = "Test";
		String prezime = "Test";
		Trener t = new Trener(ime, prezime, tr);
		c.dodajTrenera(t);
		t.setIme("Test1");
		
		assertEquals(true, c.urediTrenera(t));
	}

	/**
	 * Test method for {@link controller.Controller#nadjiTrenere(domain.Teretana)}.
	 * @throws Exception 
	 */
	@Test
	void testNadjiTrenere() throws Exception {
		Grad g = new Grad(1, "Beograd");
		Teretana tr = new Teretana();		
		tr.setGrad(g);
		tr.setNaziv("Teretana test");
		tr.setAdresa("Adresa test");
		c.zapamtiTeretanu(tr);
		
		String ime = "Test";
		String prezime = "Test";
		Trener t = new Trener(ime, prezime, tr);
		c.dodajTrenera(t);
		
		assertTrue(c.nadjiTrenere(tr).size() > 0);
	}

	/**
	 * Test method for {@link controller.Controller#dodajIndividualniTrening(domain.IndividualniTrening)}.
	 * @throws Exception 
	 */
	@Test
	void testDodajIndividualniTrening() throws Exception {
		Grad g = new Grad(1, "Beograd");
		Teretana tr = new Teretana();		
		tr.setGrad(g);
		tr.setNaziv("Teretana test");
		tr.setAdresa("Adresa test");
		c.zapamtiTeretanu(tr);
		
		String ime = "Test";
		String prezime = "Test";
		Trener t = new Trener(ime, prezime, tr);
		c.dodajTrenera(t);
		
		String korIme = "test123";
		String sifra = "test123";
		Nalog n = new Nalog(korIme, sifra);
		n.setIme("Test");
		n.setPrezime("Test");
		c.zapamtiNalog(n);
		
		LocalDate ld = LocalDate.parse("2024-10-10", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		IndividualniTrening it = new IndividualniTrening(n, t, ld);
		
		assertEquals(true, c.dodajIndividualniTrening(it));
	}

	/**
	 * Test method for {@link controller.Controller#vratiSveIndividualneTreninge(domain.Trener)}.
	 * @throws Exception 
	 */
	@Test
	void testVratiSveIndividualneTreningeTrener() throws Exception {
		Grad g = new Grad(1, "Beograd");
		Teretana tr = new Teretana();		
		tr.setGrad(g);
		tr.setNaziv("Teretana test");
		tr.setAdresa("Adresa test");
		c.zapamtiTeretanu(tr);
		
		String ime = "Test";
		String prezime = "Test";
		Trener t = new Trener(ime, prezime, tr);
		c.dodajTrenera(t);
		
		String korIme = "test123";
		String sifra = "test123";
		Nalog n = new Nalog(korIme, sifra);
		n.setIme("Test");
		n.setPrezime("Test");
		c.zapamtiNalog(n);
		
		LocalDate ld = LocalDate.parse("2024-10-10", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		IndividualniTrening it = new IndividualniTrening(n, t, ld);
		c.dodajIndividualniTrening(it);
		
		assertTrue(c.vratiSveIndividualneTreninge(t).size() > 0);
	}

	/**
	 * Test method for {@link controller.Controller#vratiSveIndividualneTreninge(domain.Nalog)}.
	 * @throws Exception 
	 */
	@Test
	void testVratiSveIndividualneTreningeNalog() throws Exception {
		Grad g = new Grad(1, "Beograd");
		Teretana tr = new Teretana();		
		tr.setGrad(g);
		tr.setNaziv("Teretana test");
		tr.setAdresa("Adresa test");
		c.zapamtiTeretanu(tr);
		
		String ime = "Test";
		String prezime = "Test";
		Trener t = new Trener(ime, prezime, tr);
		c.dodajTrenera(t);
		
		String korIme = "test123";
		String sifra = "test123";
		Nalog n = new Nalog(korIme, sifra);
		n.setIme("Test");
		n.setPrezime("Test");
		c.zapamtiNalog(n);
		
		LocalDate ld = LocalDate.parse("2024-10-10", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		IndividualniTrening it = new IndividualniTrening(n, t, ld);
		c.dodajIndividualniTrening(it);
		
		assertTrue(c.vratiSveIndividualneTreninge(n).size() > 0);
	}
	
	@Test
	void testSacuvajUJSONNalog() {
		Nalog n = new Nalog("Test", "Test", "test111", "test123");
		
		assertEquals(true, c.sacuvajUJSONNalog(n));
	}
	
	@Test
	void testSacuvajUJSONTeretana() {
		Grad g = new Grad("Beograd");
		
		Teretana t = new Teretana("Teretana test", "Adresa test", g);
		
		assertEquals(true, c.sacuvajUJSONTeretana(t));
	}
	
	@Test
	void testSacuvajUJSONTrener() {
		Grad g = new Grad("Beograd");
		Teretana tr = new Teretana("Teretana test", "Adresa test", g);
		
		Trener t = new Trener("Test", "Test", tr);
		
		assertEquals(true, c.sacuvajUJSONTrener(t));
	}

}
