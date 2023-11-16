/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domain.Clanarina;
import domain.Grad;
import domain.IndividualniTrening;
import domain.Nalog;
import domain.Ocena;
import domain.Oprema;
import domain.Teretana;
import domain.Trener;
import domain.VrstaOpreme;

import java.io.FileWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import db.DbConnectionFactory;

/**
 * Klasa Controller sadrzi logiku povezivanja GUI-a sa DbConnectionFactroy
 * 
 * @author Luka
 */
public class Controller {

    /**
     * Instanca klase DbConnectionFactory
     */
    private final DbConnectionFactory dbCon;

    /**
     * Instanca Controller 
     */
    private static Controller controller;
    
    /**
     * Pokazatelj da li je instanca objekta sacuvana u bazu ili ne
     * Pocetna vrednost je false
     */
    boolean sacuvan = false;

    private Controller() {
        this.dbCon = new DbConnectionFactory();
    }

    /**
     * Vraca instancu Controller-a
     * 
     * @return instanca Controller
     */
    public static Controller getInstance() {
        if (controller == null) {
            controller = new Controller();
        }
        return controller;
    }
    
    public DbConnectionFactory getDbCon() {
		return dbCon;
	}

	/**
     * Vrsi login korisnika u aplikaciju
     * 
     * @param korisnickoIme korisnicko ime naloga
     * @param sifra sifra naloga
     * @return nalog koji se ulogovao
     * @throws Exception 
     */
    public Nalog login(Nalog n) throws Exception {
        List<Nalog> nalozi = dbCon.vratiSveNaloge();
        for (Nalog nalog : nalozi) {
            if (nalog.getKorisnickoIme().equals(n.getKorisnickoIme()) && nalog.getSifra().equals(n.getSifra())) {
                return nalog;
            }
        }
        throw new Exception("Unknown user!");
    }   
    
    
    /**
     * Vraca listu svih naloga iz baze podataka
     * 
     * @return lista svih naloga iz baze podataka
     * @throws Exception 
     */
    public List<Nalog> vratiSveNaloge() throws Exception{
        return dbCon.vratiSveNaloge();
    }
    
    /**
     * Cuva izmene postojeceg naloga u bazi podataka
     * 
     * @param n nalog
     * @return 
     * <ul>
	 * 		<li> true - ako je uneti nalog uspesno izmenjen i sacuvan </li>
	 * 		<li> false - ako unet nalog nije uspesno izmenjen i sacuvan </li>
	 * </ul>
     * @throws Exception
     */
    public boolean sacuvajNalog(Nalog n) throws Exception {
        DbConnectionFactory.getInstance().getConnection();
        try {
            dbCon.urediNalog(n);
            DbConnectionFactory.getInstance().getConnection().commit();
            sacuvan = true;
        } catch (Exception e) {
            e.printStackTrace();
            DbConnectionFactory.getInstance().getConnection().rollback();
            throw e;
        } finally {
            DbConnectionFactory.getInstance().getConnection().close();
        }
        return sacuvan;
    }
    
    /**
     * Dodaje novi nalog u bazu podataka
     * 
     * @param n nalog
     * @return
     * <ul>
	 * 		<li> true - ako je uneti nalog uspesno sacuvan </li>
	 * 		<li> false - ako unet nalog nije sacuvan </li>
	 * </ul>
     * @throws Exception
     */
    public boolean zapamtiNalog(Nalog n) throws Exception {
        DbConnectionFactory.getInstance().getConnection();
        try {
            dbCon.dodajNalog(n);
            DbConnectionFactory.getInstance().getConnection().commit();
            sacuvan = true;
        } catch (Exception e) {
            e.printStackTrace();
            DbConnectionFactory.getInstance().getConnection().rollback();
            throw e;
        } finally {
            DbConnectionFactory.getInstance().getConnection().close();
        }
        
        sacuvajUJSONNalog(n);
        
        return sacuvan;
    }    
    
    
    /**
     * Vraca listu svih gradova u bazi podataka
     * 
     * @return lista svih gradova
     * @throws Exception 
     */
    public List<Grad> vratiSveGradove() throws Exception{
        return dbCon.vratiSveGradove();
    }
    
    
    
