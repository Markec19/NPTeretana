/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.io.Serializable;
import java.util.Objects;

/**
 * Klasa VrstaOpreme predstavlja vrste opreme koje mogu biti u teretani
 * 
 * VrstaOpreme ima id i naziv vrste opreme
 * 
 * @author Luka
 */
public class VrstaOpreme implements Serializable{

    /**
     * Id vrste opreme kao long
     */
    private long id;
    
    /**
     * Naziv vrste opreme kao string
     */
    private String vrsta;

    /**
     * Kreira objekat VrstaOpreme kojem nisu dodeljene konkretne vrednosti
     */
    public VrstaOpreme() {
    }

    /**
     * Postavlja id i vrstu na unete vrednosti
     * 
     * @param id id vrste opreme
     * @param vrsta vrsta opreme
     */
    public VrstaOpreme(long id, String vrsta) {
        this.id = id;
        this.vrsta = vrsta;
    }

    /**
     * Postavlja vrstu na unetu vrednost
     * 
     * @param vrsta vrsta opreme
     */
    public VrstaOpreme(String vrsta) {
        this.vrsta = vrsta;
    }

    /**
     * Vraca id vrste opreme
     * 
     * @return id
     */
    public long getId() {
        return id;
    }

    /**
     * Postavlja id vrste opreme
     * 
     * @param id vrste opreme
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Vraca naziv vrste opreme
     * 
     * @return vrsta naziv vrste opreme
     */
    public String getVrsta() {
        return vrsta;
    }

    /**
     * Postavlja naziv vrste opreme
     * 
     * @param vrsta naziv vrste opreme
     * @throws IllegalArgumentException kada je vrsta null ili prazan string
     */
    public void setVrsta(String vrsta) {
        if(vrsta == null || vrsta.length() == 0)
            throw new IllegalArgumentException("Naziv vrste ne sme biti null ili prazan string");
        this.vrsta = vrsta;
    }

    /**
     * Poredi dve instance vrsta opreme
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
        final VrstaOpreme other = (VrstaOpreme) obj;
//        if (this.id != other.id) {
//            return false;
//        }
        return Objects.equals(this.vrsta, other.vrsta);
    }

    /**
     * Override metode toString klase Object
     */
    @Override
    public String toString() {
        return vrsta;
    }
}
