/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;


/**
 * Klasa Clanarina predstavlja clanarinu jednog korisnika u jednoj teretani
 * 
 * Clanarina ima id i cenu clanarine, nalog korisnika koji je uplatio clanarinu, teretanu u kojoj je clanarina uplacena
 * kao i datum od kad vazi clanarina i datum do kad vazi clanarina
 * 
 * @author Luka
 */
public class Clanarina implements Serializable{
	/**
     * Id clanarine kao long
     */
    private long id;
    
    /**
     * Cena clanarine kao BigDecimal
     */
    private BigDecimal cena;
    
    /**
     * Nalog korisnika koji je uplatio clanarinu
     */
    private Nalog nalog;
    
    /**
     * Teretana u kojoj je clanarina uplacena
     */
    private Teretana teretana;
    
    /**
     * Datum od kad clanarina vazi kao LocalDate
     */
    private LocalDate datumOd;
    
    /**
     * Datum do kad clanarina vazi kao LocalDate
     */
    private LocalDate datumDo;

    public Clanarina() {
    }

    public Clanarina(long id, BigDecimal cena, Nalog nalog, Teretana teretana, LocalDate datumOd, LocalDate datumDo) {
        this.id = id;
        this.cena = cena;
        this.nalog = nalog;
        this.teretana = teretana;
        this.datumOd = datumOd;
        this.datumDo = datumDo;
    }

    public Clanarina(BigDecimal cena, Nalog nalog, Teretana teretana, LocalDate datumOd, LocalDate datumDo) {
        this.cena = cena;
        this.nalog = nalog;
        this.teretana = teretana;
        this.datumOd = datumOd;
        this.datumDo = datumDo;
    }

    

    /**
     * Vraca id clanarine
     * 
     * @return id
     */
    public long getId() {
        return id;
    }

    /**
     * Postavlja id clanarine
     * @param id clanarine
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Vraca cenu clanarine
     * 
     * @return cena
     */
    public BigDecimal getCena() {
        return cena;
    }

    /**
     * Postavlja cenu clanarine u teretani
     * 
     * @param cena clanarine u teretani
     * @throws IllegalArgumentException kada je cena null ili manja ili jednaka 0
     */
    public void setCena(BigDecimal cena) {
        if(cena == null || cena.intValue() <= 0)
            throw new IllegalArgumentException("Cena ne moze biti null ili manja ili jednaka 0");
        this.cena = cena;
    }

    /**
     * Vraca nalog koji je uplatio clanarinu
     * 
     * @return nalog
     */
    public Nalog getNalog() {
        return nalog;
    }

    /**
     * Postavlja nalog koji je uplatio clanarinu u teretani
     * 
     * @param nalog koji je uplatio clanarinu u teretani
     * @throws IllegalArgumentException kada je nalog null
     */
    public void setNalog(Nalog nalog) {
        if(nalog == null)
            throw new IllegalArgumentException("Nalog ne moze biti null");
        this.nalog = nalog;
    }

    /**
     * Vraca teretanu u kojoj je korisnik uplatio clanarinu
     * 
     * @return teretana
     */
    public Teretana getTeretana() {
        return teretana;
    }

    /**
     * Postavlja teretanu u kojoj je korisnik uplatio clanarinu
     * 
     * @param teretana u kojoj je korisnik uplatio clanarinu
     * @throws IllegalArgumentException kada je teretana null
     */
    public void setTeretana(Teretana teretana) {
        if(teretana == null)
            throw new IllegalArgumentException("Teretana ne moze biti null");
        this.teretana = teretana;
    }

    /**
     * Vraca datum od kada vazi clanarina
     * 
     * @return datumOd
     */
    public LocalDate getDatumOd() {
        return datumOd;
    }

    /**
     * Postavlja datum od kada vazi clanarina
     * 
     * @param datumOd datum od kada vazi clanarina
     * @throws IllegalArgumentException kada je datumOd null
     */
    public void setDatumOd(LocalDate datumOd) {
        if(datumOd == null)
            throw new IllegalArgumentException("Datum od ne moze biti null");        
        this.datumOd = datumOd;
    }

    /**
     * Vraca datum do kada vazi clanarina
     * 
     * @return datumDo
     */
    public LocalDate getDatumDo() {
        return datumDo;
    }

    /**
     * Postavlja datum do kada vazi clanarina
     * 
     * @param datumDo datum do kada vazi clanarina
     * @throws IllegalArgumentException kada je datumDo null
     */
    public void setDatumDo(LocalDate datumDo) {
        if(datumDo == null || datumDo.isBefore(datumOd))
            throw new IllegalArgumentException("Datum do ne moze biti null i ne moze biti pre datuma od"); 
        this.datumDo = datumDo;
    }   

    /**
     * Override metode hashCode klase Object
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 53 * hash + Objects.hashCode(this.cena);
        hash = 53 * hash + Objects.hashCode(this.nalog);
        hash = 53 * hash + Objects.hashCode(this.teretana);
        hash = 53 * hash + Objects.hashCode(this.datumOd);
        hash = 53 * hash + Objects.hashCode(this.datumDo);
        return hash;
    }

    /**
     * Poredi dve instance clanarina
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Clanarina other = (Clanarina) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.cena, other.cena)) {
            return false;
        }
        if (!Objects.equals(this.nalog, other.nalog)) {
            return false;
        }
        if (!Objects.equals(this.teretana, other.teretana)) {
            return false;
        }
        if (!Objects.equals(this.datumOd, other.datumOd)) {
            return false;
        }
        return Objects.equals(this.datumDo, other.datumDo);
    }
    
    
    
}