    /**
     * Vraca listu svih teretana u bazi podataka
     * 
     * @return lista svih teretana
     * @throws Exception 
     */
    public List<Teretana> vratiSveTeretane() throws Exception{
        return dbCon.vratiSveTeretane();
    }
    
    /**
     * Cuva novu teretanu u bazi podataka
     * 
     * @param t teretana
     * @return
     * <ul>
	 * 		<li> true - ako je uneta teretana uspesno sacuvana </li>
	 * 		<li> false - ako uneta teretana nije sacuvana </li>
	 * </ul>
     * @throws Exception
     */
    public boolean zapamtiTeretanu(Teretana t) throws Exception {
        DbConnectionFactory.getInstance().getConnection();
        try {
            dbCon.dodajTeretanu(t);
            DbConnectionFactory.getInstance().getConnection().commit();
            sacuvan = true;
        } catch (Exception e) {
            e.printStackTrace();
            DbConnectionFactory.getInstance().getConnection().rollback();
            throw e;
        } finally {
            DbConnectionFactory.getInstance().getConnection().close();
        }
        
        sacuvajUJSONTeretana(t);
        
        return sacuvan;
    }
    
    /**
     * Pronalazi i vraca listu teretana koje se nalaze u odredjenom gradu
     * 
     * @param grad
     * @return lista teretana iz odredjenog grada
     * @throws Exception 
     */
    public List<Teretana> nadjiTeretane(Grad grad) throws Exception {
        List<Teretana> list = new ArrayList<>();
        DbConnectionFactory.getInstance().getConnection();
        try{
            list = dbCon.vratiSveTeretane(grad);
            DbConnectionFactory.getInstance().getConnection().commit();
        }catch (Exception e){
            e.printStackTrace();
            DbConnectionFactory.getInstance().getConnection().rollback();
            throw e;
        }finally{
            DbConnectionFactory.getInstance().getConnection().close();
        }
        return list;
    }
    
    /**
     * Vraca teretanu sa odredjenim id-em iz baze podataka
     * @param teretana
     * @return teretana
     * @throws Exception 
     */
    public Teretana ucitajTeretanu(Teretana teretana) throws Exception{
        Teretana t = new Teretana();
        t.setId(0);
        
        DbConnectionFactory.getInstance().getConnection();
        try{
            t = dbCon.vratiSveTeretane(teretana);
            DbConnectionFactory.getInstance().getConnection().commit();
        }catch (Exception e){
            e.printStackTrace();
            DbConnectionFactory.getInstance().getConnection().rollback();
            throw e;
        }finally{
            DbConnectionFactory.getInstance().getConnection().close();
        }
        return t;
    }
    
    
    
    /**
     * Vraca listu svih clanarina iz baze podataka
     * 
     * @return lista svih clanarina
     * @throws Exception
     */
    public List<Clanarina> vratiSveClanarine() throws Exception{
        return dbCon.vratiSveClanarine();
    }
    
    /**
     * Vraca sve clanarine koje je odredjeni nalog uplatio
     * 
     * @param n nalog
     * @return lista clanarina jednog naloga
     * @throws Exception 
     */
    public List<Clanarina> vratiSveClanarine(Nalog n) throws Exception{
        return dbCon.vratiSveClanarine(n);
    }
    
    /**
     * Cuva izmenu clanarine, odnosno produzetak postojece clanarine, u bazi podataka
     * 
     * @param c clanarina
     * @return
     * <ul>
	 * 		<li> true - ako je uneta clanarina uspesno izmenjena i sacuvana </li>
	 * 		<li> false - ako uneta clanarina nije uspesno izmenjena i sacuvana </li>
	 * </ul>
     * @throws Exception
     */
    public boolean sacuvajClanarinu(Clanarina c) throws Exception {
        DbConnectionFactory.getInstance().getConnection();
        try {
            dbCon.urediClanarinu(c);
            DbConnectionFactory.getInstance().getConnection().commit();
            sacuvan = true;
        } catch (Exception e) {
            e.printStackTrace();
            DbConnectionFactory.getInstance().getConnection().rollback();
            throw e;
        } finally {
            DbConnectionFactory.getInstance().getConnection().close();
        }
        return sacuvan;
    }
    
