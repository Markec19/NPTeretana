/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Klasa Teretana predstavlja jednu teretanu u odredjenom gradu
 * 
 * Teretana ima id, naziv, adresu i prosecnu ocenu teretane, grad u kom se teretana nalazi, kao i listu oprema i trenera te teretane
 * Liste sa opremama i trenerima su implementirane preko ArrayList klase
 * 
 * @author Luka
 */
public class Teretana implements Serializable {

    /**
     * Id teretane kao long
     */
    private long id;
    
    /**
     * Naziv teretane kao string
     */
    private String naziv;
    
    /**
     * Adresa teretane kao string
     */
    private String adresa;
    
    /**
     * Prosecna ocena teretane kao BigDecimal
     */
    private BigDecimal prosecnaOcena;
    
    /**
     * Grad u kome se teretana nalazi
     */
    private Grad grad;
    
    /**
     * Lista sa opremama u teretani
     */
    private List<Oprema> opreme;
    
    /**
     * Lista sa trenerima u teretani
     */
    private List<Trener> treneri;

    /**
     * Kreira objekat teretana kojem nisu dodeljene konkretne vrednosti 
     */
    public Teretana() {
    }

    /**
     * Postavlja id, naziv, adresu, prosecnu ocenu, grad, opremu i trenere na unete vrednosti
     * 
     * @param id id teretane
     * @param naziv naziv teretane
     * @param adresa adresa teretane
     * @param prosecnaOcena prosecna ocena teretane
     * @param grad grad u kom se teretana nalazi
     * @param opreme lista oprema u teretani
     * @param treneri lista trenera u teretani 
     */
    public Teretana(long id, String naziv, String adresa, BigDecimal prosecnaOcena, Grad grad, List<Oprema> opreme, List<Trener> treneri) {
        this.id = id;
        this.naziv = naziv;
        this.adresa = adresa;
        this.prosecnaOcena = prosecnaOcena;
        this.grad = grad;
        this.opreme = opreme;
        this.treneri = treneri;
    }    
    
    /**
     * Postavlja id, naziv, adresu, prosecnu ocenu i grad na unete vrednosti
     * 
     * @param id id teretane
     * @param naziv naziv teretane
     * @param adresa adresa teretane
     * @param prosecnaOcena prosecna ocena teretane
     * @param grad grad u kom se teretana nalazi
     */
    public Teretana(long id, String naziv, String adresa, BigDecimal prosecnaOcena, Grad grad) {
        this.id = id;
        this.naziv = naziv;
        this.adresa = adresa;
        this.prosecnaOcena = prosecnaOcena;
        this.grad = grad;
        this.opreme = new ArrayList<>();
    }

    /**
     * Postavlja naziv, adresu, prosecnu ocenu i grad na unete vrednosti
     * 
     * @param naziv naziv teretane
     * @param adresa adresa teretane
     * @param prosecnaOcena prosecna ocena teretane
     * @param grad grad u kom se teretana nalazi
     */
    public Teretana(String naziv, String adresa, BigDecimal prosecnaOcena, Grad grad) {
        this.naziv = naziv;
        this.adresa = adresa;
        this.prosecnaOcena = prosecnaOcena;
        this.grad = grad;
        this.opreme = new ArrayList<>();
    }

    /**
     * Postavlja id, naziv, adresu i grad na unete vrednosti
     * 
     * @param id id teretane
     * @param naziv naziv teretane
     * @param adresa adresa teretane
     * @param grad grad u kom se teretana nalazi
     */
    public Teretana(long id, String naziv, String adresa, Grad grad) {
        this.id = id;
        this.naziv = naziv;
        this.adresa = adresa;
        this.grad = grad;
    }    
    
    /**
     * Postavlja naziv, adresu i grad na unete vrednosti
     * 
     * @param naziv naziv teretane
     * @param adresa adresa teretane
     * @param grad grad u kom se teretana nalazi
     */
    public Teretana(String naziv, String adresa, Grad grad) {
        this.naziv = naziv;
        this.adresa = adresa;
        this.grad = grad;
    }   
        

    /**
     * Vraca id teretane
     * 
     * @return id
     */
    public long getId() {
        return id;
    }

    /**
     * Postavlja id teretane
     * 
     * @param id 
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Vraca naziv teretane
     * 
     * @return naziv teretane
     */
    public String getNaziv() {
        return naziv;
    }

