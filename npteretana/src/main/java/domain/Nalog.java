/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.io.Serializable;
import java.util.Objects;

/**
 * Klasa Nalog predstavlja nalog korisnika koji moze da koristi aplikaciju
 * 
 * Nalog ima id, ime i prezime korisnika naloga, korisnicko ime naloga i sifru naloga
 * 
 * @author Luka
 */
public class Nalog implements Serializable{

	
    /**
     * Id naloga kao long
     */
    private long id;
    
    /**
     * Ime korisnika naloga kao string
     */
    private String ime;
    
    /**
     * Prezime korisnika naloga kao string
     */
    private String prezime;
    
    /**
     * Korisnicko ime naloga kao string
     */
    private String korisnickoIme;
    
    /**
     * Sifra naloga kao string
     */
    private String sifra;

    public Nalog() {
    }       

    public Nalog(String korisnickoIme, String sifra) {
		this.korisnickoIme = korisnickoIme;
		this.sifra = sifra;
	}

	public Nalog(long id, String ime, String prezime, String korisnickoIme, String sifra) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.korisnickoIme = korisnickoIme;
        this.sifra = sifra;
    }

    public Nalog(String ime, String prezime, String korisnickoIme, String sifra) {
        this.ime = ime;
        this.prezime = prezime;
        this.korisnickoIme = korisnickoIme;
        this.sifra = sifra;
    }

    /**
     * Vraca id naloga
     * 
     * @return id 
     */
    public long getId() {
        return id;
    }

    /**
     * Postavlja id naloga
     * 
     * @param id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Vraca ime korisnika naloga
     * 
     * @return ime
     */
    public String getIme() {
        return ime;
    }

    /**
     * Postavlja ime korisnika naloga
     * 
     * @param ime korisnika
     * @throws IllegalArgumentException kada je ime korisnika null ili prazan string
     */
    public void setIme(String ime) {
        if(ime == null || ime.length() == 0)
            throw new IllegalArgumentException("Ime korisnika ne moze biti null ili prazan string");
        this.ime = ime;
    }

    /**
     * Vraca prezime korisnika naloga
     * 
     * @return prezime
     */
    public String getPrezime() {
        return prezime;
    }

    /**
     * Postavlja prezime korisnika naloga
     * 
     * @param prezime korisnika
     * @throws IllegalArgumentException kada je prezime korisnika null ili prazan string
     */
    public void setPrezime(String prezime) {
        if(prezime == null || prezime.length() == 0)
            throw new IllegalArgumentException("Prezime korisnika ne moze biti null ili prazan string");
        this.prezime = prezime;
    }

    /**
     * Vraca korisnicko ime naloga
     * 
     * @return korisnickoIme
     */
    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    /**
     * Postavlja korisnicko ime naloga
     * 
     * @param korisnickoIme naloga
     * @throws IllegalArgumentException kada je korisnicko ime null ili prazan string
     */
    public void setKorisnickoIme(String korisnickoIme) {
        if(korisnickoIme == null || korisnickoIme.length() == 0)
            throw new IllegalArgumentException("Korisnicko ime ne moze biti null ili prazan string");
        this.korisnickoIme = korisnickoIme;
    }

    /**
     * Vraca sifru naloga
     * 
     * @return sifra
     */
    public String getSifra() {
        return sifra;
    }

    /**
     * Postavlja sifru naloga
     * 
     * @param sifra naloga
     * @throws IllegalArgumentException kada je sifra null ili prazan string
     */
    public void setSifra(String sifra) {
        if(sifra == null || sifra.length() == 0)
            throw new IllegalArgumentException("Sifra ne moze biti null ili prazan string");
        this.sifra = sifra;
    }

    /**
     * Poredi dve instance naloga
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
        final Nalog other = (Nalog) obj;
//        if (this.id != other.id) {
//            return false;
//        }
        if (!Objects.equals(this.ime, other.ime)) {
            return false;
        }
        if (!Objects.equals(this.prezime, other.prezime)) {
            return false;
        }
        if (!Objects.equals(this.korisnickoIme, other.korisnickoIme)) {
            return false;
        }
        return Objects.equals(this.sifra, other.sifra);
    }

    /**
     * Override metode toString klase Object
     */
    @Override
    public String toString() {
        return "ime=" + ime + ", prezime=" + prezime + ", korisnicko ime=" + korisnickoIme;
    }   
    
    
}
