/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isib.demo.crossfit.OtherClass;

import isib.demo.crossfit.Tables.Clients;
import isib.demo.crossfit.Tables.Competition;
import isib.demo.crossfit.Tables.Epreuve;
import isib.demo.crossfit.Tables.Jury;
import lombok.Data;

/**
 *
 * @author aliou
 */
@Data
public class AddEpreuveInCompetition {

    private Integer rang;
    private Competition compet;
    private Epreuve epreuve;
    private Integer idcompet;
    private Integer idepreuve;

    public AddEpreuveInCompetition() {
        
        
    }
}
