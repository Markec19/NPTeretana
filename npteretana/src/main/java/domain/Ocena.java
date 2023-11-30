/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.io.Serializable;
import java.util.Objects;

/**
 * Klasa Ocena predstavlja  ocenu koju je korisnik dao teretani
 * 
 * Ocena ima nalog koji je dao ocenu, teretanu kojoj je ocena data i vrednost ocene od 1 do 5
 * 
 * @author Luka
 */
public class Ocena implements Serializable {

    /**
     * Nalog korisnika koji daje ocenu teretani
     */
    private Nalog nalog;
    
    /**
     * Teretana koja je ocenjena
     */
    private Teretana teretana;
    
    /**
     * Vrednost ocene od 1 do 5 kao integer
     */
    private int vrednost;

    public Ocena() {
    }

//    public Ocena(long id, Nalog nalog_id, Teretana teretana_id, int vrednost) {
//        this.id = id;
//        this.nalog_id = nalog_id;
//        this.teretana_id = teretana_id;
//        this.vrednost = vrednost;
//    }   
    
    public Ocena(Nalog nalog, Teretana teretana, int vrednost) {
        this.nalog = nalog;
        this.teretana = teretana;
        this.vrednost = vrednost;
    }

//    public long getId() {
//        return id;
//    }
//
//    public void setId(long id) {
//        this.id = id;
//    }

    /**
     * Vraca nalog koji je ocenio teretanu
     * 
     * @return nalog
     */
    public Nalog getNalog() {
        return nalog;
    }

    /**
     * Postavlja nalog koji je ocenio teretanu
     * 
     * @param nalog koji je ocenio teretanu
     * @throws IllegalArgumentException ako je nalog null
     */
    public void setNalog(Nalog nalog) {
        if(nalog == null)
            throw new IllegalArgumentException("Nalog ne moze biti null");
        this.nalog = nalog;
    }

    /**
     * Vraca teretanu koja je ocenjena
     * 
     * @return teretana
     */
    public Teretana getTeretana() {
        return teretana;
    }

    /**
     * Postavlja teretanu koja je ocenjena
     * 
     * @param teretana koji je ocenjena
     * @throws IllegalArgumentException ako je teretana null
     */
    public void setTeretana(Teretana teretana_id) {
        if(teretana_id == null)
            throw new IllegalArgumentException("Teretana ne moze biti null");            
        this.teretana = teretana_id;
    }

    /**
     * Vraca vrednost ocene
     * @return vrednost
     */
    public int getVrednost() {
        return vrednost;
    }

    /**
     * Postavlja vrednost ocene
     * @param vrednost vrednost ocene od 1 do 5
     * @throws IllegalArgumentException ako vrednost nije izmedju 1 i 5
     */
    public void setVrednost(int vrednost) {
        if(vrednost < 1 || vrednost > 5)
            throw new IllegalArgumentException("Vrednost mora biti izmedju 1 i 5");                
        this.vrednost = vrednost;
    }

    /**
     * Poredi dve instance ocena
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
        final Ocena other = (Ocena) obj;
        if (this.vrednost != other.vrednost) {
            return false;
        }
        if (!Objects.equals(this.nalog, other.nalog)) {
            return false;
        }
        return Objects.equals(this.teretana, other.teretana);
    }
}
