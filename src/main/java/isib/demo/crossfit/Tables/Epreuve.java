/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isib.demo.crossfit.Tables;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author aliou
 */
@Entity
@Table(name = "epreuve")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Epreuve.findAll", query = "SELECT e FROM Epreuve e"),
    @NamedQuery(name = "Epreuve.findByNie", query = "SELECT e FROM Epreuve e WHERE e.nie = :nie"),
    @NamedQuery(name = "Epreuve.findByNEpreuve", query = "SELECT e FROM Epreuve e WHERE e.nEpreuve = :nEpreuve"),
    @NamedQuery(name = "Epreuve.findByTimes", query = "SELECT e FROM Epreuve e WHERE e.times = :times"),
    @NamedQuery(name = "Epreuve.GetEpreuvebyNom", query = "SELECT e FROM Epreuve e WHERE e.nEpreuve = :nEpreuve"),
    @NamedQuery(name = "Epreuve.GetEpreuveCount", query = "SELECT count(e)  FROM Epreuve e")})
public class Epreuve implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "NIE")
    private Integer nie;
    @Basic(optional = false)
    @Column(name = "NEpreuve")
    private String nEpreuve;
    @Basic(optional = false)
    @Column(name = "times")
    private String times;
    @ManyToMany(mappedBy = "epreuveCollection")
    private Collection<Competition> competitionCollection;

    public Epreuve() {
    }

    public Epreuve(Integer nie) {
        this.nie = nie;
    }

    public Epreuve(Integer nie, String nEpreuve, String times) {
        this.nie = nie;
        this.nEpreuve = nEpreuve;
        this.times = times;
    }

    public Integer getNie() {
        return nie;
    }

    public void setNie(Integer nie) {
        this.nie = nie;
    }

    public String getNEpreuve() {
        return nEpreuve;
    }

    public void setNEpreuve(String nEpreuve) {
        this.nEpreuve = nEpreuve;
    }

    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }

    @XmlTransient
    public Collection<Competition> getCompetitionCollection() {
        return competitionCollection;
    }

    public void setCompetitionCollection(Collection<Competition> competitionCollection) {
        this.competitionCollection = competitionCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nie != null ? nie.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Epreuve)) {
            return false;
        }
        Epreuve other = (Epreuve) object;
        if ((this.nie == null && other.nie != null) || (this.nie != null && !this.nie.equals(other.nie))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "isib.demo.crossfit.Tables.Epreuve[ nie=" + nie + " ]";
    }
    
}