    /**
     * Dodaje novu clanarinu u bazu podataka
     * 
     * @param c clanarina
     * @return
     * <ul>
	 * 		<li> true - ako je uneta clanarina uspesno sacuvana </li>
	 * 		<li> false - ako uneta clanarina nije sacuvana </li>
	 * </ul>
     * @throws Exception
     */
    public boolean zapamtiClanarinu(Clanarina c) throws Exception {
        DbConnectionFactory.getInstance().getConnection();
        boolean sacuvan = false;
        try {
           dbCon.dodajClanarinu(c);
           DbConnectionFactory.getInstance().getConnection().commit();
           sacuvan = true;
        } catch (Exception e) {
            e.printStackTrace();
            DbConnectionFactory.getInstance().getConnection().rollback();
            throw e;
        } finally {
            DbConnectionFactory.getInstance().getConnection().close();
        }
        return sacuvan;
    }
    
    /**
     * Trazi i vraca clanarinu po id-u
     * 
     * @param clanarina
     * @return clanarina
     * @throws Exception 
     */
    public Clanarina ucitajClanarinu(Clanarina clanarina) throws Exception{
        Clanarina c = new Clanarina();
        c.setId(0);
        
        DbConnectionFactory.getInstance().getConnection();
        try{
            c = dbCon.vratiSveClanarine(clanarina);
            DbConnectionFactory.getInstance().getConnection().commit();
        }catch (Exception e){
            e.printStackTrace();
            DbConnectionFactory.getInstance().getConnection().rollback();
            throw e;
        }finally{
            DbConnectionFactory.getInstance().getConnection().close();
        }
        return c;
    }
    
    
    
    /**
     * Vraca listu svih ocena koje je odredjeni nalog uneo
     * 
     * @param n nalog
     * @return lista ocena
     * @throws Exception 
     */
    public List<Ocena> vratiSveOcene(Nalog n) throws Exception{
        return dbCon.vratiSveOcene(n);
    }
    
    /**
     * Cuva novu vrednost ocene nakon izmene u bazi podataka
     * 
     * @param o ocena
     * @return
     * <ul>
	 * 		<li> true - ako je uneta ocena uspesno izmenjena i sacuvana </li>
	 * 		<li> false - ako uneta clanarina nije uspesno izmenjena i sacuvana </li>
	 * </ul>
     * @throws Exception
     */
    public boolean urediOcenu(Ocena o) throws Exception {
        DbConnectionFactory.getInstance().getConnection();
        boolean sacuvan = false;
        try {
            dbCon.urediOcenu(o);
            DbConnectionFactory.getInstance().getConnection().commit();
            sacuvan = true;
        } catch (Exception e) {
            e.printStackTrace();
            DbConnectionFactory.getInstance().getConnection().rollback();
            throw e;
        } finally {
            DbConnectionFactory.getInstance().getConnection().close();
        }
        return sacuvan;
    }
    
    /**
     * Dodaje novu ocenu u bazu podataka
     * 
     * @param ocena
     * @return
     * <ul>
	 * 		<li> true - ako je uneta ocena uspesno sacuvana </li>
	 * 		<li> false - ako uneta clanarina nije sacuvana </li>
	 * </ul>
     * @throws Exception
     */
    public boolean dodajOcenu(Ocena ocena) throws Exception {
        DbConnectionFactory.getInstance().getConnection();
        boolean sacuvan = false;
        try {
            dbCon.dodajOcenu(ocena);
            DbConnectionFactory.getInstance().getConnection().commit();
            sacuvan = true;
        } catch (Exception e) {
            e.printStackTrace();
            DbConnectionFactory.getInstance().getConnection().rollback();
            throw e;
        } finally {
            DbConnectionFactory.getInstance().getConnection().close();
        }
        return sacuvan;
    }
    
