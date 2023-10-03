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
 *
 * @author Luka
 */
public class Controller {
    private final DbConnectionFactory dbCon;

    private static Controller controller;

    private Controller() {
        this.dbCon = new DbConnectionFactory();
    }

    public static Controller getInstance() {
        if (controller == null) {
            controller = new Controller();
        }
        return controller;
    }
    
    
    
    public Nalog login(String korisnickoIme, String sifra) throws Exception {
        List<Nalog> nalozi = dbCon.vratiSveNaloge();
        for (Nalog nalog : nalozi) {
            if (nalog.getKorisnickoIme().equals(korisnickoIme) && nalog.getSifra().equals(sifra)) {
                return nalog;
            }
        }
        throw new Exception("Unknown user!");
    }   
    
    
    public List<Nalog> vratiSveNaloge() throws Exception{
        return dbCon.vratiSveNaloge();
    }      
        
    public Nalog kreirajNalog() throws Exception{
        Nalog n = new Nalog();
        n.setIme("Unesite ime korisnika");
        n.setPrezime("Unesite prezime korisnika");
        n.setKorisnickoIme("Unesite korisničko ime");
        n.setSifra("Unesite šifru");
        
        return n;
    }
    
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
     
      
    
    public List<Grad> vratiSveGradove() throws Exception{
        return dbCon.vratiSveGradove();
    }
    
    
    
    public List<Teretana> vratiSveTeretane() throws Exception{
        return dbCon.vratiSveTeretane();
    }
    
    public void sacuvajTeretanu(Teretana t) throws Exception {
        DbConnectionFactory.getInstance().getConnection();
        try {
            dbCon.urediTeretanu(t);
            DbConnectionFactory.getInstance().getConnection().commit();
        } catch (Exception e) {
            e.printStackTrace();
            DbConnectionFactory.getInstance().getConnection().rollback();
            throw e;
        } finally {
            DbConnectionFactory.getInstance().getConnection().close();
        }
    }
    
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
    
    
    
    public List<Clanarina> vratiSveClanarine() throws Exception, Exception{
        return dbCon.vratiSveClanarine();
    }
    
    public List<Clanarina> vratiSveClanarine(Nalog n) throws Exception, Exception{
        return dbCon.vratiSveClanarine(n);
    }
    
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
    
    
    
    public List<Ocena> vratiSveOcene(Nalog n) throws Exception{
        return dbCon.vratiSveOcene(n);
    }
    
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
        
    
    public void dodajVrstuOpreme(VrstaOpreme vrstaOpreme) throws Exception {
        DbConnectionFactory.getInstance().getConnection();
        try {
            dbCon.dodajVrstuOpreme(vrstaOpreme);
            DbConnectionFactory.getInstance().getConnection().commit();
        } catch (Exception e) {
            e.printStackTrace();
            DbConnectionFactory.getInstance().getConnection().rollback();
            throw e;
        } finally {
            DbConnectionFactory.getInstance().getConnection().close();
        }
    }
    
    public List<VrstaOpreme> vratiSveVrsteOpreme() throws Exception{
        return dbCon.vratiSveVrsteOpreme();
    }
    
    
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
       
    public List<Oprema> vratiSveOpreme() throws Exception{
        return dbCon.vratiSveOpreme();
    }
    
    
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
   
    public List<Trener> vratiSveTrenere(Teretana teretana) throws Exception{
        return dbCon.vratiSveTrenere(teretana);
    }
   
   
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
    
    public List<IndividualniTrening> vratiSveIndividualneTreninge(Trener t) throws Exception{
        return vratiSveIndividualneTreninge(t);
    }
    
    public List<IndividualniTrening> vratiSveIndividualneTreninge(Nalog n) throws Exception{
        return vratiSveIndividualneTreninge(n);
    }
}
