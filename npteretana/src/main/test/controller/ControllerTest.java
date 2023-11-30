/**
 * 
 */
package controller;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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
		//c.setTest(true);
		c.getDbCon().getConn().getConnection();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
		clearTable("individualni_trening");
        clearTable("trener");
        clearTable("oprema");
        clearTable("clanarina");
        clearTable("ocena");
        clearTable("teretana");
        clearTable("nalog");
		
		
		
		c.getDbCon().getConn().closeConnection();
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
	
	@Test
	void testLoginNepostojeciNalog() throws Exception {
		String korIme = "test123";
		String sifra = "test123";
		Nalog n = new Nalog(korIme, sifra);
		n.setIme("Test");
		n.setPrezime("Test");		
		
		Exception e = assertThrows(Exception.class,
				() ->c.login(n));
		
		assertEquals("Unknown user!", e.getMessage());
	}
	
	@Test
	void testLoginNull() throws Exception {
		Exception e = assertThrows(Exception.class,
				() ->c.login(null));
		
		assertEquals("Unknown user!", e.getMessage());
	}

	/**
	 * Test method for {@link controller.Controller#vratiSveNaloge()}.
	 * @throws Exception 
	 */
	@Test
	void testVratiSveNaloge() throws Exception {
		Nalog n1 = new Nalog("Test1", "Test1", "test1", "test1");
		Nalog n2 = new Nalog("Test2", "Test2", "test2", "test2");
		Nalog n3 = new Nalog("Test3", "Test3", "test3", "test3");
		
		c.zapamtiNalog(n1);
		c.zapamtiNalog(n2);
		c.zapamtiNalog(n3);
		
		List<Nalog> nalozi = new ArrayList<>();
		nalozi.add(n1);
		nalozi.add(n2);
		nalozi.add(n3);
		
		List<Nalog> naloziDb = c.vratiSveNaloge();
		
		assertEquals(nalozi, naloziDb);
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
		c.sacuvajNalog(n);
		
		List<Nalog> nalozi = c.vratiSveNaloge();
		Nalog n1 = nalozi.get(0);
		
		assertEquals(n, n1);
	}
	
	@Test
	void testSacuvajNalogNull() throws Exception {
		String korIme = "test123";
		String sifra = "test123";
		Nalog n = new Nalog(korIme, sifra);
		n.setIme("Test");
		n.setPrezime("Test");
		c.zapamtiNalog(n);
		
		n.setKorisnickoIme("test111");		
		Exception e = assertThrows(Exception.class,
				() ->c.sacuvajNalog(null));
		
		assertEquals("Neuspesno cuvanje izmena naloga!", e.getMessage());
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
		c.zapamtiNalog(n);
		
		List<Nalog> nalozi = c.vratiSveNaloge();
		Nalog n1 = nalozi.get(0);
		
		assertEquals(n, n1);
	}
	
	@Test
	void testZapamtiNalogNull() throws Exception {
		Exception e = assertThrows(Exception.class,
				() ->c.zapamtiNalog(null));
		
		assertEquals("Neuspesno cuvanje naloga!", e.getMessage());
	}

	/**
	 * Test method for {@link controller.Controller#vratiSveGradove()}.
	 * @throws Exception 
	 */
	@Test
	void testVratiSveGradove() throws Exception {
		Grad g1 = new Grad(1, "Beograd");
		Grad g2 = new Grad(2, "Novi Sad");
		Grad g3 = new Grad(3, "Nis");
		
		List<Grad> gradovi = new ArrayList<>();
		gradovi.add(g1);
		gradovi.add(g2);
		gradovi.add(g3);
		
		List<Grad> gradoviDb = c.vratiSveGradove();
		
		assertEquals(gradovi, gradoviDb);
	}

	/**
	 * Test method for {@link controller.Controller#vratiSveTeretane()}.
	 * @throws Exception 
	 */
	@Test
	void testVratiSveTeretane() throws Exception {
		Grad g = new Grad(1, "Beograd");
		Teretana t1 = new Teretana("Teretana 1", "Adresa 1", g);
		c.zapamtiTeretanu(t1);
		Teretana t2 = new Teretana("Teretana 2", "Adresa 2", g);
		c.zapamtiTeretanu(t2);
		Teretana t3 = new Teretana("Teretana 3", "Adresa 3", g);
		c.zapamtiTeretanu(t3);
		
		List<Teretana> list = new ArrayList<>();
		list.add(t1);
		list.add(t2);
		list.add(t3);
		
		List<Teretana> listDb = c.vratiSveTeretane();
		
		assertEquals(list, listDb);
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
		c.zapamtiTeretanu(t);
		
		List<Teretana> list = c.vratiSveTeretane();
		
		assertEquals(t, list.get(0));
	}
	
	@Test
	void testZapamtiTeretanuNull() throws Exception {		
		Exception e = assertThrows(Exception.class,
				() ->c.zapamtiTeretanu(null));
		
		assertEquals("Neuspesno cuvanje teretane!", e.getMessage());
	}
	
	@Test
	void testZapamtiTeretanuNepostojeciGrad() throws Exception {
		Teretana t = new Teretana();
		Grad g = new Grad(20, "Subotica");
		
		t.setGrad(g);
		t.setNaziv("Teretana test");
		t.setAdresa("Adresa test");		
		Exception e = assertThrows(Exception.class,
				() ->c.zapamtiTeretanu(t));
		
		assertEquals("Neuspesno cuvanje teretane!", e.getMessage());
	}

	/**
	 * Test method for {@link controller.Controller#nadjiTeretane(domain.Grad)}.
	 * @throws Exception 
	 */
	@Test
	void testNadjiTeretane() throws Exception {
		Grad g = new Grad(1, "Beograd");
		Grad g1 = new Grad(2, "Novi Sad");
		Teretana t = new Teretana();
		
		t.setGrad(g);
		t.setNaziv("Teretana test");
		t.setAdresa("Adresa test");
		c.zapamtiTeretanu(t);
		
		Teretana t1 = new Teretana("Teretana 1", "Adresa 1", g);
		c.zapamtiTeretanu(t1);
		
		Teretana t2 = new Teretana("Teretana 2", "Adresa 2", g1);
		c.zapamtiTeretanu(t2);
		
		List<Teretana> list = new ArrayList<>();
		list.add(t);
		list.add(t1);
		
		List<Teretana> listDb = c.nadjiTeretane(g);
		
		assertEquals(list, listDb);
	}
	
	@Test
	void testNadjiTeretaneNull() throws Exception {
		Exception e = assertThrows(Exception.class,
				() ->c.nadjiTeretane(null));
		
		assertEquals("Grad je null!", e.getMessage());
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
		
		assertEquals(t, t1);		
	}
	
	@Test
	void testUcitajTeretanuNull() throws Exception {		
		Exception e = assertThrows(Exception.class,
				() ->c.ucitajTeretanu(null));
		
		assertEquals("Teretana je null!", e.getMessage());
	}

	/**
	 * Test method for {@link controller.Controller#vratiSveClanarine()}.
	 * @throws Exception 
	 */
	@Test
	void testVratiSveClanarine() throws Exception {
		Grad g = new Grad(1, "Beograd");
		Teretana t1 = new Teretana("Teretana 1", "Adresa 1", g);
		c.zapamtiTeretanu(t1);
		Teretana t2 = new Teretana("Teretana 2", "Adresa 2", g);
		c.zapamtiTeretanu(t2);
		Teretana t3 = new Teretana("Teretana 3", "Adresa 3", g);
		c.zapamtiTeretanu(t3);
		
		Nalog n1 = new Nalog("Test1", "Test1", "test1", "test1");
		Nalog n2 = new Nalog("Test2", "Test2", "test2", "test2");
		Nalog n3 = new Nalog("Test3", "Test3", "test3", "test3");
		c.zapamtiNalog(n1);
		c.zapamtiNalog(n2);
		c.zapamtiNalog(n3);
		
		LocalDate ld = LocalDate.parse("2023-09-10", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		LocalDate ld1 = LocalDate.parse("2023-10-10", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		
		Clanarina c1 = new Clanarina(BigDecimal.valueOf(2500), n1, t1, ld, ld1);
		Clanarina c2 = new Clanarina(BigDecimal.valueOf(2500), n2, t2, ld, ld1);
		Clanarina c3 = new Clanarina(BigDecimal.valueOf(2500), n3, t3, ld, ld1);
		c.zapamtiClanarinu(c1);
		c.zapamtiClanarinu(c2);
		c.zapamtiClanarinu(c3);
		
		
		List<Clanarina> list = new ArrayList<>();
		list.add(c1);
		list.add(c2);
		list.add(c3);
		
		List<Clanarina> listDb = c.vratiSveClanarine();
		
		assertEquals(list, listDb);
	}

	/**
	 * Test method for {@link controller.Controller#vratiSveClanarine(domain.Nalog)}.
	 * @throws Exception 
	 */
	@Test
	void testVratiSveClanarineNalog() throws Exception {	
		Grad g = new Grad(1, "Beograd");
		Teretana t1 = new Teretana("Teretana 1", "Adresa 1", g);
		c.zapamtiTeretanu(t1);
		Teretana t2 = new Teretana("Teretana 2", "Adresa 2", g);
		c.zapamtiTeretanu(t2);
		Teretana t3 = new Teretana("Teretana 3", "Adresa 3", g);
		c.zapamtiTeretanu(t3);
		
		Nalog n1 = new Nalog("Test1", "Test1", "test1", "test1");
		Nalog n2 = new Nalog("Test2", "Test2", "test2", "test2");
		c.zapamtiNalog(n1);
		c.zapamtiNalog(n2);
		
		LocalDate ld = LocalDate.parse("2023-09-10", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		LocalDate ld1 = LocalDate.parse("2023-10-10", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		
		Clanarina c1 = new Clanarina(BigDecimal.valueOf(2500), n1, t1, ld, ld1);
		Clanarina c2 = new Clanarina(BigDecimal.valueOf(2500), n1, t2, ld, ld1);
		Clanarina c3 = new Clanarina(BigDecimal.valueOf(2500), n2, t3, ld, ld1);
		c.zapamtiClanarinu(c1);
		c.zapamtiClanarinu(c2);
		c.zapamtiClanarinu(c3);
		
		
		List<Clanarina> list = new ArrayList<>();
		list.add(c1);
		list.add(c2);
		
		List<Clanarina> listDb = c.vratiSveClanarine(n1);
		
		assertEquals(list, listDb);
	}
	
	@Test
	void testVratiSveClanarineNalogNull() throws Exception {		
		Exception e = assertThrows(Exception.class,
				() ->c.vratiSveClanarine(null));
		
		assertEquals("Nalog ne sme biti null!", e.getMessage());
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
		c.sacuvajClanarinu(cl);
		
		List<Clanarina> list = c.vratiSveClanarine();
		
		assertEquals(cl, list.get(0));
	}
	
	@Test
	void testSacuvajClanarinuNull() throws Exception {		
		Exception e = assertThrows(Exception.class,
				() ->c.sacuvajClanarinu(null));
		
		assertEquals("Izmena clanarine se ne moze sacuvati!", e.getMessage());
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
		c.zapamtiClanarinu(cl);
		
		List<Clanarina> list = c.vratiSveClanarine();
		
		assertEquals(cl, list.get(0));
	}
	
	@Test
	void testZapamtiClanarinuNull() throws Exception {		
		Exception e = assertThrows(Exception.class,
				() ->c.zapamtiClanarinu(null));
		
		assertEquals("Clanarina se ne moze sacuvati!", e.getMessage());
	}
	
	@Test
	void testZapamtiClanarinuNepostojeciNalog() throws Exception {
		String korIme = "test123";
		String sifra = "test123";
		Nalog n = new Nalog(korIme, sifra);
		n.setIme("Test");
		n.setPrezime("Test");
		n.setId(0);
		
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
		
		Exception e = assertThrows(Exception.class,
				() ->c.zapamtiClanarinu(null));
		
		assertEquals("Clanarina se ne moze sacuvati!", e.getMessage());
	}
	
	@Test
	void testZapamtiClanarinuNepostojecaTeretana() throws Exception {
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
		t.setId(0);
		
		Clanarina cl = new Clanarina();
		LocalDate ld = LocalDate.parse("2023-09-10", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		LocalDate ld1 = LocalDate.parse("2023-10-10", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		cl.setNalog(n);
		cl.setTeretana(t);
		cl.setCena(BigDecimal.valueOf(2500));
		cl.setDatumOd(ld);
		cl.setDatumDo(ld1);
		
		Exception e = assertThrows(Exception.class,
				() ->c.zapamtiClanarinu(null));
		
		assertEquals("Clanarina se ne moze sacuvati!", e.getMessage());
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
		assertEquals(cl, cl1);
	}

	/**
	 * Test method for {@link controller.Controller#vratiSveOcene(domain.Nalog)}.
	 * @throws Exception 
	 */
	@Test
	void testVratiSveOcene() throws Exception {
		Grad g = new Grad(1, "Beograd");
		Teretana t1 = new Teretana("Teretana 1", "Adresa 1", g);
		c.zapamtiTeretanu(t1);
		Teretana t2 = new Teretana("Teretana 2", "Adresa 2", g);
		c.zapamtiTeretanu(t2);
		Teretana t3 = new Teretana("Teretana 3", "Adresa 3", g);
		c.zapamtiTeretanu(t3);
		
		Nalog n1 = new Nalog("Test1", "Test1", "test1", "test1");
		Nalog n2 = new Nalog("Test2", "Test2", "test2", "test2");
		c.zapamtiNalog(n1);
		c.zapamtiNalog(n2);
		
		Ocena o1 = new Ocena(n1, t1, 4);
		Ocena o2 = new Ocena(n1, t2, 4);
		Ocena o3 = new Ocena(n2, t3, 4);
		
		c.dodajOcenu(o1);
		c.dodajOcenu(o2);
		c.dodajOcenu(o3);
		
		
		List<Ocena> list = new ArrayList<>();
		list.add(o1);
		list.add(o2);
		
		List<Ocena> listDb = c.vratiSveOcene(n1);
		
		assertEquals(list, listDb);
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
		c.urediOcenu(o);
		
		List<Ocena> list = c.vratiSveOcene(n);
		
		assertEquals(o, list.get(0));
	}
	
	@Test
	void testUrediOcenuNull() throws Exception {		
		Exception e = assertThrows(Exception.class,
				() ->c.urediOcenu(null));
		
		assertEquals("Izmena ocene se ne moze sacuvati!", e.getMessage());
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
		c.dodajOcenu(o);
		
		List<Ocena> list = c.vratiSveOcene(n);
		
		assertEquals(o, list.get(0));
	}
	
	@Test
	void testDodajOcenuNull() throws Exception {
		Exception e = assertThrows(Exception.class,
				() ->c.dodajOcenu(null));
		
		assertEquals("Ocena se ne moze sacuvati!", e.getMessage());
	}
	
	@Test
	void testDodajOcenuNepostojeciNalog() throws Exception {
		String korIme = "test123";
		String sifra = "test123";
		Nalog n = new Nalog(korIme, sifra);
		n.setIme("Test");
		n.setPrezime("Test");
		n.setId(0);
		
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
		
		Exception e = assertThrows(Exception.class,
				() ->c.dodajOcenu(null));
		
		assertEquals("Ocena se ne moze sacuvati!", e.getMessage());
	}
	
	@Test
	void testDodajOcenuNepostojecaTeretana() throws Exception {
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
		t.setId(0);
		
		Ocena o = new Ocena();
		o.setNalog(n);
		o.setTeretana(t);
		o.setVrednost(5);
		
		Exception e = assertThrows(Exception.class,
				() ->c.dodajOcenu(null));
		
		assertEquals("Ocena se ne moze sacuvati!", e.getMessage());
	}


	/**
	 * Test method for {@link controller.Controller#vratiSveVrsteOpreme()}.
	 * @throws Exception 
	 */
	@Test
	void testVratiSveVrsteOpreme() throws Exception {
		VrstaOpreme vo = new VrstaOpreme(2, "Traka za trcanje");
		VrstaOpreme vo1 = new VrstaOpreme(3, "Tegovi");
		List<VrstaOpreme> list = new ArrayList<>();
		list.add(vo);
		list.add(vo1);
		
		List<VrstaOpreme> listDb = c.vratiSveVrsteOpreme();
		assertEquals(list, listDb);
	}

	/**
	 * Test method for {@link controller.Controller#dodajOpremu(domain.Oprema)}.
	 * @throws Exception 
	 */
	@Test
	void testDodajOpremu() throws Exception {
		Grad g = new Grad(1, "Beograd");
		Teretana t = new Teretana("Teretana test", "Adresa test", g);
		c.zapamtiTeretanu(t);
		
		VrstaOpreme vo = new VrstaOpreme(2, "Traka za trcanje");
		
		Oprema o = new Oprema("Dobro", vo, t);
		c.dodajOpremu(o);
		
		List<Oprema> list = c.vratiSveOpreme();
		
		assertEquals(o, list.get(0));
	}
	
	@Test
	void testDodajOpremuNull() throws Exception {
		Exception e = assertThrows(Exception.class,
				() ->c.dodajOpremu(null));
		
		assertEquals("Oprema se ne moze sacuvati!", e.getMessage());
	}
	
	@Test
	void testDodajOpremuNepostojecaTeretana() throws Exception {
		Grad g = new Grad(1, "Beograd");
		Teretana t = new Teretana("Teretana test", "Adresa test", g);
		t.setId(0);
		
		VrstaOpreme vo = new VrstaOpreme(2, "Traka za trcanje");
		
		Oprema o = new Oprema("Dobro", vo, t);
		
		Exception e = assertThrows(Exception.class,
				() ->c.dodajOpremu(null));
		
		assertEquals("Oprema se ne moze sacuvati!", e.getMessage());
	}
	
	@Test
	void testDodajOpremuNepostojecaVrstaOpreme() throws Exception {
		Grad g = new Grad(1, "Beograd");
		Teretana t = new Teretana("Teretana test", "Adresa test", g);
		c.zapamtiTeretanu(t);
		
		VrstaOpreme vo = new VrstaOpreme(0, "Traka za trcanje");
		
		Oprema o = new Oprema("Dobro", vo, t);

		Exception e = assertThrows(Exception.class,
				() ->c.dodajOpremu(null));
		
		assertEquals("Oprema se ne moze sacuvati!", e.getMessage());
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
		
		VrstaOpreme vo = new VrstaOpreme(2, "Traka za trcanje");
		
		String stanje = "Dobro";
		Oprema o = new Oprema(stanje, vo, t);
		c.dodajOpremu(o);
		o.setStanjeOpreme("Srednje");		
		c.urediOpremu(o);
		
		List<Oprema> list = c.vratiSveOpreme();
		
		assertEquals(o, list.get(0));	
	}
	
	@Test
	void testUrediOpremuNull() throws Exception {		
		Exception e = assertThrows(Exception.class,
				() ->c.urediOpremu(null));
		
		assertEquals("Oprema ne sme biti null!", e.getMessage());
	}

	/**
	 * Test method for {@link controller.Controller#nadjiOpreme(domain.Teretana)}.
	 * @throws Exception 
	 */
	@Test
	void testNadjiOpremeTeretana() throws Exception {
		Grad g = new Grad(1, "Beograd");
		Teretana t = new Teretana("Teretana test", "Adresa test", g);
		c.zapamtiTeretanu(t);
		Teretana t1 = new Teretana("Teretana 1 test", "Adresa 1 test", g);
		c.zapamtiTeretanu(t1);
		
		VrstaOpreme vo = new VrstaOpreme(2, "Traka za trcanje");
		
		Oprema o1 = new Oprema("Dobro", vo, t);
		c.dodajOpremu(o1);
		Oprema o2 = new Oprema("Lose", vo, t);
		c.dodajOpremu(o2);
		Oprema o3 = new Oprema("Dobro", vo, t1);
		c.dodajOpremu(o3);
		
		List<Oprema> list = new ArrayList<>();
		list.add(o1);
		list.add(o2);
		
		List<Oprema> listDb = c.nadjiOpreme(t);
		c.nadjiOpreme(t1);
		
		assertEquals(list, listDb);
	}
	
	@Test
	void testNadjiOpremeTeretanaNull() throws Exception {	
		Teretana t = null;
		Exception e = assertThrows(Exception.class,
				() ->c.nadjiOpreme(t));
		
		assertEquals("Teretana ne sme biti null", e.getMessage());
	}

	/**
	 * Test method for {@link controller.Controller#nadjiOpreme(domain.VrstaOpreme)}.
	 * @throws Exception 
	 */
	@Test
	void testNadjiOpremeVrstaOpreme() throws Exception {
		Grad g = new Grad(1, "Beograd");
		Teretana t = new Teretana("Teretana test", "Adresa test", g);
		c.zapamtiTeretanu(t);
		
		VrstaOpreme vo = new VrstaOpreme(2, "Traka za trcanje");
		VrstaOpreme vo1 = new VrstaOpreme(3, "Tegovi");
		
		Oprema o1 = new Oprema("Dobro", vo, t);
		c.dodajOpremu(o1);
		Oprema o2 = new Oprema("Lose", vo, t);
		c.dodajOpremu(o2);
		Oprema o3 = new Oprema("Dobro", vo1, t);
		c.dodajOpremu(o3);
		
		List<Oprema> list = new ArrayList<>();
		list.add(o1);
		list.add(o2);
		
		List<Oprema> listDb = c.nadjiOpreme(vo);
		
		assertEquals(list, listDb);
	}
	
	@Test
	void testNadjiOpremeVrstaOpremeNull() throws Exception {	
		VrstaOpreme vo = null;
		Exception e = assertThrows(Exception.class,
				() ->c.nadjiOpreme(vo));
		
		assertEquals("Vrsta opreme ne sme biti null", e.getMessage());
	}

	/**
	 * Test method for {@link controller.Controller#vratiSveOpreme()}.
	 * @throws Exception 
	 */
	@Test
	void testVratiSveOpreme() throws Exception {
		Grad g = new Grad(1, "Beograd");
		Teretana t1 = new Teretana("Teretana 1", "Adresa 1", g);
		c.zapamtiTeretanu(t1);
		Teretana t2 = new Teretana("Teretana 2", "Adresa 2", g);
		c.zapamtiTeretanu(t2);
		Teretana t3 = new Teretana("Teretana 3", "Adresa 3", g);
		c.zapamtiTeretanu(t3);
		
		VrstaOpreme vo = new VrstaOpreme(2, "Traka za trcanje");
		
		Oprema o1 = new Oprema("Dobro", vo, t1);
		Oprema o2 = new Oprema("Dobro", vo, t2);
		Oprema o3 = new Oprema("Dobro", vo, t3);
		
		c.dodajOpremu(o1);
		c.dodajOpremu(o2);
		c.dodajOpremu(o3);
		
		
		List<Oprema> list = new ArrayList<>();
		list.add(o1);
		list.add(o2);
		list.add(o3);
		
		List<Oprema> listDb = c.vratiSveOpreme();
		
		assertEquals(list, listDb);
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
		c.dodajTrenera(t);
		
		List<Trener> list = c.nadjiTrenere(tr);
		assertEquals(t, list.get(0));
	}
	
	@Test
	void testDodajTreneraNull() throws Exception {
		Exception e = assertThrows(Exception.class,
				() ->c.dodajTrenera(null));
		
		assertEquals("Trener se ne moze sacuvati!", e.getMessage());
	}
	
	@Test
	void testDodajTreneraNepostojecaTeretana() throws Exception {
		Grad g = new Grad(1, "Beograd");
		Teretana tr = new Teretana("Teretana test", "Adresa test", g);
		tr.setId(0);
		
		Trener t = new Trener("Test", "Test", tr);
		
		Exception e = assertThrows(Exception.class,
				() ->c.dodajTrenera(t));
		
		assertEquals("Trener se ne moze sacuvati!", e.getMessage());
	}

	/**
	 * Test method for {@link controller.Controller#urediTrenera(domain.Trener)}.
	 * @throws Exception 
	 */
	@Test
	void testUrediTreneraIme() throws Exception {
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
		c.urediTrenera(t);
		
		List<Trener> list = c.nadjiTrenere(tr);
		assertEquals(t, list.get(0));
	}
	
	@Test
	void testUrediTreneraTeretana() throws Exception {
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

		Teretana tr1 = new Teretana();		
		tr1.setGrad(g);
		tr1.setNaziv("Teretana 1 test");
		tr1.setAdresa("Adresa 1 test");
		c.zapamtiTeretanu(tr1);
		t.setTeretana(tr1);
		
		c.urediTrenera(t);
		
		List<Trener> list = c.nadjiTrenere(tr1);
		assertEquals(t, list.get(0));
	}
	
	@Test
	void testUrediTreneraNull() throws Exception {
		Exception e = assertThrows(Exception.class,
				() ->c.urediTrenera(null));
		
		assertEquals("Trener ne sme biti null!", e.getMessage());
	}
	
	@Test
	void testUrediTreneraNepostojecaTeretana() throws Exception {
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

		Teretana tr1 = new Teretana();		
		tr1.setGrad(g);
		tr1.setNaziv("Teretana 1 test");
		tr1.setAdresa("Adresa 1 test");
		t.setTeretana(tr1);
		
		Exception e = assertThrows(Exception.class,
				() ->c.urediTrenera(t));
		
		assertEquals("Neuspesno cuvanje izmene trenera!", e.getMessage());
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
		
		Teretana tr1 = new Teretana("Teretana 1 test", "Adresa 1 test", g);
		c.zapamtiTeretanu(tr1);
		
		String ime = "Test";
		String prezime = "Test";
		Trener t = new Trener(ime, prezime, tr);
		c.dodajTrenera(t);
		
		Trener t1 = new Trener("Test 1", "Test 1", tr);
		c.dodajTrenera(t1);		
		Trener t2 = new Trener("Test 2", "Test 2", tr1);
		c.dodajTrenera(t2);
		
		
		List<Trener> list = new ArrayList<>();
		list.add(t);
		list.add(t1);
		
		List<Trener> listDb = c.nadjiTrenere(tr);
		
		assertEquals(list, listDb);
	}
	
	@Test
	void testNadjiTrenereNull() throws Exception {
		Exception e = assertThrows(Exception.class,
				() ->c.nadjiTrenere(null));
		
		assertEquals("Teretana ne sme biti null!", e.getMessage());
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
		c.dodajIndividualniTrening(it);
		
		List<IndividualniTrening> list = c.vratiSveIndividualneTreninge(n);
		
		assertEquals(it, list.get(0));
	}
	
	@Test
	void testDodajIndividualniTreningNull() throws Exception {
		Exception e = assertThrows(Exception.class,
				() ->c.dodajIndividualniTrening(null));
		
		assertEquals("Individualni trening se ne moze sacuvati!", e.getMessage());
	}
	
	@Test
	void testDodajIndividualniTreningNepostojecaTrener() throws Exception {
		Grad g = new Grad(1, "Beograd");
		Teretana tr = new Teretana();		
		tr.setGrad(g);
		tr.setNaziv("Teretana test");
		tr.setAdresa("Adresa test");
		c.zapamtiTeretanu(tr);
		
		String ime = "Test";
		String prezime = "Test";
		Trener t = new Trener(ime, prezime, tr);
		t.setId(0);
		
		String korIme = "test123";
		String sifra = "test123";
		Nalog n = new Nalog(korIme, sifra);
		n.setIme("Test");
		n.setPrezime("Test");
		c.zapamtiNalog(n);
		
		LocalDate ld = LocalDate.parse("2024-10-10", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		IndividualniTrening it = new IndividualniTrening(n, t, ld);
		
		Exception e = assertThrows(Exception.class,
				() ->c.dodajIndividualniTrening(it));
		
		assertEquals("Individualni trening se ne moze sacuvati!", e.getMessage());
	}
	
	@Test
	void testDodajIndividualniTreningNepostojecaNalog() throws Exception {
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
		n.setId(0);
		
		LocalDate ld = LocalDate.parse("2024-10-10", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		IndividualniTrening it = new IndividualniTrening(n, t, ld);
		
		Exception e = assertThrows(Exception.class,
				() ->c.dodajIndividualniTrening(it));
		
		assertEquals("Individualni trening se ne moze sacuvati!", e.getMessage());
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
		
		Trener t = new Trener("Test", "Test", tr);
		c.dodajTrenera(t);
		
		Trener t1 = new Trener("Test 1", "Test 1", tr);
		c.dodajTrenera(t1);
		
		String korIme = "test123";
		String sifra = "test123";
		Nalog n = new Nalog(korIme, sifra);
		n.setIme("Test");
		n.setPrezime("Test");
		c.zapamtiNalog(n);
		
		Nalog n1 = new Nalog("test1234", "test1234");
		n1.setIme("Test1");
		n1.setPrezime("Test1");
		c.zapamtiNalog(n1);
		
		LocalDate ld = LocalDate.parse("2024-10-10", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		IndividualniTrening it = new IndividualniTrening(n, t, ld);
		c.dodajIndividualniTrening(it);
		IndividualniTrening it1 = new IndividualniTrening(n1, t, ld);
		c.dodajIndividualniTrening(it1);
		IndividualniTrening it2 = new IndividualniTrening(n, t1, ld);
		c.dodajIndividualniTrening(it2);
		
		List<IndividualniTrening> list = new ArrayList<>();
		list.add(it);
		list.add(it1);
		
		List<IndividualniTrening> listDb = c.vratiSveIndividualneTreninge(t);
		
		assertEquals(list, listDb);
	}
	
	@Test
	void testVratiSveIndividualneTreningeTrenerNull() throws Exception {
		Trener t = null;
		Exception e = assertThrows(Exception.class,
				() ->c.vratiSveIndividualneTreninge(t));
		
		assertEquals("Trener ne sme biti null!", e.getMessage());
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
		
		Teretana tr1 = new Teretana();		
		tr1.setGrad(g);
		tr1.setNaziv("Teretana 1 test");
		tr1.setAdresa("Adresa 1 test");
		c.zapamtiTeretanu(tr1);
				
		Trener t = new Trener("Test", "Test", tr);
		c.dodajTrenera(t);
		
		Trener t1 = new Trener("Test 1", "Test 1", tr);
		c.dodajTrenera(t1);
		
		String korIme = "test123";
		String sifra = "test123";
		Nalog n = new Nalog(korIme, sifra);
		n.setIme("Test");
		n.setPrezime("Test");
		c.zapamtiNalog(n);
		
		Nalog n1 = new Nalog("test1234", "test1234");
		n1.setIme("Test1");
		n1.setPrezime("Test1");
		c.zapamtiNalog(n1);
		
		LocalDate ld = LocalDate.parse("2024-10-10", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		IndividualniTrening it = new IndividualniTrening(n, t, ld);
		c.dodajIndividualniTrening(it);
		IndividualniTrening it1 = new IndividualniTrening(n, t1, ld);
		c.dodajIndividualniTrening(it1);
		IndividualniTrening it2 = new IndividualniTrening(n1, t, ld);
		c.dodajIndividualniTrening(it2);
		
		List<IndividualniTrening> list = new ArrayList<>();
		list.add(it);
		list.add(it1);
		
		List<IndividualniTrening> listDb = c.vratiSveIndividualneTreninge(n);
		
		assertEquals(list, listDb);
	}
	
	@Test
	void testVratiSveIndividualneTreningeNalogNull() throws Exception {
		Nalog n = null;
		Exception e = assertThrows(Exception.class,
				() ->c.vratiSveIndividualneTreninge(n));
		
		assertEquals("Nalog ne sme biti null!", e.getMessage());
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
	
	
	private void clearTable(String tableName) {
		String deleteQuery = "DELETE FROM " + tableName;		
		
		try (PreparedStatement preparedStatement = c.getDbCon().getConn().getConnection().prepareStatement(deleteQuery)) {
		    preparedStatement.executeUpdate();
		    c.getDbCon().getConn().getConnection().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

}
