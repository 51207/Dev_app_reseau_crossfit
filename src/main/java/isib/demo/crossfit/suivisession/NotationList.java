/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isib.demo.crossfit.suivisession;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

/**
 *
 * @author aliou
 */
@Data
public class NotationList {
    private List<Notation>  notations;
     
    public NotationList(){notations= new ArrayList();}
    
    public void addNotation(Notation notation){
    
        this.notations.add(notation);
    }

}
