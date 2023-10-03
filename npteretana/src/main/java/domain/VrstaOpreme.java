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
public class VrstaOpreme implements Serializable{
    private long id;
    private String vrsta;

    public VrstaOpreme() {
    }

    public VrstaOpreme(long id, String vrsta) {
        this.id = id;
        this.vrsta = vrsta;
    }

    public VrstaOpreme(String vrsta) {
        this.vrsta = vrsta;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getVrsta() {
        return vrsta;
    }

    public void setVrsta(String vrsta) {
        this.vrsta = vrsta;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 73 * hash + Objects.hashCode(this.vrsta);
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
        final VrstaOpreme other = (VrstaOpreme) obj;
        if (this.id != other.id) {
            return false;
        }
        return Objects.equals(this.vrsta, other.vrsta);
    }

    @Override
    public String toString() {
        return vrsta;
    }
    
    
}
