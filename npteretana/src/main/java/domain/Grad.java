/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.io.Serializable;
import java.util.Objects;

/**
 * Klasa Grad predstavlja gradove u kojima se nalaze teretane
 * 
 * Grad ima id i naziv grada
 * 
 * @author Luka
 */
public class Grad implements Serializable {

    /**
     * Id grada kao long
     */
    private long id;
    
    /**
     * Naziv grada kao string
     */
    private String naziv;

    /**
     * Kreira objekat grad kojem nisu dodeljene konkretne vrednosti
     */
    public Grad() {
    }

    /**
     * Postavlja naziv na unetu vrednost
     * 
     * @param naziv naziv grada
     */
    public Grad(String naziv) {
        this.naziv = naziv;
    }    
    
    /**
     * Postavlja id i naziv na unetu vrednost
     * 
     * @param id id grada
     * @param naziv naziv grada
     */
    public Grad(long id, String naziv) {
        this.id = id;
        this.naziv = naziv;
    }

    /**
     * Vraca id grada
     * 
     * @return id
     */
    public long getId() {
        return id;
    }

    /**
     * Postavlja id grada
     * 
     * @param id      * 
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Vraca naziv grada
     * 
     * @return naziv
     */
    public String getNaziv() {
        return naziv;
    }

    /**
     * Postavlja naziv grada
     * 
     * @param naziv grada
     * @throws IllegalArgumentException ako je naziv null ili prazan string
     */
    public void setNaziv(String naziv) {
        if(naziv == null || naziv.length() == 0)
            throw new IllegalArgumentException("Naziv grada ne sme biti null ili prazan string");
        this.naziv = naziv;
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
        final Grad other = (Grad) obj;
//        if (this.id != other.id) {
//            return false;
//        }
        return Objects.equals(this.naziv, other.naziv);
    }

    /**
     * Override metode toString klase Object
     */
    @Override
    public String toString() {
        return naziv;
    }
}
