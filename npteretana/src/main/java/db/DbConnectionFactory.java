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
 * DbConnectionFactory uspostavlja konekciju sa bazom podataka
 * 
 * @author Luka
 */
public class DbConnectionFactory {

    /**
     * Instanca klase Connection
     */
    Connection connection;
    
    /**
     * Instanca DbConnectionFactory
     */
    private static DbConnectionFactory instance;
    
    /**
     * Vraca instancu DbConnectionFactory
     * 
     * @return instanca DbConnectionFactory
     */
    public static DbConnectionFactory getInstance(){
        if(instance == null)
            instance = new DbConnectionFactory();
        return instance;
    }
    
    /**
     * Uspostavlja konekciju sa bazom podataka
     * 
     * @return connection
     * @throws Exception 
     */
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
    
    public Connection getConn() {
    	return connection;
    }
    
    public void closeConnection() throws Exception {
    	try {
			connection.close();
		} catch (SQLException e) {
			throw new Exception("Nije moguce zatvoriti konekciju");
		}
    }
    
    
    
    /**
     * Unosi novi nalog u bazu podataka
     * 
     * @param n nalog
     * @throws Exception 
     */
    public void dodajNalog(Nalog n) throws Exception {
        String query = "INSERT INTO nalog(ime, prezime, sifra_naloga, korisnicko_ime) VALUES (?,?,?,?)";
        System.out.println(query);        
        Connection conn = DbConnectionFactory.getInstance().getConnection();
        PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);        
        
        //PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, n.getIme());
        ps.setString(2, n.getPrezime());        
        ps.setString(3, n.getSifra());
        ps.setString(4, n.getKorisnickoIme());
        ps.executeUpdate();
        
        ResultSet rs = ps.getGeneratedKeys();
        int generatedKey = 0;
        if (rs.next()) {
            generatedKey = rs.getInt(1);
        }
        n.setId(generatedKey);
        
