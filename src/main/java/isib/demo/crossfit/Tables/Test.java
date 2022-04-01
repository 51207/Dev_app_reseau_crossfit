/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isib.demo.crossfit.Tables;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author aliou
 */
@Entity
@Table(name = "test")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Test.findAll", query = "SELECT t FROM Test t"),
    @NamedQuery(name = "Test.findByTDates", query = "SELECT t FROM Test t WHERE t.testPK.tDates = :tDates"),
    @NamedQuery(name = "Test.findByTnic", query = "SELECT t FROM Test t WHERE t.testPK.tnic = :tnic"),
    @NamedQuery(name = "Test.findByTJury", query = "SELECT t FROM Test t WHERE t.testPK.tJury = :tJury"),
    @NamedQuery(name = "Test.findByTnie", query = "SELECT t FROM Test t WHERE t.testPK.tnie = :tnie"),
    @NamedQuery(name = "Test.findByNote", query = "SELECT t FROM Test t WHERE t.note = :note")})
public class Test implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TestPK testPK;
    @Column(name = "note")
    private Integer note;

    public Test() {
    }

    public Test(TestPK testPK) {
        this.testPK = testPK;
    }

    public Test(String tDates, int tnic, int tJury, int tnie) {
        this.testPK = new TestPK(tDates, tnic, tJury, tnie);
    }

    public TestPK getTestPK() {
        return testPK;
    }

    public void setTestPK(TestPK testPK) {
        this.testPK = testPK;
    }

    public Integer getNote() {
        return note;
    }

    public void setNote(Integer note) {
        this.note = note;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (testPK != null ? testPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Test)) {
            return false;
        }
        Test other = (Test) object;
        if ((this.testPK == null && other.testPK != null) || (this.testPK != null && !this.testPK.equals(other.testPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "isib.demo.crossfit.Tables.Test[ testPK=" + testPK + " ]";
    }
    
}
