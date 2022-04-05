/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isib.demo.crossfit.Tables;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "sites")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sites.findAll", query = "SELECT s FROM Sites s"),
    @NamedQuery(name = "Sites.findByNSites", query = "SELECT s FROM Sites s WHERE s.nSites = :nSites"),
    @NamedQuery(name = "Sites.findByRueSites", query = "SELECT s FROM Sites s WHERE s.rueSites = :rueSites"),
    @NamedQuery(name = "Sites.findByNumeroSites", query = "SELECT s FROM Sites s WHERE s.numeroSites = :numeroSites"),
    @NamedQuery(name = "Sites.findByCPSites", query = "SELECT s FROM Sites s WHERE s.cPSites = :cPSites"),
    @NamedQuery(name = "Sites.findByCommuneSites", query = "SELECT s FROM Sites s WHERE s.communeSites = :communeSites"),
    @NamedQuery(name = "Sites.GetSitesCount", query = "SELECT count(s)  FROM Sites s") })
public class Sites implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "NSites")
    private Integer nSites;
    @Basic(optional = false)
    @Column(name = "Rue_Sites")
    private String rueSites;
    @Basic(optional = false)
    @Column(name = "Numero_Sites")
    private String numeroSites;
    @Basic(optional = false)
    @Column(name = "CPSites")
    private String cPSites;
    @Basic(optional = false)
    @Column(name = "Commune_Sites")
    private String communeSites;
    @JoinColumn(name = "DNCompetition", referencedColumnName = "NCompetition")
    @ManyToOne
    private Competition dNCompetition;

    public Sites() {
    }

    public Sites(Integer nSites) {
        this.nSites = nSites;
    }

    public Sites(Integer nSites, String rueSites, String numeroSites, String cPSites, String communeSites) {
        this.nSites = nSites;
        this.rueSites = rueSites;
        this.numeroSites = numeroSites;
        this.cPSites = cPSites;
        this.communeSites = communeSites;
    }

    public Integer getNSites() {
        return nSites;
    }

    public void setNSites(Integer nSites) {
        this.nSites = nSites;
    }

    public String getRueSites() {
        return rueSites;
    }

    public void setRueSites(String rueSites) {
        this.rueSites = rueSites;
    }

    public String getNumeroSites() {
        return numeroSites;
    }

    public void setNumeroSites(String numeroSites) {
        this.numeroSites = numeroSites;
    }

    public String getCPSites() {
        return cPSites;
    }

    public void setCPSites(String cPSites) {
        this.cPSites = cPSites;
    }

    public String getCommuneSites() {
        return communeSites;
    }

    public void setCommuneSites(String communeSites) {
        this.communeSites = communeSites;
    }

    public Competition getDNCompetition() {
        return dNCompetition;
    }

    public void setDNCompetition(Competition dNCompetition) {
        this.dNCompetition = dNCompetition;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nSites != null ? nSites.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sites)) {
            return false;
        }
        Sites other = (Sites) object;
        if ((this.nSites == null && other.nSites != null) || (this.nSites != null && !this.nSites.equals(other.nSites))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "isib.demo.crossfit.Tables.Sites[ nSites=" + nSites + " ]";
    }
    
}
