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
 *
 * @author Luka
 */
public class Teretana implements Serializable {
    private long id;
    private String naziv;
    private String adresa;
    private BigDecimal prosecnaOcena;
    private Grad grad;
    private List<Oprema> opreme;
    private List<Trener> treneri;

    public Teretana() {
    }

    public Teretana(long id, String naziv, String adresa, BigDecimal prosecnaOcena, Grad grad, List<Oprema> opreme, List<Trener> treneri) {
        this.id = id;
        this.naziv = naziv;
        this.adresa = adresa;
        this.prosecnaOcena = prosecnaOcena;
        this.grad = grad;
        this.opreme = opreme;
        this.treneri = treneri;
    }    
    
    public Teretana(long id, String naziv, String adresa, BigDecimal prosecnaOcena, Grad grad) {
        this.id = id;
        this.naziv = naziv;
        this.adresa = adresa;
        this.prosecnaOcena = prosecnaOcena;
        this.grad = grad;
        this.opreme = new ArrayList<>();
    }

    public Teretana(String naziv, String adresa, BigDecimal prosecnaOcena, Grad grad) {
        this.naziv = naziv;
        this.adresa = adresa;
        this.prosecnaOcena = prosecnaOcena;
        this.grad = grad;
        this.opreme = new ArrayList<>();
    }

    public Teretana(long id, String naziv, String adresa, Grad grad) {
        this.id = id;
        this.naziv = naziv;
        this.adresa = adresa;
        this.grad = grad;
    }    
    
    public Teretana(String naziv, String adresa, Grad grad) {
        this.naziv = naziv;
        this.adresa = adresa;
        this.grad = grad;
    }   
        

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public BigDecimal getProsecnaOcena() {
        return prosecnaOcena;
    }

    public void setProsecnaOcena(BigDecimal prosecna_ocena) {
        this.prosecnaOcena = prosecna_ocena;
    }

    public Grad getGrad() {
        return grad;
    }

    public void setGrad(Grad gradId) {
        this.grad = gradId;
    }

    public List<Oprema> getOpreme() {
        return opreme;
    }

    public void setOpreme(List<Oprema> opreme) {
        this.opreme = opreme;
    }

    public List<Trener> getTreneri() {
        return treneri;
    }

    public void setTreneri(List<Trener> treneri) {
        this.treneri = treneri;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 67 * hash + Objects.hashCode(this.naziv);
        hash = 67 * hash + Objects.hashCode(this.adresa);
        hash = 67 * hash + Objects.hashCode(this.prosecnaOcena);
        hash = 67 * hash + Objects.hashCode(this.grad);
        return hash;
    }

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
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.naziv, other.naziv)) {
            return false;
        }
        if (!Objects.equals(this.adresa, other.adresa)) {
            return false;
        }
        if (!Objects.equals(this.prosecnaOcena, other.prosecnaOcena)) {
            return false;
        }
        return Objects.equals(this.grad, other.grad);
    }    
    
    @Override
    public String toString() {
        return naziv;
    }
    
    private void addOprema(Oprema oprema){
        opreme.add(oprema);
        oprema.setTeretana(this);
    }
    
    private void addTrenera(Trener trener){
        treneri.add(trener);
        trener.setTeretana(this);
    }
    
}