    /**
     * Postavlja naziv teretane
     * 
     * @param naziv teretane
     * @throws IllegalArgumentException kada je naziv teretane null ili prazan string
     */
    public void setNaziv(String naziv) {
        if(naziv == null || naziv.length() == 0)
            throw new IllegalArgumentException("Naziv teretane ne sme biti null ili prazan string");
        this.naziv = naziv;
    }

    /**
     * Vraca adresu teretane
     * 
     * @return adresa
     */
    public String getAdresa() {
        return adresa;
    }

    /**
     * Postavlja adresu teretane
     * 
     * @param adresa teretane
     * @throws IllegalArgumentException kada je adresa teretane null ili prazan string
     */
    public void setAdresa(String adresa) {
        if(adresa == null || adresa.length() == 0)
            throw new IllegalArgumentException("Adresa teretane ne sme biti null ili prazan string");
        this.adresa = adresa;
    }

    /**
     * Vraca prosecnu ocenu teretane
     * 
     * @return prosecnaOcena
     */
    public BigDecimal getProsecnaOcena() {
        return prosecnaOcena;
    }

    /**
     * Postavlja prosecnu ocenu teretane
     * 
     * @param prosecna_ocena prosecna ocena teretane
     * @throws IllegalArgumentException kada je prosecna ocena null ili manja od 1
     */
    public void setProsecnaOcena(BigDecimal prosecna_ocena) {
        if(prosecna_ocena != null && prosecna_ocena.intValue() < 1)
            throw new IllegalArgumentException("Prosecna ocena mora biti jednaka ili veca od 1");
        this.prosecnaOcena = prosecna_ocena;
    }
    
    /**
     * Vraca grad u kom se nalazi teretana
     * 
     * @return grad
     */
    public Grad getGrad() {
        return grad;
    }

    /**
     * Postavlja grad u kom se nalazi teretana
     * 
     * @param gradId grad u kom se nalazi teretana
     * @throws IllegalArgumentException kada je grad null
     */
    public void setGrad(Grad gradId) {
        if(gradId == null)
            throw new IllegalArgumentException("Grad ne sme biti null");
        this.grad = gradId;
    }

    /**
     * Vraca listu sa svom opremom koja se nalazi u teretani
     * 
     * @return Listu sa svom opremom u teretani
     */
    public List<Oprema> getOpreme() {
        return opreme;
    }

    /**
     * Postavlja listu sa opremama u teretani
     * Lista sme biti null ili prazna ako teretana nema opremu
     * 
     * @param opreme lista svih oprema u teretani
     */
    public void setOpreme(List<Oprema> opreme) {
        this.opreme = opreme;
    }

    /**
     * Vraca listu sa svim trenerima u teretani
     * 
     * @return Listu sa svim trenerima u teretani
     */
    public List<Trener> getTreneri() {
        return treneri;
    }

    /**
     * Postavlja listu sa trenerima u teretani
     * Lista sme biti null ili prazna ako teretana nema opremu
     * @param treneri lista svih trenera u teretani
     */
    public void setTreneri(List<Trener> treneri) {
        this.treneri = treneri;
    }

    /**
     * Poredi dve instance teretana
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
        final Teretana other = (Teretana) obj;
//        if (this.id != other.id) {
//            return false;
//        }
        if (!Objects.equals(this.naziv, other.naziv)) {
            return false;
        }
        if (!Objects.equals(this.adresa, other.adresa)) {
            return false;
        }
//        if (!Objects.equals(this.prosecnaOcena, other.prosecnaOcena)) {
//            return false;
//        }
        return Objects.equals(this.grad, other.grad);
    }    
    
    /**
     * Override metode toString klase Object
     */
    @Override
    public String toString() {
        return naziv;
    }
    
    /**
     * Dodaje novu opremu u listu oprema date teretane
     * 
     * @param oprema nova oprema
     * @throws IllegalArgumentException kada je oprema null
     */
    private void addOprema(Oprema oprema){
        if(oprema == null)
            throw new IllegalArgumentException("Oprema ne moze biti null");
        opreme.add(oprema);
        oprema.setTeretana(this);
    }
    
    /**
     * Dodaje novog trenera u listu trenera date teretane
     * 
     * @param trener novi trener
     * @throws IllegalArgumentException kada je trener null
     */
    private void addTrenera(Trener trener){
        if(trener == null)
            throw new IllegalArgumentException("Trener ne moze biti null");
        treneri.add(trener);
        trener.setTeretana(this);
    }
    
}