        ps.close();
    }
    
    /**
     * Cuva izmene naloga u bazi podataka
     * 
     * @param n nalog
     * @throws Exception 
     */
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
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Update product DB error: \n" + ex.getMessage());
        }
    }
    
    /**
     * Vraca listu svih naloga iz baze podataka
     * 
     * @return lista svih naloga iz baze podataka
     * @throws Exception 
     */
    public List<Nalog> vratiSveNaloge() throws Exception {
        try {
            String query = "SELECT * FROM nalog";
            
            List<Nalog> list = new ArrayList<>();
            //Connection conn = DbConnectionFactory.getInstance().getConnection();
            //Statement stat = conn.createStatement();
            Connection connection = DbConnectionFactory.getInstance().getConnection();
            Statement stat = connection.createStatement();
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
            //conn.close();
            
            System.out.println("ResultSet zatvoren: " + rs.isClosed());
            System.out.println("Statement zatvoren: " + stat.isClosed());
            //System.out.println("Konekcija zatvorena: " + conn.isClosed());
            return list;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }       
    }
      
    
    /**
     * Vraca listu svih gradova iz baze podataka
     * 
     * @return lista svih gradova iz baze podataka
     * @throws Exception 
     */
    public List<Grad> vratiSveGradove() throws Exception {
        String query = "SELECT * FROM grad";
        try {
            List<Grad> list = new ArrayList<>();
            Connection conn = DbConnectionFactory.getInstance().getConnection();
            Statement stat = conn.createStatement();
            
            //Statement stat = connection.createStatement();
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
    
    
    /**
     * Unosi novu teretanu u bazu podataka
     * 
     * @param t teretana
     * @throws Exception 
     */
    public void dodajTeretanu(Teretana t) throws Exception {
        String query = "INSERT INTO teretana(naziv, adresa, grad_id, prosecna_ocena) VALUES (?,?,?,?)";
        Connection conn = DbConnectionFactory.getInstance().getConnection();
        PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        
        //PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, t.getNaziv());
        ps.setString(2, t.getAdresa());
        ps.setLong(3, t.getGrad().getId());
        ps.setBigDecimal(4, t.getProsecnaOcena());
        ps.executeUpdate();
        
        ResultSet rs = ps.getGeneratedKeys();
        int generatedKey = 0;
        if (rs.next()) {
            generatedKey = rs.getInt(1);
        }
        t.setId(generatedKey);
        
        ps.close();
    }
        
    /**
     * Vraca listu svih teretana iz baze podataka
     * 
     * @return lista svih teretana iz baze podataka
     * @throws Exception 
     */
    public List<Teretana> vratiSveTeretane() throws Exception {
        try {
            String query = "SELECT * FROM teretana t INNER JOIN grad g ON g.id = t.grad_id";
            
            List<Teretana> list = new ArrayList<>();
            Connection conn = DbConnectionFactory.getInstance().getConnection();
            Statement stat = conn.createStatement();
            
            //Statement stat = connection.createStatement();
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
    
    /**
     * Vraca listu svih teretana iz baze podataka koje su iz datog grada
     * @param g grad
     * @return lista svih teretana iz baze podataka koje su iz datog grada
     * @throws Exception 
     */
    public List<Teretana> vratiSveTeretane(Grad g) throws Exception {
        try {
            String query = "SELECT * FROM teretana WHERE grad_id =" + g.getId();
            
            List<Teretana> list = new ArrayList<>();
            Connection connection = DbConnectionFactory.getInstance().getConnection();
            Statement stat = connection.createStatement();
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
    
    /**
     * Pronalazi teretanu iz baze podataka po id-u
     * 
     * @param teretana
     * @return teretana
     * @throws Exception 
     */
    public Teretana vratiSveTeretane(Teretana teretana) throws Exception {        
        try {
            String query = "SELECT * FROM teretana t INNER JOIN grad g ON g.id=t.grad_id WHERE t.id=" + teretana.getId();
            
            List<Teretana> list = new ArrayList<>();
            Connection connection = DbConnectionFactory.getInstance().getConnection();
            Statement stat = connection.createStatement();
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
    
    /**
     * Vraca listu svih vrsta opreme
     * 
     * @return lista svih vrste opreme
     * @throws Exception 
     */
    public List<VrstaOpreme> vratiSveVrsteOpreme() throws Exception {
        String query = "SELECT * FROM vrsta_opreme";
        try {
            List<VrstaOpreme> list = new ArrayList<>();
            Connection connection = DbConnectionFactory.getInstance().getConnection();
            Statement stat = connection.createStatement();
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
    
    
    
    /**
     * Unosi novu opremu u bazu podataka
     * 
     * @param o oprema
     * @throws Exception 
     */
    public void dodajOpremu(Oprema o) throws Exception {
        String query = "INSERT INTO oprema(stanje, vrsta_opreme_id, teretana_id) VALUES (?,?,?)";
        Connection connection = DbConnectionFactory.getInstance().getConnection();
        PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        //ps.setInt(1, o.getKolicina());
        ps.setString(1, o.getStanjeOpreme());
        ps.setLong(2, o.getVrsta().getId());
        ps.setLong(3, o.getTeretana().getId());
        ps.executeUpdate();
        
        ResultSet rs = ps.getGeneratedKeys();
        int generatedKey = 0;
        if (rs.next()) {
            generatedKey = rs.getInt(1);
        }
        o.setId(generatedKey);
        
        ps.close();
    }
    
    /**
     * Cuva izmenjenu opremu u bazi podataka
     * 
     * @param o oprema
     * @throws Exception 
     */
/*    public void urediOpremu(Oprema o) throws Exception {
        try {
            String query = "UPDATE oprema SET "
                //+ "kolicina=" + o.getKolicina() + ", "
                + "stanje='"+ o.getStanjeOpreme() + "' "
                + "WHERE id=" + o.getId();
            System.out.println(query);
            Connection connection = DbConnectionFactory.getInstance().getConnection();
            Statement stat = connection.createStatement();
            stat.executeQuery(query);
            stat.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Update oprema error: \n" + ex.getMessage());
        }
    }/*
    
    /**
     * Vraca listu svih oprema
     * 
     * @return lista svih oprema
     * @throws Exception 
     */
    public List<Oprema> vratiSveOpreme() throws Exception {
        try {
            String query = "SELECT * FROM oprema o "
                + "INNER JOIN vrsta_opreme vo ON vo.id=o.vrsta_opreme_id "
                    + "INNER JOIN teretana t ON t.id=o.teretana_id "
                    + "INNER JOIN grad g ON g.id=t.grad_id";
            System.out.println(query);
            List<Oprema> list = new ArrayList<>();
            Connection connection = DbConnectionFactory.getInstance().getConnection();
            Statement stat = connection.createStatement();
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
    
    /**
     * Vraca listu svih oprema u jednoj teretani
     * 
     * @param t teretana
     * @return 
     */
    public List<Oprema> vratiSveOpreme(Teretana t){
        try {
            String query = "SELECT * FROM oprema o INNER JOIN vrsta_opreme vo ON o.vrsta_opreme_id=vo.id WHERE teretana_id =" + t.getId();
            
            List<Oprema> list = new ArrayList<>();
            Connection connection = DbConnectionFactory.getInstance().getConnection();
            Statement stat = connection.createStatement();
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
    
    /**
     * Vraca listu svih oprema odredjene vrste
     * 
     * @param vo vrsta opreme
     * @return 
     */
    public List<Oprema> vratiSveOpreme(VrstaOpreme vo){
        try {
            String query = "SELECT * FROM oprema o INNER JOIN teretana t ON o.teretana_id=t.id "
                    + "INNER JOIN grad g ON g.id=t.grad_id WHERE vrsta_opreme_id =" + vo.getId();
            
            List<Oprema> list = new ArrayList<>();
            Connection connection = DbConnectionFactory.getInstance().getConnection();
            Statement stat = connection.createStatement();
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
    
    
    
    /**
     * Unosi novu clanarinu u bazu podataka
     * 
     * @param c clanarina
     * @throws Exception 
     */
    public void dodajClanarinu(Clanarina c) throws Exception {
        String query = "INSERT INTO clanarina(cena, nalog_id, teretana_id, datumOd, datumDo) VALUES (?,?,?,?,?)";
        System.out.println(query);
        Connection connection = DbConnectionFactory.getInstance().getConnection();
        PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        ps.setBigDecimal(1, c.getCena());
        ps.setLong(2, c.getNalog().getId());
        ps.setLong(3, c.getTeretana().getId());
        ps.setDate(4, Date.valueOf(c.getDatumOd()));
        ps.setDate(5, Date.valueOf(c.getDatumDo()));
        ps.executeUpdate();
        
        ResultSet rs = ps.getGeneratedKeys();
        int generatedKey = 0;
        if (rs.next()) {
            generatedKey = rs.getInt(1);
        }
        c.setId(generatedKey);
        
        ps.close();
    }
    
    /**
     * Uredjuje i update-uje clanarinu
     * 
     * @param c clanarina
     * @throws Exception 
     */
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
    
    /**
     * Vraca listu svih clanarina iz baze podataka
     * 
     * @return lista svih clanarina iz baze podataka
     * @throws Exception 
     */
    public List<Clanarina> vratiSveClanarine() throws Exception {
        try {
            String query = "SELECT * FROM clanarina c INNER JOIN nalog n ON n.id = c.nalog_id "
                    + "INNER JOIN teretana t ON t.id = c.teretana_id INNER JOIN grad g ON g.id = t.grad_id";
            
            List<Clanarina> list = new ArrayList<>();
            Connection connection = DbConnectionFactory.getInstance().getConnection();
            Statement stat = connection.createStatement();
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
    
    /**
     * Vraca listu svih clanarina jednog naloga
     * 
     * @param n nalog
     * @return lista svih clanarina jednog naloga
     * @throws Exception 
     */
    public List<Clanarina> vratiSveClanarine(Nalog n) throws Exception {
        try {
            String query = "SELECT * FROM clanarina c INNER JOIN nalog n ON n.id = c.nalog_id "
                    + "INNER JOIN teretana t ON t.id = c.teretana_id INNER JOIN grad g ON g.id = t.grad_id WHERE n.id = " + n.getId();
            
            List<Clanarina> list = new ArrayList<>();
            Connection connection = DbConnectionFactory.getInstance().getConnection();
            Statement stat = connection.createStatement();
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
    
    /**
     * Nalazi clanarinu po id-u
     * 
     * @param clanarina
     * @return clanarina
     * @throws Exception 
     */
    public Clanarina vratiSveClanarine(Clanarina clanarina) throws Exception {
        try {
            String query = "SELECT * FROM clanarina c INNER JOIN nalog n ON n.id = c.nalog_id "
                    + "INNER JOIN teretana t ON t.id = c.teretana_id INNER JOIN grad g ON g.id = t.grad_id WHERE c.id=" + clanarina.getId();
            
            List<Clanarina> list = new ArrayList<>();
            Connection connection = DbConnectionFactory.getInstance().getConnection();
            Statement stat = connection.createStatement();
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
    
    
    
    /**
     * Unosi novu ocenu u bazu podataka
     * 
     * @param o ocena
     * @throws Exception 
     */
    public void dodajOcenu(Ocena o) throws Exception {
        String query = "INSERT INTO ocena(nalog_id, teretana_id, vrednost) VALUES (?,?,?)";
        Connection connection = DbConnectionFactory.getInstance().getConnection();
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setLong(1, o.getNalog().getId());
        ps.setLong(2, o.getTeretana().getId());
        ps.setInt(3, o.getVrednost());
        ps.executeUpdate();
        ps.close();
    }
    
    /**
     * Uredjuje i update-uje ocenu u bazi podataka
     * 
     * @param o ocena
     * @throws Exception 
     */
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
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Update product DB error: \n" + ex.getMessage());
        }
    }
    
    /**
     * Vraca listu svih ocena
     * 
     * @return lista svih ocena
     * @throws Exception 
     */
    public List<Ocena> vratiSveOcene() throws Exception {
        try {
            String query = "SELECT * FROM ocena o INNER JOIN nalog n ON n.id = o.nalog_id "
                    + "INNER JOIN teretana t ON t.id = o.teretana_id INNER JOIN grad g ON g.id = t.grad_id";
            
            List<Ocena> list = new ArrayList<>();
            Connection connection = DbConnectionFactory.getInstance().getConnection();
            Statement stat = connection.createStatement();
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
            //conn.close();
            
            return list;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }   
    }
    
    /**
     * Vraca listu svih ocena koje je uneo dati nalog
     * 
     * @param n nalog
     * @return lista svih ocena koje je uneo dati nalog
     * @throws Exception 
     */
    public List<Ocena> vratiSveOcene(Nalog n) throws Exception {
        try {
            String query = "SELECT * FROM ocena o INNER JOIN nalog n ON n.id = o.nalog_id "
                    + "INNER JOIN teretana t ON t.id = o.teretana_id INNER JOIN grad g ON g.id = t.grad_id WHERE n.id=" + n.getId();
            
            List<Ocena> list = new ArrayList<>();
            Connection connection = DbConnectionFactory.getInstance().getConnection();
            Statement stat = connection.createStatement();
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
                
                o.setTeretana(t);
                
                list.add(o);
            }            
            rs.close();
            stat.close();
            //conn.close();
            
            popuniTeretaneOcena(list);
            
            return list;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        } 
    }
    
    
    
    /**
     * Unosi novog trenera u bazu podataka
     * 
     * @param t trener
     * @throws Exception 
     */
    public void dodajTrenera(Trener t) throws Exception {
        String query = "INSERT INTO trener(ime, prezime, teretana_id) VALUES (?,?,?)";
        Connection connection = DbConnectionFactory.getInstance().getConnection();
        PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, t.getIme());
        ps.setString(2, t.getPrezime());
        ps.setLong(3, t.getTeretana().getId());
        ps.executeUpdate();
        
        ResultSet rs = ps.getGeneratedKeys();
        int generatedKey = 0;
        if (rs.next()) {
            generatedKey = rs.getInt(1);
        }
        t.setId(generatedKey);
        
        ps.close();
    }
    
    /**
     * Uredjuje i update-uje trenera u bazi podataka
     * 
     * @param t trener
     * @throws Exception 
     */
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
    
    /**
     * Vraca listu svih trenera
     * 
     * @return lista svih trenera
     * @throws Exception 
     */
    public List<Trener> vratiSveTrenere() throws Exception {
        try {
            String query = "SELECT * FROM trener t INNER JOIN teretana tr ON tr.id = t.teretana_id "
                    + "INNER JOIN grad g ON g.id=tr.grad_id ";
            
            List<Trener> list = new ArrayList<>();
            Connection connection = DbConnectionFactory.getInstance().getConnection();
            Statement stat = connection.createStatement();
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
    
    /**
     * Vraca listu svih trenera u datoj teretani
     * 
     * @param tr teretana
     * @return lista svih trenera u datoj teretani
     * @throws Exception 
     */
    public List<Trener> vratiSveTrenere(Teretana tr) throws Exception {
        try {
            String query = "SELECT * FROM trener WHERE teretana_id =" + tr.getId();
            
            List<Trener> list = new ArrayList<>();
            Connection connection = DbConnectionFactory.getInstance().getConnection();
            Statement stat = connection.createStatement();
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
    
    
    
    
    /**
     * Unosi novi individualni trening u bazu podataka
     * 
     * @param it individualni trening
     * @throws Exception 
     */
    public void dodajIndividualniTrening(IndividualniTrening it) throws Exception {
        String query = "INSERT INTO individualni_trening(nalog_id, trener_id, termin) VALUES (?,?,?)";
        Connection connection = DbConnectionFactory.getInstance().getConnection();
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setLong(1, it.getNalog().getId());
        ps.setLong(2, it.getTrener().getId());
        ps.setDate(3, Date.valueOf(it.getTermin()));
        ps.executeUpdate();
        ps.close();
    }
    
    /**
     * Vraca listu svih individualnih treninga
     * 
     * @return lista svih individualnih treninga
     * @throws Exception 
     */
    public List<IndividualniTrening> vratiSveIndividualneTreninge() throws Exception {
        try {
            String query = "SELECT * FROM individualni_trening it INNER JOIN nalog n ON n.id = it.nalog_id "
                    + "INNER JOIN trener tr ON tr.id = it.teretana_id "
                    + "INNER JOIN teretana t ON t.id = tr.teretana_id "
                    + "INNER JOIN grad g ON g.id = t.grad_id ";
            
            List<IndividualniTrening> list = new ArrayList<>();
            Connection connection = DbConnectionFactory.getInstance().getConnection();
            Statement stat = connection.createStatement();
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
    
    /**
     * Vraca listu svih individualnih treninga jednog trenera
     * @param t trener
     * @return listu svih individualnih treninga jednog trenera
     */
    public List<IndividualniTrening> vratiSveIndividualneTreninge(Trener t) {
        try {
            String query = "SELECT * FROM individualni_trening it "
                    + "INNER JOIN nalog n ON n.id = it.nalog_id WHERE it.trener_id=" + t.getId();
            
            List<IndividualniTrening> list = new ArrayList<>();
            Connection connection = DbConnectionFactory.getInstance().getConnection();
            Statement stat = connection.createStatement();
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
    
    /**
     * Vraca listu svih individualnih treninga koje je zakazao jedan nalog
     * @param n nalog
     * @return lista svih individualnih treninga koje je zakazao jedan nalog
     */
    public List<IndividualniTrening> vratiSveIndividualneTreninge(Nalog n) {
        try {
            String query = "SELECT * FROM individualni_trening it "
                    + "INNER JOIN trener tr ON tr.id = it.trener_id "
                    + "INNER JOIN teretana t ON t.id = tr.teretana_id INNER JOIN grad g ON g.id = t.grad_id WHERE it.nalog_id=" + n.getId();
            
            List<IndividualniTrening> list = new ArrayList<>();
            Connection connection = DbConnectionFactory.getInstance().getConnection();
            Statement stat = connection.createStatement();
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
                
                tr.setTeretana(t);
                
                it.setTrener(tr);
                
                list.add(it);
            }            
            rs.close();
            stat.close();
            
            popuniTeretaneIndividualniTrening(list);
            
            return list;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        } 
    }

    /**
     * Popunjava listu trenera i opreme u teretani pri vrsenju sistemskih operacija vezanih za ocene
     * 
     * @param list lista ocena
     * @throws Exception 
     */
    private void popuniTeretaneOcena(List<Ocena> list) throws Exception {       
        for (int i = 0; i < list.size(); i++) {
            list.get(i).getTeretana().setOpreme(Controller.getInstance().nadjiOpreme(list.get(i).getTeretana()));
            list.get(i).getTeretana().setTreneri(Controller.getInstance().nadjiTrenere(list.get(i).getTeretana()));
        }
    }
    
    /**
     * Popunjava listu trenera i opreme u teretani pri vrsenju sistemskih operacija vezanih za clanarine
     * 
     * @param list lista clanarina
     * @throws Exception 
     */
    private void popuniTeretaneClanarina(List<Clanarina> list) throws Exception{
        for (int i = 0; i < list.size(); i++) {
            list.get(i).getTeretana().setOpreme(Controller.getInstance().nadjiOpreme(list.get(i).getTeretana()));
            list.get(i).getTeretana().setTreneri(Controller.getInstance().nadjiTrenere(list.get(i).getTeretana()));
        }
    }
    
    /**
     * Popunjava listu trenera i opreme u teretani pri vrsenju sistemskih operacija vezanih za teretane
     * 
     * @param list lista teretana
     * @throws Exception 
     */
    private void popuniTeretaneTeretana(List<Teretana> list) throws Exception{
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setOpreme(Controller.getInstance().nadjiOpreme(list.get(i)));
            list.get(i).setTreneri(Controller.getInstance().nadjiTrenere(list.get(i)));
        
        }
    }
    
    private void popuniTeretaneIndividualniTrening(List<IndividualniTrening> list) throws Exception{
        for (int i = 0; i < list.size(); i++) {
            list.get(i).getTrener().getTeretana().setOpreme(Controller.getInstance().nadjiOpreme(list.get(i).getTrener().getTeretana()));
            list.get(i).getTrener().getTeretana().setTreneri(Controller.getInstance().nadjiTrenere(list.get(i).getTrener().getTeretana()));
        
        }
    }
}