    /**
     * Vraca listu svih vrsta opreme iz baze podataka
     * 
     * @return lista svih vrsta opreme
     * @throws Exception 
     */
    public List<VrstaOpreme> vratiSveVrsteOpreme() throws Exception{
        return dbCon.vratiSveVrsteOpreme();
    }
    
    
    /**
     * Dodaje novu opremu u bazu podataka
     * 
     * @param o oprema
     * @return
     * <ul>
	 * 		<li> true - ako je uneta oprema uspesno sacuvana </li>
	 * 		<li> false - ako uneta oprema nije sacuvana </li>
	 * </ul>
     * @throws Exception
     */
    public boolean dodajOpremu(Oprema o) throws Exception {
        DbConnectionFactory.getInstance().getConnection();
        boolean sacuvan = false;
        try {
            dbCon.dodajOpremu(o);
            DbConnectionFactory.getInstance().getConnection().commit();
            sacuvan = true;
        } catch (Exception e) {
            e.printStackTrace();
            DbConnectionFactory.getInstance().getConnection().rollback();
            throw e;
        } finally {
            DbConnectionFactory.getInstance().getConnection().close();
        }
        return sacuvan;
    }
    
    /**
     * Uredjuje i cuva promene opreme u bazi podataka
     * 
     * @param o oprema
     * @return
     * <ul>
	 * 		<li> true - ako je uneta oprema uspesno izmenjena i sacuvana </li>
	 * 		<li> false - ako uneta oprema nije uspesno izmenjena i sacuvana </li>
	 * </ul>
     * @throws Exception
     */
/*    public boolean urediOpremu(Oprema o) throws Exception {
        DbConnectionFactory.getInstance().getConnection();
        boolean sacuvan = false;
        try {
            dbCon.urediOpremu(o);
            DbConnectionFactory.getInstance().getConnection().commit();
            sacuvan = true;
        } catch (Exception e) {
            e.printStackTrace();
            DbConnectionFactory.getInstance().getConnection().rollback();
            throw e;
        } finally {
            DbConnectionFactory.getInstance().getConnection().close();
        }
        return sacuvan;
    }*/
    
    /**
     * Vraca listu opreme iz odredjene teretani
     * 
     * @param t teretana
     * @return lista opreme iz odredjene teretane
     * @throws Exception 
     */
    public List<Oprema> nadjiOpreme(Teretana t) throws Exception {
        List<Oprema> list = new ArrayList<>();
        DbConnectionFactory.getInstance().getConnection();
        try{
            list = dbCon.vratiSveOpreme(t);
            DbConnectionFactory.getInstance().getConnection().commit();
        }catch (Exception e){
            e.printStackTrace();
            DbConnectionFactory.getInstance().getConnection().rollback();
            throw e;
        }finally{
            DbConnectionFactory.getInstance().getConnection().close();
        }
        return list;
    }
    
    /**
     * Vraca listu opreme odredjene vrste
     * 
     * @param vo vrsta opreme
     * @return lista opreme odredjene vrste
     * @throws Exception 
     */
    public List<Oprema> nadjiOpreme(VrstaOpreme vo) throws Exception {
        List<Oprema> list = new ArrayList<>();
        DbConnectionFactory.getInstance().getConnection();
        try{
            list = dbCon.vratiSveOpreme(vo);
            DbConnectionFactory.getInstance().getConnection().commit();
        }catch (Exception e){
            e.printStackTrace();
            DbConnectionFactory.getInstance().getConnection().rollback();
            throw e;
        }finally{
            DbConnectionFactory.getInstance().getConnection().close();
        }
        return list;
    } 
    
    /**
     * Vraca listu svih oprema iz baze podataka
     * 
     * @return lista svih oprema iz baze podataka
     * @throws Exception 
     */
    public List<Oprema> vratiSveOpreme() throws Exception{
        return dbCon.vratiSveOpreme();
    }
    
    
    /**
     * Dodaje novog trenera u bazu podataka
     * 
     * @param t trener
     * @return
     * <ul>
	 * 		<li> true - ako je uneti trener uspesno sacuvan </li>
	 * 		<li> false - ako uneti trener nije sacuvan </li>
	 * </ul>
     * @throws Exception
     */
    public boolean dodajTrenera(Trener t) throws Exception {
        DbConnectionFactory.getInstance().getConnection();
        boolean sacuvan = false;
        try {
            dbCon.dodajTrenera(t);
            DbConnectionFactory.getInstance().getConnection().commit();
            sacuvan = true;
        } catch (Exception e) {
            e.printStackTrace();
            DbConnectionFactory.getInstance().getConnection().rollback();
            throw e;
        } finally {
            DbConnectionFactory.getInstance().getConnection().close();
        }
        
        return sacuvan;
    }
    
    
    /**
     * Vrsi i cuva izmenu trenera u bazi podataka
     * 
     * @param t trener
     * @return 
     * <ul>
	 * 		<li> true - ako je uneti trener uspesno izmenjen i sacuvan </li>
	 * 		<li> false - ako uneti trener nije uspesno izmenjen i sacuvan </li>
	 * </ul>
     * @throws Exception
     */
    public boolean urediTrenera(Trener t) throws Exception {
        DbConnectionFactory.getInstance().getConnection();
        boolean sacuvan = false;
        try {
            dbCon.urediTrenera(t);
            DbConnectionFactory.getInstance().getConnection().commit();
            sacuvan = true;
        } catch (Exception e) {
            e.printStackTrace();
            DbConnectionFactory.getInstance().getConnection().rollback();
            throw e;
        } finally {
            DbConnectionFactory.getInstance().getConnection().close();
        }
        return sacuvan;
    }
    
