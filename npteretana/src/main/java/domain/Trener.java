/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.io.Serializable;
import java.util.Objects;

/**
 * Klasa Trener predstavlja trenera koji pruza individualne treninge
 * 
 * Trener ima id, ime i prezime trenera kao i teretanu u kojoj trener radi
 * 
 * @author Luka
 */
public class Trener implements Serializable{

    /**
     * Id trenera kao long
     */
    private long id;
    
    /**
     * Ime trenera kao string
     */
    private String ime;
    
    /**
     * Prezime trenera kao string
     */
    private String prezime;
    
    /**
     * Teretana u kojoj trener radi
     */
    private Teretana teretana;

    public Trener() {
    }

    public Trener(long id, String ime, String prezime, Teretana teretana) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.teretana = teretana;
    }

    public Trener(String ime, String prezime, Teretana teretana) {
        this.ime = ime;
        this.prezime = prezime;
        this.teretana = teretana;
    }

    /**
     * Vraca id trenera
     * 
     * @return id
     */
    public long getId() {
        return id;
    }

    /**
     * Postavlja id trenera
     * 
     * @param id trenera
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Vraca ime trenera
     * 
     * @return ime
     */
    public String getIme() {
        return ime;
    }

    /**
     * Postavlja ime trenera
     * 
     * @param ime trenera
     * @throws IllegalArgumentException kada je ime null ili prazan string
     */
    public void setIme(String ime) {
        if(ime == null || ime.length() == 0)
            throw new IllegalArgumentException("Ime trenera ne moze biti null ili prazan string");
        this.ime = ime;
    }

    /**
     * Vraca prezime trenera
     * 
     * @return prezime
     */
    public String getPrezime() {
        return prezime;
    }

    /**
     * Postavlja prezime trenera
     * 
     * @param prezime trenera
     * @throws IllegalArgumentException kada je prezime null ili prazan string
     */
    public void setPrezime(String prezime) {
        if(prezime == null || prezime.length() == 0)
            throw new IllegalArgumentException("Prezime trenera ne moze biti null ili prazan string");
        this.prezime = prezime;
    }

    /**
     * Vraca teretanu u kojoj trener radi
     * 
     * @return teretana
     */
    public Teretana getTeretana() {
        return teretana;
    }

    /**
     * Postavlja teretanu u kojoj trener radi
     * 
     * @param teretana u kojoj trener radi
     * @throws IllegalArgumentException teretana ne moze biti null
     */
    public void setTeretana(Teretana teretana) {
        if(teretana == null)
            throw new IllegalArgumentException("Teretana ne moze biti null");
        this.teretana = teretana;
    }

    /**
     * Override metode hashCode klase Object
     */ 
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 73 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 73 * hash + Objects.hashCode(this.ime);
        hash = 73 * hash + Objects.hashCode(this.prezime);
        hash = 73 * hash + Objects.hashCode(this.teretana);
        return hash;
    }

    /**
     * Poredi dve instance grada
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
        final Trener other = (Trener) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.ime, other.ime)) {
            return false;
        }
        if (!Objects.equals(this.prezime, other.prezime)) {
            return false;
        }
        return Objects.equals(this.teretana, other.teretana);
    }

    /**
     * Override metode toString klase Object
     */
    @Override
    public String toString() {
        return ime + " " + prezime;
    }
    
    
}
