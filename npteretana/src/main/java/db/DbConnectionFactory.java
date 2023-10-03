/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;

import controller.Controller;
import domain.Clanarina;
import domain.Grad;
import domain.IndividualniTrening;
import domain.Nalog;
import domain.Ocena;
import domain.Oprema;
import domain.Teretana;
import domain.Trener;
import domain.VrstaOpreme;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 *
 * @author Luka
 */
public class DbConnectionFactory {
    Connection connection;
    private static DbConnectionFactory instance;
    
    public static DbConnectionFactory getInstance(){
        if(instance == null)
            instance = new DbConnectionFactory();
        return instance;
    }
    
    public Connection getConnection() throws Exception{
        if(connection == null || connection.isClosed()){
            Properties properties = new Properties();
            properties.load(new FileInputStream("config/dbconfig.properties"));
            String url = properties.getProperty("url");
            String username = properties.getProperty("username");
            String password = properties.getProperty("password");
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connection is successful");
            connection.setAutoCommit(false);
        }
        return connection;
    }
    
    
    
    
    public void dodajNalog(Nalog n) throws Exception {
        String query = "INSERT INTO nalog(ime, prezime, sifra_naloga, korisnicko_ime) VALUES (?,?,?,?)";
        System.out.println(query);
        Connection conn = DbConnectionFactory.getInstance().getConnection();
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, n.getIme());
        ps.setString(2, n.getPrezime());        
        ps.setString(3, n.getSifra());
        ps.setString(4, n.getKorisnickoIme());
        ps.executeUpdate();
        ps.close();
    }
    
    public void urediNalog(Nalog n) throws Exception {
        try {
            String sql = "UPDATE nalog SET "
                    + "ime='" + n.getIme()+ "', "
                    + "prezime='" + n.getPrezime()+ "', "
                    + "korisnicko_ime='" + n.getKorisnickoIme()+ "',"
                    + "sifra_naloga='" + n.getSifra()+ "' "
                    + "WHERE id=" + n.getId();
            System.out.println(sql);
            Connection connection = DbConnectionFactory.getInstance().getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            statement.close();
            connection.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Update product DB error: \n" + ex.getMessage());
        }
    }
    
    public List<Nalog> vratiSveNaloge() throws Exception {
        try {
            String query = "SELECT * FROM nalog";
            
            List<Nalog> list = new ArrayList<>();
            Connection conn = DbConnectionFactory.getInstance().getConnection();
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery(query);
            while(rs.next()){
                Nalog n = new Nalog();
                n.setId(rs.getLong("id"));
                n.setIme(rs.getString("ime"));
                n.setPrezime(rs.getString("prezime"));
                n.setKorisnickoIme(rs.getString("korisnicko_ime"));
                n.setSifra(rs.getString("sifra_naloga"));
                
                list.add(n);
            }            
            rs.close();
            stat.close();            
            conn.close();
            
            System.out.println("ResultSet zatvoren: " + rs.isClosed());
            System.out.println("Statement zatvoren: " + stat.isClosed());
            System.out.println("Konekcija zatvorena: " + conn.isClosed());
            return list;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }       
    }
    
    public Nalog pronadjiNalog(Nalog nalog) throws Exception {
        
        try {
            String query = "SELECT * FROM nalog WHERE id=" + nalog.getId();
            
            List<Nalog> list = new ArrayList<>();
            Connection conn = DbConnectionFactory.getInstance().getConnection();
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery(query);
            while(rs.next()){
                Nalog n = new Nalog();
                n.setId(rs.getLong("id"));
                n.setIme(rs.getString("ime"));
                n.setPrezime(rs.getString("prezime"));
                n.setKorisnickoIme(rs.getString("korisnicko_ime"));
                n.setSifra(rs.getString("sifra_naloga"));
                
                list.add(n);
            }            
            rs.close();
            stat.close();
            conn.close();
            return list.get(0);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
    
    
    
    public List<Grad> vratiSveGradove() throws Exception {
        String query = "SELECT * FROM grad";
        try {
            List<Grad> list = new ArrayList<>();
            Connection conn = DbConnectionFactory.getInstance().getConnection();
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery(query);
            while(rs.next()){
                Grad g = new Grad();
                g.setId(rs.getLong("id"));
                g.setNaziv(rs.getString("naziv_grada"));
                
                list.add(g);
            }            
            rs.close();
            stat.close();
            
            return list;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        } 
    }
    
    
    
    public void dodajTeretanu(Teretana t) throws Exception {
        String query = "INSERT INTO teretana(naziv, adresa, grad_id, prosecna_ocena) VALUES (?,?,?,?)";
        Connection conn = DbConnectionFactory.getInstance().getConnection();
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, t.getNaziv());
        ps.setString(2, t.getAdresa());
        ps.setLong(3, t.getGrad().getId());
        ps.setBigDecimal(4, t.getProsecnaOcena());
        ps.executeUpdate();
        ps.close();
    }
    
    public void urediTeretanu(Teretana t) throws Exception {
        try {
            String sql = "UPDATE teretana SET "
                    + "naziv='" + t.getNaziv()+ "', "
                    + "adresa='" + t.getAdresa()+ "', "
                    + "grad_id='" + t.getGrad().getId()+ "', "
                    + "WHERE id=" + t.getId();
            System.out.println(sql);
            Connection connection = DbConnectionFactory.getInstance().getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            statement.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Update product DB error: \n" + ex.getMessage());
        }
    }
    
    public List<Teretana> vratiSveTeretane() throws Exception {
        try {
            String query = "SELECT * FROM teretana t INNER JOIN grad g ON g.id = t.grad_id";
            
            List<Teretana> list = new ArrayList<>();
            Connection conn = DbConnectionFactory.getInstance().getConnection();
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery(query);
            while(rs.next()){
                Teretana t = new Teretana();
                t.setId(rs.getLong("t.id"));
                t.setNaziv(rs.getString("t.naziv"));
                t.setAdresa(rs.getString("t.adresa"));
                t.setProsecnaOcena(rs.getBigDecimal("t.prosecna_ocena"));
                
                Grad g = new Grad();
                g.setId(rs.getLong("g.id"));
                g.setNaziv(rs.getString("g.naziv_grada"));
                
                t.setGrad(g);
                
                list.add(t);
            }            
            rs.close();
            stat.close();
            
            popuniTeretaneTeretana(list);
            
            return list;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        } 
    }
    
    public List<Teretana> vratiSveTeretane(Grad g) throws Exception {
        try {
            String query = "SELECT * FROM teretana WHERE grad_id =" + g.getId();
            
            List<Teretana> list = new ArrayList<>();
            Connection conn = DbConnectionFactory.getInstance().getConnection();
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery(query);
            while(rs.next()){
                Teretana t = new Teretana();
                t.setId(rs.getLong("id"));
                t.setNaziv(rs.getString("naziv"));
                t.setAdresa(rs.getString("adresa"));
                t.setProsecnaOcena(rs.getBigDecimal("prosecna_ocena"));
                                                
                t.setGrad(g);
                
                list.add(t);
            }            
            rs.close();
            stat.close();
            
            popuniTeretaneTeretana(list);
            
            return list;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }                 
    }
    
    public Teretana vratiSveTeretane(Teretana teretana) throws Exception {        
        try {
            String query = "SELECT * FROM teretana t INNER JOIN grad g ON g.id=t.grad_id WHERE t.id=" + teretana.getId();
            
            List<Teretana> list = new ArrayList<>();
            Connection conn = DbConnectionFactory.getInstance().getConnection();
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery(query);
            while(rs.next()){
                Teretana t = new Teretana();
                t.setId(rs.getLong("t.id"));
                t.setNaziv(rs.getString("t.naziv"));
                t.setAdresa(rs.getString("t.adresa"));
                t.setProsecnaOcena(rs.getBigDecimal("t.prosecna_ocena"));
                
                Grad g = new Grad();
                g.setId(rs.getLong("g.id"));
                g.setNaziv(rs.getString("g.naziv_grada"));
                
                t.setGrad(g);
                
                list.add(t);
            }
            
            rs.close();
            stat.close();
            
            popuniTeretaneTeretana(list);
            
            return list.get(0);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
    
    
    
    public void dodajVrstuOpreme(VrstaOpreme vo) throws Exception {
        String query = "INSERT INTO vrsta_opreme(naziv) VALUES (?)";
        Connection conn = DbConnectionFactory.getInstance().getConnection();
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, vo.getVrsta());
        ps.executeUpdate();
        ps.close();
    }
    
    public List<VrstaOpreme> vratiSveVrsteOpreme() throws Exception {
        String query = "SELECT * FROM vrsta_opreme";
        try {
            List<VrstaOpreme> list = new ArrayList<>();
            Connection conn = DbConnectionFactory.getInstance().getConnection();
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery(query);
            while(rs.next()){
                VrstaOpreme vo = new VrstaOpreme();
                vo.setId(rs.getLong("id"));
                vo.setVrsta(rs.getString("naziv"));
                
                list.add(vo);
            }            
            rs.close();
            stat.close();
            
            return list;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        } 
    }
    
    
    
    public void dodajOpremu(Oprema o) throws Exception {
        String query = "INSERT INTO oprema(stanje, vrsta_opreme_id, teretana_id) VALUES (?,?,?)";
        Connection conn = DbConnectionFactory.getInstance().getConnection();
        PreparedStatement ps = conn.prepareStatement(query);
        //ps.setInt(1, o.getKolicina());
        ps.setString(1, o.getStanjeOpreme());
        ps.setLong(2, o.getVrsta().getId());
        ps.setLong(3, o.getTeretana().getId());
        ps.executeUpdate();
        ps.close();
    }
    
    public void urediOpremu(Oprema o) throws Exception {
        try {
            String query = "UPDATE oprema SET "
                //+ "kolicina=" + o.getKolicina() + ", "
                + "stanje='"+ o.getStanjeOpreme() + "' "
                + "WHERE id=" + o.getId();
            System.out.println(query);
            Connection conn = DbConnectionFactory.getInstance().getConnection();
            Statement stat = conn.createStatement();
            stat.executeQuery(query);
            stat.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Update oprema error: \n" + ex.getMessage());
        }
    }
    
    public void izbrisiOpremu(Oprema o) throws Exception {
        try {
            String query = "DELETE FROM oprema WHERE id=" + o.getId();
            Connection conn = DbConnectionFactory.getInstance().getConnection();
            Statement stat = conn.createStatement();
            stat.executeUpdate(query);
            stat.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Delete oprema error: \n" + ex.getMessage());
        }
    }
    
    public List<Oprema> vratiSveOpreme() throws Exception {
        try {
            String query = "SELECT * FROM oprema o "
                + "INNER JOIN vrsta_opreme vo ON vo.id=o.vrsta_opreme_id "
                    + "INNER JOIN teretana t ON t.id=o.teretana_id "
                    + "INNER JOIN grad g ON g.id=t.grad_id";
            System.out.println(query);
            List<Oprema> list = new ArrayList<>();
            Connection conn = DbConnectionFactory.getInstance().getConnection();
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery(query);
            while(rs.next()){
                Oprema o = new Oprema();
                o.setId(rs.getLong("o.id"));
                //o.setKolicina(rs.getInt("o.kolicina"));
                o.setStanjeOpreme(rs.getString("o.stanje"));

                VrstaOpreme vo = new VrstaOpreme();
                vo.setId(rs.getLong("vo.id"));
                vo.setVrsta(rs.getString("vo.naziv"));
                o.setVrsta(vo);
                
                Teretana t = new Teretana();
                t.setId(rs.getLong("t.id"));
                t.setNaziv(rs.getString("t.naziv"));
                t.setAdresa(rs.getString("t.adresa"));
                t.setProsecnaOcena(rs.getBigDecimal("t.prosecna_ocena"));
                
                Grad g = new Grad();
                g.setId(rs.getLong("g.id"));
                g.setNaziv(rs.getString("g.naziv_grada"));                
                t.setGrad(g);
                
                o.setTeretana(t);
                
                list.add(o);
            }
            return list;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public List<Oprema> vratiSveOpreme(Teretana t){
        try {
            String query = "SELECT * FROM oprema o INNER JOIN vrsta_opreme vo ON o.vrsta_opreme_id=vo.id WHERE teretana_id =" + t.getId();
            
            List<Oprema> list = new ArrayList<>();
            Connection conn = DbConnectionFactory.getInstance().getConnection();
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery(query);
            while(rs.next()){
                Oprema o = new Oprema();
                o.setId(rs.getLong("o.id"));
                //o.setKolicina(rs.getInt("o.kolicina"));
                o.setStanjeOpreme(rs.getString("o.stanje"));                                                
                o.setTeretana(t);
                
                VrstaOpreme vo = new VrstaOpreme();
                vo.setId(rs.getLong("vo.id"));
                vo.setVrsta(rs.getString("vo.naziv"));
                o.setVrsta(vo);
                
                list.add(o);
            }            
            rs.close();
            stat.close();
            
            return list;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        } 
    }
    
    public List<Oprema> vratiSveOpreme(VrstaOpreme vo){
        try {
            String query = "SELECT * FROM oprema o INNER JOIN teretana t ON o.teretana_id=t.id "
                    + "INNER JOIN grad g ON g.id=t.grad_id WHERE vrsta_opreme_id =" + vo.getId();
            
            List<Oprema> list = new ArrayList<>();
            Connection conn = DbConnectionFactory.getInstance().getConnection();
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery(query);
            while(rs.next()){
                Oprema o = new Oprema();
                o.setId(rs.getLong("o.id"));
                //o.setKolicina(rs.getInt("o.kolicina"));
                o.setStanjeOpreme(rs.getString("o.stanje"));                                                
                o.setVrsta(vo);
                
                Teretana t = new Teretana();
                t.setId(rs.getLong("t.id"));
                t.setNaziv(rs.getString("t.naziv"));
                t.setAdresa(rs.getString("t.adresa"));
                t.setProsecnaOcena(rs.getBigDecimal("t.prosecna_ocena"));
                
                Grad g = new Grad();
                g.setId(rs.getLong("g.id"));
                g.setNaziv(rs.getString("g.naziv_grada"));
                
                t.setGrad(g);
                                
                list.add(o);
            }            
            rs.close();
            stat.close();
            
            return list;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        } 
    }
    
    
    
    public void dodajClanarinu(Clanarina c) throws Exception {
        String query = "INSERT INTO clanarina(cena, nalog_id, teretana_id, datumOd, datumDo) VALUES (?,?,?,?,?)";
        System.out.println(query);
        Connection conn = DbConnectionFactory.getInstance().getConnection();
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setBigDecimal(1, c.getCena());
        ps.setLong(2, c.getNalog().getId());
        ps.setLong(3, c.getTeretana().getId());
        ps.setDate(4, Date.valueOf(c.getDatumOd()));
        ps.setDate(5, Date.valueOf(c.getDatumDo()));
        ps.executeUpdate();
        ps.close();
    }
    
    public void urediClanarinu(Clanarina c) throws Exception {
        try {
            String sql = "UPDATE clanarina SET "
                    + "cena=" + c.getCena()+ ", "
                    + "datumOd ='" + Date.valueOf(c.getDatumOd()) + "', "
                    + "datumDo ='" + Date.valueOf(c.getDatumDo()) + "' "
                    + "WHERE id=" + c.getId();
            System.out.println(sql);
            Connection connection = DbConnectionFactory.getInstance().getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            statement.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Update clanarina DB error: \n" + ex.getMessage());
        }
    }
    
    public List<Clanarina> vratiSveClanarine() throws Exception {
        try {
            String query = "SELECT * FROM clanarina c INNER JOIN nalog n ON n.id = c.nalog_id "
                    + "INNER JOIN teretana t ON t.id = c.teretana_id INNER JOIN grad g ON g.id = t.grad_id";
            
            List<Clanarina> list = new ArrayList<>();
            Connection conn = DbConnectionFactory.getInstance().getConnection();
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery(query);
            while(rs.next()){
                Clanarina c = new Clanarina();
                c.setId(rs.getLong("c.id"));
                c.setCena(rs.getBigDecimal("c.cena"));
                c.setDatumOd(rs.getDate("c.datumOd").toLocalDate());
                c.setDatumDo(rs.getDate("c.datumDo").toLocalDate());
                
                Nalog n = new Nalog();
                n.setId(rs.getLong("n.id"));
                n.setIme(rs.getString("n.ime"));
                n.setPrezime(rs.getString("n.prezime"));
                n.setKorisnickoIme(rs.getString("n.korisnicko_ime"));
                n.setSifra(rs.getString("n.sifra_naloga"));
                
                c.setNalog(n);
                
                Teretana t = new Teretana();
                t.setId(rs.getLong("t.id"));
                t.setNaziv(rs.getString("t.naziv"));
                t.setAdresa(rs.getString("t.adresa"));
                
                Grad g = new Grad();
                g.setId(rs.getLong("g.id"));
                g.setNaziv(rs.getString("g.naziv_grada"));                
                t.setGrad(g);                
                
                c.setTeretana(t);
                
                list.add(c);
            }            
            rs.close();
            stat.close();
            
            popuniTeretaneClanarina(list);
            
            return list;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }    
    } 
    
    public List<Clanarina> vratiSveClanarine(Nalog n) throws Exception {
        try {
            String query = "SELECT * FROM clanarina c INNER JOIN nalog n ON n.id = c.nalog_id "
                    + "INNER JOIN teretana t ON t.id = c.teretana_id INNER JOIN grad g ON g.id = t.grad_id WHERE n.id = " + n.getId();
            
            List<Clanarina> list = new ArrayList<>();
            Connection conn = DbConnectionFactory.getInstance().getConnection();
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery(query);
            while(rs.next()){
                Clanarina c = new Clanarina();
                c.setId(rs.getLong("c.id"));
                c.setCena(rs.getBigDecimal("c.cena"));
                c.setDatumOd(rs.getDate("c.datumOd").toLocalDate());
                c.setDatumDo(rs.getDate("c.datumDo").toLocalDate());
                
                c.setNalog(n);
                
                Teretana t = new Teretana();
                t.setId(rs.getLong("t.id"));
                t.setNaziv(rs.getString("t.naziv"));
                t.setAdresa(rs.getString("t.adresa"));
                
                Grad g = new Grad();
                g.setId(rs.getLong("g.id"));
                g.setNaziv(rs.getString("g.naziv_grada"));                
                t.setGrad(g);
                
//                t.setOpreme(Controller.getInstance().nadjiOpreme(t));
//                t.setTreneri(Controller.getInstance().nadjiTrenere(t));
                
                c.setTeretana(t);
                
                list.add(c);
            }            
            rs.close();
            stat.close();
            
            popuniTeretaneClanarina(list);
            
            return list;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }   
    }
    
    public Clanarina vratiSveClanarine(Clanarina clanarina) throws Exception {
        try {
            String query = "SELECT * FROM clanarina c INNER JOIN nalog n ON n.id = c.nalog_id "
                    + "INNER JOIN teretana t ON t.id = c.teretana_id INNER JOIN grad g ON g.id = t.grad_id WHERE c.id=" + clanarina.getId();
            
            List<Clanarina> list = new ArrayList<>();
            Connection conn = DbConnectionFactory.getInstance().getConnection();
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery(query);
            while(rs.next()){
                Clanarina c = new Clanarina();
                c.setId(rs.getLong("c.id"));
                c.setCena(rs.getBigDecimal("c.cena"));
                c.setDatumOd(rs.getDate("c.datumOd").toLocalDate());
                c.setDatumDo(rs.getDate("c.datumDo").toLocalDate());
                
                Nalog n = new Nalog();
                n.setId(rs.getLong("n.id"));
                n.setIme(rs.getString("n.ime"));
                n.setPrezime(rs.getString("n.prezime"));
                n.setKorisnickoIme(rs.getString("n.korisnicko_ime"));
                n.setSifra(rs.getString("n.sifra_naloga"));
                
                c.setNalog(n);
                
                Teretana t = new Teretana();
                t.setId(rs.getLong("t.id"));
                t.setNaziv(rs.getString("t.naziv"));
                t.setAdresa(rs.getString("t.adresa"));
                
                Grad g = new Grad();
                g.setId(rs.getLong("g.id"));
                g.setNaziv(rs.getString("g.naziv_grada"));                
                t.setGrad(g);
                
                
                c.setTeretana(t);
                
                list.add(c);
            }            
            rs.close();
            stat.close();
            
            popuniTeretaneClanarina(list);
            
            return list.get(0);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }    
    }
    
    
    
    
    public void dodajOcenu(Ocena o) throws Exception {
        String query = "INSERT INTO ocena(nalog_id, teretana_id, vrednost) VALUES (?,?,?)";
        Connection conn = DbConnectionFactory.getInstance().getConnection();
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setLong(1, o.getNalog().getId());
        ps.setLong(2, o.getTeretana().getId());
        ps.setInt(3, o.getVrednost());
        ps.executeUpdate();
        ps.close();
        conn.close();
    }
    
    public void urediOcenu(Ocena o) throws Exception {
        try {
            String sql = "UPDATE ocena SET "
                    + "vrednost=" + o.getVrednost()+ " "
                    + "WHERE nalog_id=" + o.getNalog().getId()
                    + " AND teretana_id=" + o.getTeretana().getId();
            System.out.println(sql);
            Connection connection = DbConnectionFactory.getInstance().getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            statement.close();
            connection.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Update product DB error: \n" + ex.getMessage());
        }
    }
    
    public List<Ocena> vratiSveOcene() throws Exception {
        try {
            String query = "SELECT * FROM ocena o INNER JOIN nalog n ON n.id = o.nalog_id "
                    + "INNER JOIN teretana t ON t.id = o.teretana_id INNER JOIN grad g ON g.id = t.grad_id";
            
            List<Ocena> list = new ArrayList<>();
            Connection conn = DbConnectionFactory.getInstance().getConnection();
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery(query);
            while(rs.next()){
                Ocena o = new Ocena();
                o.setVrednost(rs.getInt("o.vrednost"));
                
                Nalog n = new Nalog();
                n.setId(rs.getLong("n.id"));
                n.setIme(rs.getString("n.ime"));
                n.setPrezime(rs.getString("n.prezime"));
                n.setKorisnickoIme(rs.getString("n.korisnicko_ime"));
                n.setSifra(rs.getString("n.sifra_naloga"));
                
                o.setNalog(n);
                
                Teretana t = new Teretana();
                t.setId(rs.getLong("t.id"));
                t.setNaziv(rs.getString("t.naziv"));
                t.setAdresa(rs.getString("t.adresa"));
                
                Grad g = new Grad();
                g.setId(rs.getLong("g.id"));
                g.setNaziv(rs.getString("g.naziv_grada"));                
                t.setGrad(g);
                
                t.setOpreme(Controller.getInstance().nadjiOpreme(t));
                t.setTreneri(Controller.getInstance().nadjiTrenere(t));
                
                o.setTeretana(t);
                
                list.add(o);
            }            
            rs.close();
            stat.close();
            conn.close();
            
            return list;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }   
    }
    
    public List<Ocena> vratiSveOcene(Nalog n) throws Exception {
        try {
            String query = "SELECT * FROM ocena o INNER JOIN nalog n ON n.id = o.nalog_id "
                    + "INNER JOIN teretana t ON t.id = o.teretana_id INNER JOIN grad g ON g.id = t.grad_id WHERE n.id=" + n.getId();
            
            List<Ocena> list = new ArrayList<>();
            Connection conn = DbConnectionFactory.getInstance().getConnection();
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery(query);
            while(rs.next()){
                Ocena o = new Ocena();
                o.setVrednost(rs.getInt("o.vrednost"));
                
                o.setNalog(n);
                
                Teretana t = new Teretana();
                t.setId(rs.getLong("t.id"));
                t.setNaziv(rs.getString("t.naziv"));
                t.setAdresa(rs.getString("t.adresa"));
                
                Grad g = new Grad();
                g.setId(rs.getLong("g.id"));
                g.setNaziv(rs.getString("g.naziv_grada"));                
                t.setGrad(g);
                
//                t.setOpreme(Controller.getInstance().nadjiOpreme(t));
//                t.setTreneri(Controller.getInstance().nadjiTrenere(t));
                
                o.setTeretana(t);
                
                list.add(o);
            }            
            rs.close();
            stat.close();
            conn.close();
            
            popuniTeretaneOcena(list);
            
            return list;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        } 
    }
    
    
    
    
    public void dodajTrenera(Trener t) throws Exception {
        String query = "INSERT INTO trener(ime, prezime, teretana_id) VALUES (?,?,?)";
        Connection conn = DbConnectionFactory.getInstance().getConnection();
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, t.getIme());
        ps.setString(2, t.getPrezime());
        ps.setLong(3, t.getTeretana().getId());
        ps.executeUpdate();
        ps.close();
    }
    
    public void urediTrenera(Trener t) throws Exception {
        try {
            String sql = "UPDATE trener SET "
                    + "ime='" + t.getIme()+ "', "
                    + "prezime='" + t.getPrezime()+ "', "
                    + "teretana_id=" + t.getTeretana().getId()+ " "
                    + "WHERE id=" + t.getId();
            System.out.println(sql);
            Connection connection = DbConnectionFactory.getInstance().getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            statement.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Update trener DB error: \n" + ex.getMessage());
        }
    }
    
    public List<Trener> vratiSveTrenere() throws Exception {
        try {
            String query = "SELECT * FROM trener t INNER JOIN teretana tr ON tr.id = t.teretana_id "
                    + "INNER JOIN grad g ON g.id=tr.grad_id ";
            
            List<Trener> list = new ArrayList<>();
            Connection conn = DbConnectionFactory.getInstance().getConnection();
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery(query);
            while(rs.next()){
                Trener t = new Trener();
                t.setId(rs.getLong("t.id"));
                t.setIme(rs.getString("t.ime"));
                t.setPrezime(rs.getString("t.prezime"));
                
                Teretana tr = new Teretana();
                tr.setId(rs.getLong("tr.id"));
                tr.setNaziv(rs.getString("tr.naziv"));
                tr.setAdresa(rs.getString("tr.adresa"));
                tr.setProsecnaOcena(rs.getBigDecimal("tr.prosecna_ocena"));
                
                Grad g = new Grad();
                g.setId(rs.getLong("g.id"));
                g.setNaziv(rs.getString("g.naziv_grada"));                
                tr.setGrad(g);
                
                t.setTeretana(tr);
                
                list.add(t);
            }            
            rs.close();
            stat.close();
            
            return list;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        } 
    }
    
    public List<Trener> vratiSveTrenere(Teretana tr) throws Exception {
        try {
            String query = "SELECT * FROM trener WHERE teretana_id =" + tr.getId();
            
            List<Trener> list = new ArrayList<>();
            Connection conn = DbConnectionFactory.getInstance().getConnection();
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery(query);
            while(rs.next()){
                Trener t = new Trener();
                t.setId(rs.getLong("id"));
                t.setIme(rs.getString("ime"));
                t.setPrezime(rs.getString("prezime"));                                               
                t.setTeretana(tr);
                
                list.add(t);
            }            
            rs.close();
            stat.close();
            
            return list;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        } 
    }
    
    public void dodajIndividualniTrening(IndividualniTrening it) throws Exception {
        String query = "INSERT INTO individualni_trening(nalog_id, trener_id, termin) VALUES (?,?,?)";
        Connection conn = DbConnectionFactory.getInstance().getConnection();
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setLong(1, it.getNalog().getId());
        ps.setLong(2, it.getTrener().getId());
        ps.setDate(3, Date.valueOf(it.getTermin()));
        ps.executeUpdate();
        ps.close();
    }
    
    public void urediIndividualniTrening(IndividualniTrening it) throws Exception {
        try {
            String sql = "UPDATE individualni_trening SET "
                    + "termin=" + it.getTermin()+ " "
                    + "WHERE nalog_id=" + it.getNalog().getId()
                    + " AND trener_id=" + it.getTrener().getId();
            System.out.println(sql);
            Connection connection = DbConnectionFactory.getInstance().getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            statement.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Update individualni_trening DB error: \n" + ex.getMessage());
        }
    }
    
    public List<IndividualniTrening> vratiSveIndividualneTreninge() throws Exception {
        try {
            String query = "SELECT * FROM individualni_trening it INNER JOIN nalog n ON n.id = it.nalog_id "
                    + "INNER JOIN trener tr ON tr.id = it.teretana_id "
                    + "INNER JOIN teretana t ON t.id = tr.teretana_id "
                    + "INNER JOIN grad g ON g.id = t.grad_id ";
            
            List<IndividualniTrening> list = new ArrayList<>();
            Connection conn = DbConnectionFactory.getInstance().getConnection();
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery(query);
            while(rs.next()){
                IndividualniTrening it = new IndividualniTrening();
                it.setTermin(rs.getDate("it.termin").toLocalDate());
                
                Nalog n = new Nalog();
                n.setId(rs.getLong("n.id"));
                n.setIme(rs.getString("n.ime"));
                n.setPrezime(rs.getString("n.prezime"));
                n.setKorisnickoIme(rs.getString("n.korisnicko_ime"));
                n.setSifra(rs.getString("n.sifra_naloga"));                
                it.setNalog(n);
                
                Trener tr = new Trener();
                tr.setId(rs.getLong("tr.id"));
                tr.setIme(rs.getString("tr.ime"));
                tr.setPrezime(rs.getString("tr.prezime"));
                
                Teretana t = new Teretana();
                t.setId(rs.getLong("t.id"));
                t.setNaziv(rs.getString("t.naziv"));
                t.setAdresa(rs.getString("t.adresa"));
                t.setProsecnaOcena(rs.getBigDecimal("t.prosecna_ocena"));
                
                Grad g = new Grad();
                g.setId(rs.getLong("g.id"));
                g.setNaziv(rs.getString("g.naziv_grada"));                
                t.setGrad(g);
                                
                t.setOpreme(Controller.getInstance().nadjiOpreme(t));
                t.setTreneri(Controller.getInstance().nadjiTrenere(t));
                
                tr.setTeretana(t);
                
                it.setTrener(tr);
                
                list.add(it);
            }            
            rs.close();
            stat.close();
            
            return list;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        } 
    }
    
    public List<IndividualniTrening> vratiSveIndividualneTreninge(Trener t) {
        try {
            String query = "SELECT * FROM individualni_trening it "
                    + "INNER JOIN nalog n ON n.id = it.nalog_id WHERE it.trener_id=" + t.getId();
            
            List<IndividualniTrening> list = new ArrayList<>();
            Connection conn = DbConnectionFactory.getInstance().getConnection();
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery(query);
            while(rs.next()){
                IndividualniTrening it = new IndividualniTrening();
                it.setTermin(rs.getDate("it.termin").toLocalDate());              
                it.setTrener(t);                
                             
                Nalog n = new Nalog();
                n.setId(rs.getLong("n.id"));
                n.setIme(rs.getString("n.ime"));
                n.setPrezime(rs.getString("n.prezime"));
                n.setKorisnickoIme(rs.getString("n.korisnicko_ime"));
                n.setSifra(rs.getString("n.sifra_naloga"));
                
                it.setNalog(n);
                
                list.add(it);
            }            
            rs.close();
            stat.close();
            
            return list;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        } 
    }
    
    public List<IndividualniTrening> vratiSveIndividualneTreninge(Nalog n) {
        try {
            String query = "SELECT * FROM individualni_trening it "
                    + "INNER JOIN trener tr ON tr.id = it.trener_id "
                    + "INNER JOIN teretana t ON t.id = o.teretana_id INNER JOIN grad g ON g.id = t.grad_id WHERE it.nalog_id=" + n.getId();
            
            List<IndividualniTrening> list = new ArrayList<>();
            Connection conn = DbConnectionFactory.getInstance().getConnection();
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery(query);
            while(rs.next()){
                IndividualniTrening it = new IndividualniTrening();
                it.setTermin(rs.getDate("it.termin").toLocalDate());                
                it.setNalog(n);
                
                Trener tr = new Trener();
                tr.setId(rs.getLong("tr.id"));
                tr.setIme(rs.getString("tr.ime"));
                tr.setPrezime(rs.getString("tr.prezime"));
                
                Teretana t = new Teretana();
                t.setId(rs.getLong("t.id"));
                t.setNaziv(rs.getString("t.naziv"));
                t.setAdresa(rs.getString("t.adresa"));
                
                Grad g = new Grad();
                g.setId(rs.getLong("g.id"));
                g.setNaziv(rs.getString("g.naziv_grada"));                
                t.setGrad(g);
                
                t.setOpreme(Controller.getInstance().nadjiOpreme(t));
                t.setTreneri(Controller.getInstance().nadjiTrenere(t));                
                tr.setTeretana(t);
                
                it.setTrener(tr);
                
                list.add(it);
            }            
            rs.close();
            stat.close();
            
            return list;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        } 
    }

    private void popuniTeretaneOcena(List<Ocena> list) throws Exception {       
        for (int i = 0; i < list.size(); i++) {
            list.get(i).getTeretana().setOpreme(Controller.getInstance().nadjiOpreme(list.get(i).getTeretana()));
            list.get(i).getTeretana().setTreneri(Controller.getInstance().nadjiTrenere(list.get(i).getTeretana()));
        }
    }
    
    private void popuniTeretaneClanarina(List<Clanarina> list) throws Exception{
        for (int i = 0; i < list.size(); i++) {
            list.get(i).getTeretana().setOpreme(Controller.getInstance().nadjiOpreme(list.get(i).getTeretana()));
            list.get(i).getTeretana().setTreneri(Controller.getInstance().nadjiTrenere(list.get(i).getTeretana()));
        }
    }
    
    private void popuniTeretaneTeretana(List<Teretana> list) throws Exception{
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setOpreme(Controller.getInstance().nadjiOpreme(list.get(i)));
            list.get(i).setTreneri(Controller.getInstance().nadjiTrenere(list.get(i)));
        
        }
    }
}