    /**
     * Vraca listu svih trenera iz odredjene teretane
     * 
     * @param teretana
     * @return lista svih trenera iz odredjene teretane
     * @throws Exception 
     */
    public List<Trener> nadjiTrenere(Teretana teretana) throws Exception {
        List<Trener> list = new ArrayList<>();
        DbConnectionFactory.getInstance().getConnection();
        try{
            list = dbCon.vratiSveTrenere(teretana);
            DbConnectionFactory.getInstance().getConnection().commit();
        }catch (Exception e){
            e.printStackTrace();
            DbConnectionFactory.getInstance().getConnection().rollback();
            throw e;
        }finally{
            DbConnectionFactory.getInstance().getConnection().close();
        }
        return list;
    }   
   
    
    
    /**
     * Dodaje novi individualni trening u bazu podataka
     * 
     * @param it individualni trening
     * @return
     * <ul>
	 * 		<li> true - ako je uneti individualni trening uspesno sacuvan </li>
	 * 		<li> false - ako uneti individualni trening nije sacuvan </li>
     * @throws Exception
     */
   public boolean dodajIndividualniTrening(IndividualniTrening it) throws Exception {
        DbConnectionFactory.getInstance().getConnection();
        boolean sacuvan = false;
        try {
            dbCon.dodajIndividualniTrening(it);
            DbConnectionFactory.getInstance().getConnection().commit();
            sacuvan = true;
        } catch (Exception e) {
            e.printStackTrace();
            DbConnectionFactory.getInstance().getConnection().rollback();
            throw e;
        } finally {
            DbConnectionFactory.getInstance().getConnection().close();
        }
        return sacuvan;
    }
    
   /**
    * Vraca listu svih individualnih treninga odredjenog trenera
    * 
    * @param t trener
    * @return lista svih individualnih treninga odredjenog trenera
    * @throws Exception 
    */
    public List<IndividualniTrening> vratiSveIndividualneTreninge(Trener t) throws Exception{
        return dbCon.vratiSveIndividualneTreninge(t);
    }
    
    /**
     * Vraca listu svih individualnih treninga odredjenog nalgoa
     * 
     * @param n nalog
     * @return lista svih individualnih treninga odredjenog nalgoa
     * @throws Exception 
     */
    public List<IndividualniTrening> vratiSveIndividualneTreninge(Nalog n) throws Exception{
    	return dbCon.vratiSveIndividualneTreninge(n);
    }
    
    public boolean sacuvajUJSONNalog(Nalog n) {
    	
    	Gson gson = new GsonBuilder().create();
    	
    	try(FileWriter fw = new FileWriter(String.format("nalog_'%s'", n.getKorisnickoIme()))){
			
			fw.write(gson.toJson(n));
			sacuvan = true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	return sacuvan;
    }
    
	public boolean sacuvajUJSONTeretana(Teretana t) {
	    	
	    	Gson gson = new GsonBuilder().create();
	    	
	    	try(FileWriter fw = new FileWriter(String.format("teretana_'%s'_'%s'_'%s'", t.getNaziv(), t.getGrad(), t.getAdresa()))){
				
				fw.write(gson.toJson(t));
				sacuvan = true;
				
			} catch (Exception e) {
				e.printStackTrace();
			}
	    	
	    	return sacuvan;
	    }
}
