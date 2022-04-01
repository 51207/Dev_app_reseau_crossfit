/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isib.demo.crossfit.Tables;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
@Table(name = "inscrit")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Inscrit.findAll", query = "SELECT i FROM Inscrit i"),
    @NamedQuery(name = "Inscrit.findByINCompetition", query = "SELECT i FROM Inscrit i WHERE i.inscritPK.iNCompetition = :iNCompetition"),
    @NamedQuery(name = "Inscrit.findByINic", query = "SELECT i FROM Inscrit i WHERE i.inscritPK.iNic = :iNic"),
    @NamedQuery(name = "Inscrit.findByIdate", query = "SELECT i FROM Inscrit i WHERE i.inscritPK.idate = :idate")})
public class Inscrit implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected InscritPK inscritPK;
    @JoinColumn(name = "I_NIC", referencedColumnName = "NIC", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Clients clients;
    @JoinColumn(name = "I_NCompetition", referencedColumnName = "NCompetition", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Competition competition;

    public Inscrit() {
    }

    public Inscrit(InscritPK inscritPK) {
        this.inscritPK = inscritPK;
    }

    public Inscrit(int iNCompetition, int iNic, String idate) {
        this.inscritPK = new InscritPK(iNCompetition, iNic, idate);
    }

    public InscritPK getInscritPK() {
        return inscritPK;
    }

    public void setInscritPK(InscritPK inscritPK) {
        this.inscritPK = inscritPK;
    }

    public Clients getClients() {
        return clients;
    }

    public void setClients(Clients clients) {
        this.clients = clients;
    }

    public Competition getCompetition() {
        return competition;
    }

    public void setCompetition(Competition competition) {
        this.competition = competition;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (inscritPK != null ? inscritPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Inscrit)) {
            return false;
        }
        Inscrit other = (Inscrit) object;
        if ((this.inscritPK == null && other.inscritPK != null) || (this.inscritPK != null && !this.inscritPK.equals(other.inscritPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "isib.demo.crossfit.Tables.Inscrit[ inscritPK=" + inscritPK + " ]";
    }
    
}
