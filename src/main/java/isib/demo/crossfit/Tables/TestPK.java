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
public class TestPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "TDates")
    private String tDates;
    @Basic(optional = false)
    @Column(name = "TNIC")
    private int tnic;
    @Basic(optional = false)
    @Column(name = "TJury")
    private int tJury;
    @Basic(optional = false)
    @Column(name = "TNIE")
    private int tnie;

    public TestPK() {
    }

    public TestPK(String tDates, int tnic, int tJury, int tnie) {
        this.tDates = tDates;
        this.tnic = tnic;
        this.tJury = tJury;
        this.tnie = tnie;
    }

    public String getTDates() {
        return tDates;
    }

    public void setTDates(String tDates) {
        this.tDates = tDates;
    }

    public int getTnic() {
        return tnic;
    }

    public void setTnic(int tnic) {
        this.tnic = tnic;
    }

    public int getTJury() {
        return tJury;
    }

    public void setTJury(int tJury) {
        this.tJury = tJury;
    }

    public int getTnie() {
        return tnie;
    }

    public void setTnie(int tnie) {
        this.tnie = tnie;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tDates != null ? tDates.hashCode() : 0);
        hash += (int) tnic;
        hash += (int) tJury;
        hash += (int) tnie;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TestPK)) {
            return false;
        }
        TestPK other = (TestPK) object;
        if ((this.tDates == null && other.tDates != null) || (this.tDates != null && !this.tDates.equals(other.tDates))) {
            return false;
        }
        if (this.tnic != other.tnic) {
            return false;
        }
        if (this.tJury != other.tJury) {
            return false;
        }
        if (this.tnie != other.tnie) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "isib.demo.crossfit.Tables.TestPK[ tDates=" + tDates + ", tnic=" + tnic + ", tJury=" + tJury + ", tnie=" + tnie + " ]";
    }
    
}
