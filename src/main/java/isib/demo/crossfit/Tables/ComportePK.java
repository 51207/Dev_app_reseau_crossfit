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
public class ComportePK implements Serializable {

    @Basic(optional = false)
    @Column(name = "cncompetition")
    private int CNCompetition;
    @Basic(optional = false)
    @Column(name = "cnie")
    private int CNIE;
    

    public ComportePK() {
    }

    public ComportePK(int CNCompetition, int CNIE) {
        this.CNCompetition = CNCompetition;
        this.CNIE = CNIE;
       
    }

    public int getCNCompetition() {
        return CNCompetition;
    }

    public void setCNCompetition(int CNCompetition) {
        this.CNCompetition = CNCompetition;
    }

    public int getCNIE() {
        return CNIE;
    }

    public void setCNIE(int CNIE) {
        this.CNIE = CNIE;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + this.CNCompetition;
        hash = 79 * hash + this.CNIE;
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
        final ComportePK other = (ComportePK) obj;
        if (this.CNCompetition != other.CNCompetition) {
            return false;
        }
        return this.CNIE == other.CNIE;
    }

    @Override
    public String toString() {
        return "ComportePK{" + "CNCompetition=" + CNCompetition + ", CNIE=" + CNIE + '}';
    }

  
    
}
