/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.io.Serializable;
import java.util.Objects;

/**
 * Klasa Oprema predstavlja opreme koje se nalaze u teterani
 * 
 * Oprema ima id i vrstu opreme, stanjeOpreme kao i teretanu u kojoj se nalazi
 * 
 * @author Luka
 */
public class Oprema implements Serializable{

    /**
     * Id opreme kao long
     */
    private long id;
    //private int kolicina;
    
    /**
     * Stanje opreme kao string
     */
    private String stanjeOpreme;
    
    /**
     * Vrsta opreme
     */
    private VrstaOpreme vrsta;
    
    /**
     * Teretana u kojoj se oprema nalazi
     */
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
    

    /**
     * Vraca id opreme
     * 
     * @return id
     */
    public long getId() {
        return id;
    }

    /**
     * Postavlja id opreme
     * 
     * @param id opreme
     */
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

    /**
     * Vraca stanje u kom se oprema nalazi
     * 
     * @return stanjeOpreme
     */
    public String getStanjeOpreme() {
        return stanjeOpreme;
    }

    /**
     * Postavlja stanje u kom se oprema nalazi
     * 
     * @param stanjeOpreme stanje u kom se oprema nalazi
     * @throws IllegalArgumentException ako je stanje opreme null ili prazan string
     */
    public void setStanjeOpreme(String stanjeOpreme) {
        if(stanjeOpreme == null || stanjeOpreme.length() == 0)
            throw new IllegalArgumentException("Stanje opreme ne sme biti null ili prazan string");
        this.stanjeOpreme = stanjeOpreme;
    }

    /**
     * Vraca vrstu opreme
     * 
     * @return vrsta
     */
    public VrstaOpreme getVrsta() {
        return vrsta;
    }

    /**
     * Postavlja vrstu opreme
     * 
     * @param vrsta opreme
     * @throws IllegalArgumentException kada je vrsta null
     */
    public void setVrsta(VrstaOpreme vrsta) {
        if(vrsta == null)
            throw new IllegalArgumentException("Vrsta ne sme biti null");
        this.vrsta = vrsta;
    }

    /**
     * Vraca teretanu u kojoj se oprema nalazi
     * 
     * @return teretana
     */
    public Teretana getTeretana() {
        return teretana;
    }

    /**
     * Postavlja teretanu u kojoj se oprema nalazi
     * 
     * @param teretana u kojoj se oprema nalazi
     * @throws IllegalArgumentException kada je teretana null
     */
    public void setTeretana(Teretana teretana) {
        if(teretana == null)
            throw new IllegalArgumentException("Teretana ne sme biti null");        
        this.teretana = teretana;
    }

    /**
     * Override metode hashCode klase Object
     */ 
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

    /**
     * Poredi dve instance oprema
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
        final Oprema other = (Oprema) obj;
//        if (this.id != other.id) {
//            return false;
//        }
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

    /**
     * Override metode toString klase Object
     */
    @Override
    public String toString() {
        return "stanje=" + stanjeOpreme + ", vrsta=" + vrsta + ", teretana=" + teretana.getNaziv();
    }
    
    
}
