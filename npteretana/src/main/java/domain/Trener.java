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
public class Trener implements Serializable{
    private long id;
    private String ime;
    private String prezime;
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
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
        hash = 73 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 73 * hash + Objects.hashCode(this.ime);
        hash = 73 * hash + Objects.hashCode(this.prezime);
        hash = 73 * hash + Objects.hashCode(this.teretana);
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

    @Override
    public String toString() {
        return ime + " " + prezime;
    }
    
    
}
