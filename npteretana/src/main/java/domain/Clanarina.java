/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author Luka
 */
public class Clanarina implements Serializable{
    private long id;
    private BigDecimal cena;
    private Nalog nalog;
    private Teretana teretana;
    private LocalDate datumOd;
    private LocalDate datumDo;

    public Clanarina() {
    }

    public Clanarina(long id, BigDecimal cena, Nalog nalog, Teretana teretana, LocalDate datumOd, LocalDate datumDo) {
        this.id = id;
        this.cena = cena;
        this.nalog = nalog;
        this.teretana = teretana;
        this.datumOd = datumOd;
        this.datumDo = datumDo;
    }

    public Clanarina(BigDecimal cena, Nalog nalog, Teretana teretana, LocalDate datumOd, LocalDate datumDo) {
        this.cena = cena;
        this.nalog = nalog;
        this.teretana = teretana;
        this.datumOd = datumOd;
        this.datumDo = datumDo;
    }

    

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BigDecimal getCena() {
        return cena;
    }

    public void setCena(BigDecimal cena) {
        this.cena = cena;
    }

    public Nalog getNalog() {
        return nalog;
    }

    public void setNalog(Nalog nalog) {
        this.nalog = nalog;
    }

    public Teretana getTeretana() {
        return teretana;
    }

    public void setTeretana(Teretana teretana) {
        this.teretana = teretana;
    }

    public LocalDate getDatumOd() {
        return datumOd;
    }

    public void setDatumOd(LocalDate datumOd) {
        this.datumOd = datumOd;
    }

    public LocalDate getDatumDo() {
        return datumDo;
    }

    public void setDatumDo(LocalDate datumDo) {
        this.datumDo = datumDo;
    }   

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 53 * hash + Objects.hashCode(this.cena);
        hash = 53 * hash + Objects.hashCode(this.nalog);
        hash = 53 * hash + Objects.hashCode(this.teretana);
        hash = 53 * hash + Objects.hashCode(this.datumOd);
        hash = 53 * hash + Objects.hashCode(this.datumDo);
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
        final Clanarina other = (Clanarina) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.cena, other.cena)) {
            return false;
        }
        if (!Objects.equals(this.nalog, other.nalog)) {
            return false;
        }
        if (!Objects.equals(this.teretana, other.teretana)) {
            return false;
        }
        if (!Objects.equals(this.datumOd, other.datumOd)) {
            return false;
        }
        return Objects.equals(this.datumDo, other.datumDo);
    }
    
    
    
}
