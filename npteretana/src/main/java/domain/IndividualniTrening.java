/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Klasa IndividualniTrening predstavlja individualni trening organizovan izmedju korisnika teretane i trenera u toj teretani
 * 
 * IndividualniTrening ima nalog koji zakazuje individualni trening, trenera koji drzi individualni trening i termin treninga
 * 
 * @author Luka
 */
public class IndividualniTrening implements Serializable{

    /**
     * Nalog koji zakazuje individualni trening
     */
    private Nalog nalog;
    
    /**
     * Trener koji drzi individualni trening
     */
    private Trener trener;
    
    /**
     * Termin treninga kao LocalDate
     */
    private LocalDate termin;

    /**
     * Kreira objekat IndividualniTrening kojem nisu dodeljene konkretne vrednosti
     */
    public IndividualniTrening() {
    }

//    public IndividualniTrening(long id, Nalog nalog_id, Trener trener_id, Date termin) {
//        this.id = id;
//        this.nalog_id = nalog_id;
//        this.trener_id = trener_id;
//        this.termin = termin;
//    }   
    
    /**
     * Postavlja nalog, trenera i termin na unete vrednosti
     * 
     * @param nalog nalog korisnika koji zakazuje individualni trening
     * @param trener trener kod kog je zakazan individualni trening
     * @param termin termin individualnog treninga
     */
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

    /**
     * Vraca nalog koji zakazuje individualni trening
     * 
     * @return nalog
     */
    public Nalog getNalog() {
        return nalog;
    }

    /**
     * Postavlja nalog koji zakazuje individualni trening
     * 
     * @param nalog koji zakazuje individualni trening
     * @throws IllegalArgumentException kada je nalog null
     */
    public void setNalog(Nalog nalog) {
        if(nalog == null)
            throw new IllegalArgumentException("Nalog ne sme biti null");
        this.nalog = nalog;
    }

    /**
     * Trener koji drzi individualni trening
     * 
     * @return trener
     */
    public Trener getTrener() {
        return trener;
    }

    /**
     * Postavlja trenera koji drzi individualni trening
     * 
     * @param trener koji drzi individualni trening
     * @throws IllegalArgumentException kada je trener null
     */
    public void setTrener(Trener trener) {
        if(trener == null)
            throw new IllegalArgumentException("Trener ne sme biti null");
        this.trener = trener;
    }

    /**
     * Vraca termin treninga
     * 
     * @return termin
     */
    public LocalDate getTermin() {
        return termin;
    }

    /**
     * Postavlja termin treninga
     * 
     * @param termin treninga
     * @throws IllegalArgumentException kada je termin null ili ako je u proslosti
     */
    public void setTermin(LocalDate termin) {
        if(termin == null || termin.isBefore(LocalDate.now()))
            throw new IllegalArgumentException("Termin ne moze biti null i ne moze biti u proslosti");
        this.termin = termin;
    }

    /**
     * Poredi dve instance grada
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
        final IndividualniTrening other = (IndividualniTrening) obj;
        if (!Objects.equals(this.nalog, other.nalog)) {
            return false;
        }
        if (!Objects.equals(this.trener, other.trener)) {
            return false;
        }
        return Objects.equals(this.termin, other.termin);
    }

    
    /**
     * Override metode toString klase Object
     */
    @Override
    public String toString() {
        return "nalog=" + nalog.getKorisnickoIme() + ", trener=" + trener + ", termin=" + termin;
    }  
    
    
    
}
