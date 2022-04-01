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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author aliou
 */
@Entity
@Table(name = "jury")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Jury.findAll", query = "SELECT j FROM Jury j"),
    @NamedQuery(name = "Jury.findByNIJury", query = "SELECT j FROM Jury j WHERE j.nIJury = :nIJury"),
    @NamedQuery(name = "Jury.findByNomJury", query = "SELECT j FROM Jury j WHERE j.nomJury = :nomJury"),
    @NamedQuery(name = "Jury.findByPrenomJury", query = "SELECT j FROM Jury j WHERE j.prenomJury = :prenomJury"),
    @NamedQuery(name = "Jury.findByTel", query = "SELECT j FROM Jury j WHERE j.tel = :tel"),
    @NamedQuery(name = "Jury.findByUserJury", query = "SELECT j FROM Jury j WHERE j.userJury = :userJury"),
    @NamedQuery(name = "Jury.findByPasswordJury", query = "SELECT j FROM Jury j WHERE j.passwordJury = :passwordJury")})
public class Jury implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "NIJury")
    private Integer nIJury;
    @Basic(optional = false)
    @Column(name = "Nom_Jury")
    private String nomJury;
    @Basic(optional = false)
    @Column(name = "Prenom_Jury")
    private String prenomJury;
    @Basic(optional = false)
    @Column(name = "tel")
    private String tel;
    @Basic(optional = false)
    @Column(name = "user_jury")
    private String userJury;
    @Basic(optional = false)
    @Column(name = "password_jury")
    private String passwordJury;

    public Jury() {
    }

    public Jury(Integer nIJury) {
        this.nIJury = nIJury;
    }

    public Jury(Integer nIJury, String nomJury, String prenomJury, String tel, String userJury, String passwordJury) {
        this.nIJury = nIJury;
        this.nomJury = nomJury;
        this.prenomJury = prenomJury;
        this.tel = tel;
        this.userJury = userJury;
        this.passwordJury = passwordJury;
    }

    public Integer getNIJury() {
        return nIJury;
    }

    public void setNIJury(Integer nIJury) {
        this.nIJury = nIJury;
    }

    public String getNomJury() {
        return nomJury;
    }

    public void setNomJury(String nomJury) {
        this.nomJury = nomJury;
    }

    public String getPrenomJury() {
        return prenomJury;
    }

    public void setPrenomJury(String prenomJury) {
        this.prenomJury = prenomJury;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getUserJury() {
        return userJury;
    }

    public void setUserJury(String userJury) {
        this.userJury = userJury;
    }

    public String getPasswordJury() {
        return passwordJury;
    }

    public void setPasswordJury(String passwordJury) {
        this.passwordJury = passwordJury;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nIJury != null ? nIJury.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Jury)) {
            return false;
        }
        Jury other = (Jury) object;
        if ((this.nIJury == null && other.nIJury != null) || (this.nIJury != null && !this.nIJury.equals(other.nIJury))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "isib.demo.crossfit.Tables.Jury[ nIJury=" + nIJury + " ]";
    }
    
}
