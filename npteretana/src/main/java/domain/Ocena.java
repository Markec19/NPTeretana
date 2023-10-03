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
public class Ocena implements Serializable {
    private Nalog nalog;
    private Teretana teretana;
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

    public Nalog getNalog() {
        return nalog;
    }

    public void setNalog(Nalog nalog) {
        this.nalog = nalog;
    }

    public Teretana getTeretana() {
        return teretana;
    }

    public void setTeretana(Teretana teretana_id) {
        this.teretana = teretana_id;
    }

    public int getVrednost() {
        return vrednost;
    }

    public void setVrednost(int vrednost) {
        this.vrednost = vrednost;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + Objects.hashCode(this.nalog);
        hash = 43 * hash + Objects.hashCode(this.teretana);
        hash = 43 * hash + this.vrednost;
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
