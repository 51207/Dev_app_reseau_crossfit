/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isib.demo.crossfit.OtherClass;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

/**
 *
 * @author aliou
 */
@Data
public class AddEpreuveInCompetitionList {
     private List<AddEpreuveInCompetition>  listepreuve;
     
    public AddEpreuveInCompetitionList(){
        listepreuve= new ArrayList();}
    
    public void addNotation(AddEpreuveInCompetition addEpreuveInCompetition){
    
        this.listepreuve.add(addEpreuveInCompetition);
    }
}
