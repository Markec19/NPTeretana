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
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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
    
    
    
    /**
     * Vrsi login korisnika u aplikaciju
     * 
     * @param korisnickoIme korisnicko ime naloga
     * @param sifra sifra naloga
     * @return nalog koji se ulogovao
     * @throws Exception 
     */
    public Nalog login(String korisnickoIme, String sifra) throws Exception {
        List<Nalog> nalozi = dbCon.vratiSveNaloge();
        for (Nalog nalog : nalozi) {
            if (nalog.getKorisnickoIme().equals(korisnickoIme) && nalog.getSifra().equals(sifra)) {
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
    
//    public Nalog kreirajNalog() throws Exception{
//        Nalog n = new Nalog();
//        n.setIme("Unesite ime korisnika");
//        n.setPrezime("Unesite prezime korisnika");
//        n.setKorisnickoIme("Unesite korisničko ime");
//        n.setSifra("Unesite šifru");
//        
//        return n;
//    }
    
    /**
     * Cuva izmene postojeceg naloga u bazi podataka
     * 
     * @param n nalog
     * @throws Exception 
     */
    public void sacuvajNalog(Nalog n) throws Exception {
        DbConnectionFactory.getInstance().getConnection();
        try {
            dbCon.urediNalog(n);
            DbConnectionFactory.getInstance().getConnection().commit();
        } catch (Exception e) {
            e.printStackTrace();
            DbConnectionFactory.getInstance().getConnection().rollback();
            throw e;
        } finally {
            DbConnectionFactory.getInstance().getConnection().close();
        }
    }
    
    /**
     * Dodaje novi nalog u bazu podataka
     * 
     * @param n nalog
     * @throws Exception 
     */
    public void zapamtiNalog(Nalog n) throws Exception {
        DbConnectionFactory.getInstance().getConnection();
        try {
            dbCon.dodajNalog(n);
            DbConnectionFactory.getInstance().getConnection().commit();
        } catch (Exception e) {
            e.printStackTrace();
            DbConnectionFactory.getInstance().getConnection().rollback();
            throw e;
        } finally {
            DbConnectionFactory.getInstance().getConnection().close();
        }
    }
    
    /**
     * Trazi naloge po korisnickom imenu
     * 
     * @param korisnickoIme korisnicko ime naloga
     * @return lista naloga 
     * @throws Exception 
     */
    public List<Nalog> nadjiNaloge(String korisnickoIme) throws Exception {
        List<Nalog> list = new ArrayList<>();
        DbConnectionFactory.getInstance().getConnection();
        try{
            list = dbCon.vratiSveNaloge();
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
     * Pronalazi i vraca nalog sa odgovarajucim id-em naloga
     * 
     * @param nalog
     * @return nalog
     * @throws Exception 
     */
    public Nalog ucitajNalog(Nalog nalog) throws Exception{
        Nalog n = new Nalog();
        n.setId(0);
        
        DbConnectionFactory.getInstance().getConnection();
        try{
            n = dbCon.pronadjiNalog(nalog);
            DbConnectionFactory.getInstance().getConnection().commit();
        }catch (Exception e){
            e.printStackTrace();
            DbConnectionFactory.getInstance().getConnection().rollback();
            throw e;
        }finally{
            DbConnectionFactory.getInstance().getConnection().close();
        }
        return n;
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
        
//    public void sacuvajTeretanu(Teretana t) throws Exception {
//        DbConnectionFactory.getInstance().getConnection();
//        try {
//            dbCon.urediTeretanu(t);
//            DbConnectionFactory.getInstance().getConnection().commit();
//        } catch (Exception e) {
//            e.printStackTrace();
//            DbConnectionFactory.getInstance().getConnection().rollback();
//            throw e;
//        } finally {
//            DbConnectionFactory.getInstance().getConnection().close();
//        }
//    }
    
    /**
     * Cuva novu teretanu u bazi podataka
     * 
     * @param t teretana
     * @throws Exception 
     */
    public void zapamtiTeretanu(Teretana t) throws Exception {
        DbConnectionFactory.getInstance().getConnection();
        try {
            dbCon.dodajTeretanu(t);
            DbConnectionFactory.getInstance().getConnection().commit();
        } catch (Exception e) {
            e.printStackTrace();
            DbConnectionFactory.getInstance().getConnection().rollback();
            throw e;
        } finally {
            DbConnectionFactory.getInstance().getConnection().close();
        }
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
     * @throws Exception 
     */
    public void sacuvajClanarinu(Clanarina c) throws Exception {
        DbConnectionFactory.getInstance().getConnection();
        try {
            dbCon.urediClanarinu(c);
            DbConnectionFactory.getInstance().getConnection().commit();
        } catch (Exception e) {
            e.printStackTrace();
            DbConnectionFactory.getInstance().getConnection().rollback();
            throw e;
        } finally {
            DbConnectionFactory.getInstance().getConnection().close();
        }
    }
    
    /**
     * Dodaje novu clanarinu u bazu podataka
     * 
     * @param c clanarina
     * @throws Exception 
     */
    public void zapamtiClanarinu(Clanarina c) throws Exception {
        DbConnectionFactory.getInstance().getConnection();
        try {
           dbCon.dodajClanarinu(c);
           DbConnectionFactory.getInstance().getConnection().commit();
        } catch (Exception e) {
            e.printStackTrace();
            DbConnectionFactory.getInstance().getConnection().rollback();
            throw e;
        } finally {
            DbConnectionFactory.getInstance().getConnection().close();
        }
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
     * @throws Exception 
     */
    public void urediOcenu(Ocena o) throws Exception {
        DbConnectionFactory.getInstance().getConnection();
        try {
            dbCon.urediOcenu(o);
            DbConnectionFactory.getInstance().getConnection().commit();
        } catch (Exception e) {
            e.printStackTrace();
            DbConnectionFactory.getInstance().getConnection().rollback();
            throw e;
        } finally {
            DbConnectionFactory.getInstance().getConnection().close();
        }
    }
    
    /**
     * Dodaje novu ocenu u bazu podataka
     * 
     * @param ocena
     * @throws Exception 
     */
    public void dodajOcenu(Ocena ocena) throws Exception {
        DbConnectionFactory.getInstance().getConnection();
        try {
            dbCon.dodajOcenu(ocena);
            DbConnectionFactory.getInstance().getConnection().commit();
        } catch (Exception e) {
            e.printStackTrace();
            DbConnectionFactory.getInstance().getConnection().rollback();
            throw e;
        } finally {
            DbConnectionFactory.getInstance().getConnection().close();
        }
    }
        
    
//    public void dodajVrstuOpreme(VrstaOpreme vrstaOpreme) throws Exception {
//        DbConnectionFactory.getInstance().getConnection();
//        try {
//            dbCon.dodajVrstuOpreme(vrstaOpreme);
//            DbConnectionFactory.getInstance().getConnection().commit();
//        } catch (Exception e) {
//            e.printStackTrace();
//            DbConnectionFactory.getInstance().getConnection().rollback();
//            throw e;
//        } finally {
//            DbConnectionFactory.getInstance().getConnection().close();
//        }
//    }
    
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
     * @throws Exception 
     */
    public void dodajOpremu(Oprema o) throws Exception {
        DbConnectionFactory.getInstance().getConnection();
        try {
            dbCon.dodajOpremu(o);
            DbConnectionFactory.getInstance().getConnection().commit();
        } catch (Exception e) {
            e.printStackTrace();
            DbConnectionFactory.getInstance().getConnection().rollback();
            throw e;
        } finally {
            DbConnectionFactory.getInstance().getConnection().close();
        }
    }
    
    /**
     * Uredjuje i cuva promene opreme u bazi podataka
     * 
     * @param o oprema
     * @throws Exception 
     */
    public void urediOpremu(Oprema o) throws Exception {
        DbConnectionFactory.getInstance().getConnection();
        try {
            dbCon.urediOpremu(o);
            DbConnectionFactory.getInstance().getConnection().commit();
        } catch (Exception e) {
            e.printStackTrace();
            DbConnectionFactory.getInstance().getConnection().rollback();
            throw e;
        } finally {
            DbConnectionFactory.getInstance().getConnection().close();
        }
    }
    
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
     * @throws Exception 
     */
    public void dodajTrenera(Trener t) throws Exception {
        DbConnectionFactory.getInstance().getConnection();
        try {
            dbCon.dodajTrenera(t);
            DbConnectionFactory.getInstance().getConnection().commit();
        } catch (Exception e) {
            e.printStackTrace();
            DbConnectionFactory.getInstance().getConnection().rollback();
            throw e;
        } finally {
            DbConnectionFactory.getInstance().getConnection().close();
        }
    }
    
    /**
     * Vrsi i cuva izmenu trenera u bazi podataka
     * 
     * @param t trener
     * @throws Exception 
     */
    public void urediTrenera(Trener t) throws Exception {
        DbConnectionFactory.getInstance().getConnection();
        try {
            dbCon.urediTrenera(t);
            DbConnectionFactory.getInstance().getConnection().commit();
        } catch (Exception e) {
            e.printStackTrace();
            DbConnectionFactory.getInstance().getConnection().rollback();
            throw e;
        } finally {
            DbConnectionFactory.getInstance().getConnection().close();
        }
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
     * @throws Exception 
     */
   public void dodajIndividualniTrening(IndividualniTrening it) throws Exception {
        DbConnectionFactory.getInstance().getConnection();
        try {
            dbCon.dodajIndividualniTrening(it);
            DbConnectionFactory.getInstance().getConnection().commit();
        } catch (Exception e) {
            e.printStackTrace();
            DbConnectionFactory.getInstance().getConnection().rollback();
            throw e;
        } finally {
            DbConnectionFactory.getInstance().getConnection().close();
        }
    }
    
   /**
    * Vraca listu svih individualnih treninga odredjenog trenera
    * 
    * @param t trener
    * @return lista svih individualnih treninga odredjenog trenera
    * @throws Exception 
    */
    public List<IndividualniTrening> vratiSveIndividualneTreninge(Trener t) throws Exception{
        return vratiSveIndividualneTreninge(t);
    }
    
    /**
     * Vraca listu svih individualnih treninga odredjenog nalgoa
     * 
     * @param n nalog
     * @return lista svih individualnih treninga odredjenog nalgoa
     * @throws Exception 
     */
    public List<IndividualniTrening> vratiSveIndividualneTreninge(Nalog n) throws Exception{
        return vratiSveIndividualneTreninge(n);
    }
}
