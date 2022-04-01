/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isib.demo.crossfit.Tables;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.Table;
import javax.websocket.Session;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author aliou
 */
@Entity
@Table(name = "clients")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Clients.findAll", query = "SELECT c FROM Clients c"),
    @NamedQuery(name = "Clients.findByNic", query = "SELECT c FROM Clients c WHERE c.nic = :nic"),
    @NamedQuery(name = "Clients.findByNom", query = "SELECT c FROM Clients c WHERE c.nom = :nom"),
    @NamedQuery(name = "Clients.findByPrenom", query = "SELECT c FROM Clients c WHERE c.prenom = :prenom"),
    @NamedQuery(name = "Clients.findByRue", query = "SELECT c FROM Clients c WHERE c.rue = :rue"),
    @NamedQuery(name = "Clients.findByNumero", query = "SELECT c FROM Clients c WHERE c.numero = :numero"),
    @NamedQuery(name = "Clients.findByCp", query = "SELECT c FROM Clients c WHERE c.cp = :cp"),
    @NamedQuery(name = "Clients.findByCommune", query = "SELECT c FROM Clients c WHERE c.commune = :commune"),
    @NamedQuery(name = "Clients.findByTel", query = "SELECT c FROM Clients c WHERE c.tel = :tel"),
    @NamedQuery(name = "Clients.findByUsername", query = "SELECT c FROM Clients c WHERE c.username = :username"),
    @NamedQuery(name = "Clients.findByPasswordclient", query = "SELECT c FROM Clients c WHERE c.passwordclient = :passwordclient")
//    ,@NamedQuery(name = "Clients.findnameByadress", query = "SELECT c.prenom ,c.nom FROM Clients c WHERE c.cp = :cp")
})

 
    
public class Clients implements Serializable {

    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "NIC")
    private Integer nic;
    @Basic(optional = false)
    @Column(name = "Nom")
    private String nom;
    @Basic(optional = false)
    @Column(name = "Prenom")
    private String prenom;
    @Basic(optional = false)
    @Column(name = "Rue")
    private String rue;
    @Basic(optional = false)
    @Column(name = "Numero")
    private String numero;
    @Basic(optional = false)
    @Column(name = "CP")
    private String cp;
    @Basic(optional = false)
    @Column(name = "Commune")
    private String commune;
    @Basic(optional = false)
    @Column(name = "tel")
    private String tel;
    @Basic(optional = false)
    @Column(name = "username")
    private String username;
    @Basic(optional = false)
    @Column(name = "passwordclient")
    private String passwordclient;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clients")
    private Collection<Inscrit> inscritCollection;

    public Clients() {
    }

    public Clients(Integer nic) {
        this.nic = nic;
    }

    public Clients(Integer nic, String nom, String prenom, String rue, String numero, String cp, String commune, String tel, String username, String passwordclient) {
        this.nic = nic;
        this.nom = nom;
        this.prenom = prenom;
        this.rue = rue;
        this.numero = numero;
        this.cp = cp;
        this.commune = commune;
        this.tel = tel;
        this.username = username;
        this.passwordclient = passwordclient;
    }
    
   
    public Integer getNic() {
        return nic;
    }

    public void setNic(Integer nic) {
        this.nic = nic;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getCommune() {
        return commune;
    }

    public void setCommune(String commune) {
        this.commune = commune;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordclient() {
        return passwordclient;
    }

    public void setPasswordclient(String passwordclient) {
        this.passwordclient = passwordclient;
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
        hash += (nic != null ? nic.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Clients)) {
            return false;
        }
        Clients other = (Clients) object;
        if ((this.nic == null && other.nic != null) || (this.nic != null && !this.nic.equals(other.nic))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "isib.demo.crossfit.Tables.Clients[ nic=" + nic + " ]";
    }
    
   public List<Clients> findall(EntityManager em){
    
     Query query  = em.createNamedQuery("Clients.findAll", Clients.class);
    
     List<Clients> results = query.getResultList();
    return results;
    }
     
   public void findById(EntityManager em, int id){
   
         Clients c = em.find(Clients.class,id);
         System.out.println("===find by id =====");
         System.out.println(c.getNom());
   
    
    
    
    
    }

  
    
    
    
    
    
}
