/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isib.demo.crossfit.Tables;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author aliou
 */
@Embeddable
public class InscritPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "I_NCompetition")
    private int iNCompetition;
    @Basic(optional = false)
    @Column(name = "I_NIC")
    private int iNic;
    @Basic(optional = false)
    @Column(name = "I_date_")
    private String idate;

    public InscritPK() {
    }

    public InscritPK(int iNCompetition, int iNic, String idate) {
        this.iNCompetition = iNCompetition;
        this.iNic = iNic;
        this.idate = idate;
    }

    public int getINCompetition() {
        return iNCompetition;
    }

    public void setINCompetition(int iNCompetition) {
        this.iNCompetition = iNCompetition;
    }

    public int getINic() {
        return iNic;
    }

    public void setINic(int iNic) {
        this.iNic = iNic;
    }

    public String getIdate() {
        return idate;
    }

    public void setIdate(String idate) {
        this.idate = idate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) iNCompetition;
        hash += (int) iNic;
        hash += (idate != null ? idate.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InscritPK)) {
            return false;
        }
        InscritPK other = (InscritPK) object;
        if (this.iNCompetition != other.iNCompetition) {
            return false;
        }
        if (this.iNic != other.iNic) {
            return false;
        }
        if ((this.idate == null && other.idate != null) || (this.idate != null && !this.idate.equals(other.idate))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "isib.demo.crossfit.Tables.InscritPK[ iNCompetition=" + iNCompetition + ", iNic=" + iNic + ", idate=" + idate + " ]";
    }
    
}
