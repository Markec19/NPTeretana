/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author Luka
 */
public class IndividualniTrening implements Serializable{
    private Nalog nalog;
    private Trener trener;
    private LocalDate termin;

    public IndividualniTrening() {
    }

//    public IndividualniTrening(long id, Nalog nalog_id, Trener trener_id, Date termin) {
//        this.id = id;
//        this.nalog_id = nalog_id;
//        this.trener_id = trener_id;
//        this.termin = termin;
//    }   
    
    public IndividualniTrening(Nalog nalog, Trener trener, LocalDate termin) {
        this.nalog = nalog;
        this.trener = trener;
        this.termin = termin;
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

    public Trener getTrener() {
        return trener;
    }

    public void setTrener(Trener trener) {
        this.trener = trener;
    }

    public LocalDate getTermin() {
        return termin;
    }

    public void setTermin(LocalDate termin) {
        this.termin = termin;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.nalog);
        hash = 29 * hash + Objects.hashCode(this.trener);
        hash = 29 * hash + Objects.hashCode(this.termin);
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
        final IndividualniTrening other = (IndividualniTrening) obj;
        if (!Objects.equals(this.nalog, other.nalog)) {
            return false;
        }
        if (!Objects.equals(this.trener, other.trener)) {
            return false;
        }
        return Objects.equals(this.termin, other.termin);
    }

    

    @Override
    public String toString() {
        return "IndividualniTrening{" + "nalog_id=" + nalog + ", trener_id=" + trener + ", termin=" + termin + '}';
    }  
    
    
    
}
