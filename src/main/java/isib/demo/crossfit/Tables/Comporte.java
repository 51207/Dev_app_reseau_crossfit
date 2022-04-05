/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isib.demo.crossfit.Tables;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author aliou
 */
@Entity
@Table(name = "comporte")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Comporte.findAll", query = "SELECT c FROM Comporte c")
    //@NamedQuery(name = "Comporte.findByCncompetition", query = "SELECT c FROM Comporte c WHERE c.comportePK.CNCompetition = :comportePK.CNCompetition"),
    //@NamedQuery(name = "Comporte.findByCnie", query = "SELECT c FROM Comporte c WHERE c.comportePK.CNIE =:comportePK.CNIE "),
   })

public class Comporte implements Serializable {
  private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ComportePK comportePK;
    @JoinColumn(name = "cncompetition", referencedColumnName = "NCompetition", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Competition competition;
    @JoinColumn(name = "cnie", referencedColumnName = "NIE", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Epreuve epreuve;

    
     public Comporte() {
    }

    public Comporte(ComportePK comportePK) {
        this.comportePK = comportePK;
    }

    public Comporte(Competition competition, Epreuve epreuve) {
        this.competition = competition;
        this.epreuve = epreuve;
    }

    public ComportePK getComportePK() {
        return comportePK;
    }

    public void setComportePK(ComportePK comportePK) {
        this.comportePK = comportePK;
    }

    public Competition getCompetition() {
        return competition;
    }

    public void setCompetition(Competition competition) {
        this.competition = competition;
    }

    public Epreuve getEpreuve() {
        return epreuve;
    }

    public void setEpreuve(Epreuve epreuve) {
        this.epreuve = epreuve;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.comportePK);
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
        final Comporte other = (Comporte) obj;
        return Objects.equals(this.comportePK, other.comportePK);
    }

    @Override
    public String toString() {
        return "Comporte{" + "comportePK=" + comportePK + '}';
    }

   
   
    
    
}
