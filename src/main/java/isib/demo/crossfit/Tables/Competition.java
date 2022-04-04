/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isib.demo.crossfit.Tables;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author aliou
 */
@Entity
@Table(name = "competition")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Competition.findAll", query = "SELECT c FROM Competition c"),
    @NamedQuery(name = "Competition.findByNCompetition", query = "SELECT c FROM Competition c WHERE c.nCompetition = :nCompetition"),
    @NamedQuery(name = "Competition.findByNomOrganisateur", query = "SELECT c FROM Competition c WHERE c.nomOrganisateur = :nomOrganisateur"),
    @NamedQuery(name = "Competition.findByPrenomOrganisateur", query = "SELECT c FROM Competition c WHERE c.prenomOrganisateur = :prenomOrganisateur"),
    @NamedQuery(name = "Competition.findByNomcompetition", query = "SELECT c FROM Competition c WHERE c.nomcompetition = :nomcompetition"),
    @NamedQuery(name = "Competition.findByUser", query = "SELECT c FROM Competition c WHERE c.user = :user"),
    @NamedQuery(name = "Competition.findByPassword", query = "SELECT c FROM Competition c WHERE c.password = :password"),

    @NamedQuery(name = "Competition.GetCompetitionbyNameCompetition", query = "SELECT c FROM Competition c WHERE c.nomOrganisateur = :nomOrganisateur AND c.prenomOrganisateur = :prenomOrganisateur"),
    @NamedQuery(name = "Competition.GetCompetitionCount", query = "SELECT count(c)  FROM Competition c")})
public class Competition implements Serializable {

    
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "NCompetition")
    private Integer nCompetition;
    @Basic(optional = false)
    @Column(name = "Nom_Organisateur")
    private String nomOrganisateur;
    @Basic(optional = false)
    @Column(name = "Prenom_Organisateur")
    private String prenomOrganisateur;
    @Basic(optional = false)
    @Column(name = "Nom_competition")
    private String nomcompetition;
    @Basic(optional = false)
    @Column(name = "user_")
    private String user;
    @Basic(optional = false)
    @Column(name = "password_")
    private String password;
    @JoinTable(name = "comporte", joinColumns = {
        @JoinColumn(name = "CNCompetition", referencedColumnName = "NCompetition")}, inverseJoinColumns = {
        @JoinColumn(name = "CNIE", referencedColumnName = "NIE")})
    @ManyToMany
    private Collection<Epreuve> epreuveCollection;
    @OneToMany(mappedBy = "dNCompetition")
    private Collection<Sites> sitesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "competition")
    private Collection<Inscrit> inscritCollection;

    public Competition() {
    }

    public Competition(Integer nCompetition) {
        this.nCompetition = nCompetition;
    }

    public Competition(Integer nCompetition, String nomOrganisateur, String prenomOrganisateur, String nomcompetition, String user, String password) {
        this.nCompetition = nCompetition;
        this.nomOrganisateur = nomOrganisateur;
        this.prenomOrganisateur = prenomOrganisateur;
        this.nomcompetition = nomcompetition;
        this.user = user;
        this.password = password;
    }

    public Integer getNCompetition() {
        return nCompetition;
    }

    public void setNCompetition(Integer nCompetition) {
        this.nCompetition = nCompetition;
    }

    public String getNomOrganisateur() {
        return nomOrganisateur;
    }

    public void setNomOrganisateur(String nomOrganisateur) {
        this.nomOrganisateur = nomOrganisateur;
    }

    public String getPrenomOrganisateur() {
        return prenomOrganisateur;
    }

    public void setPrenomOrganisateur(String prenomOrganisateur) {
        this.prenomOrganisateur = prenomOrganisateur;
    }

    public String getNomcompetition() {
        return nomcompetition;
    }

    public void setNomcompetition(String nomcompetition) {
        this.nomcompetition = nomcompetition;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @XmlTransient
    public Collection<Epreuve> getEpreuveCollection() {
        return epreuveCollection;
    }

    public void setEpreuveCollection(Collection<Epreuve> epreuveCollection) {
        this.epreuveCollection = epreuveCollection;
    }

    @XmlTransient
    public Collection<Sites> getSitesCollection() {
        return sitesCollection;
    }

    public void setSitesCollection(Collection<Sites> sitesCollection) {
        this.sitesCollection = sitesCollection;
    }

    @XmlTransient
    public Collection<Inscrit> getInscritCollection() {
        return inscritCollection;
    }

    public void setInscritCollection(Collection<Inscrit> inscritCollection) {
        this.inscritCollection = inscritCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nCompetition != null ? nCompetition.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Competition)) {
            return false;
        }
        Competition other = (Competition) object;
        if ((this.nCompetition == null && other.nCompetition != null) || (this.nCompetition != null && !this.nCompetition.equals(other.nCompetition))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "isib.demo.crossfit.Tables.Competition[ nCompetition=" + nCompetition + " ]";
    }
    
   
}
