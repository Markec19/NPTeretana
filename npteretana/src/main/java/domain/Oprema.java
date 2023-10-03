/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Luka
 */
public class Oprema implements Serializable{
    private long id;
    //private int kolicina;
    private String stanjeOpreme;
    private VrstaOpreme vrsta;
    private Teretana teretana;

    public Oprema() {
    }

    public Oprema(long id, String stanjeOpreme, VrstaOpreme vrsta, Teretana teretana) {
        this.id = id;
        //this.kolicina = kolicina;
        this.stanjeOpreme = stanjeOpreme;
        this.vrsta = vrsta;
        this.teretana = teretana;
    }

    public Oprema(String stanjeOpreme, VrstaOpreme vrsta, Teretana teretana) {
        //this.kolicina = kolicina;
        this.stanjeOpreme = stanjeOpreme;
        this.vrsta = vrsta;
        this.teretana = teretana;
    }
    

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

//    public int getKolicina() {
//        return kolicina;
//    }
//
//    public void setKolicina(int kolicina) {
//        this.kolicina = kolicina;
//    }

    public String getStanjeOpreme() {
        return stanjeOpreme;
    }

    public void setStanjeOpreme(String stanjeOpreme) {
        this.stanjeOpreme = stanjeOpreme;
    }

    public VrstaOpreme getVrsta() {
        return vrsta;
    }

    public void setVrsta(VrstaOpreme vrsta) {
        this.vrsta = vrsta;
    }

    public Teretana getTeretana() {
        return teretana;
    }

    public void setTeretana(Teretana teretana) {
        this.teretana = teretana;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + (int) (this.id ^ (this.id >>> 32));
        //hash = 71 * hash + this.kolicina;
        hash = 71 * hash + Objects.hashCode(this.stanjeOpreme);
        hash = 71 * hash + Objects.hashCode(this.vrsta);
        hash = 71 * hash + Objects.hashCode(this.teretana);
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
        final Oprema other = (Oprema) obj;
        if (this.id != other.id) {
            return false;
        }
//        if (this.kolicina != other.kolicina) {
//            return false;
//        }
        if (!Objects.equals(this.stanjeOpreme, other.stanjeOpreme)) {
            return false;
        }
        if (!Objects.equals(this.vrsta, other.vrsta)) {
            return false;
        }
        return Objects.equals(this.teretana, other.teretana);
    }        

    @Override
    public String toString() {
        return "Oprema{" + ", stanje=" + stanjeOpreme + ", vrsta=" + vrsta + ", teretana=" + teretana.getNaziv() + '}';
    }
    
    
}
