/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isib.demo.crossfit.suivisession;

import lombok.Data;

/**
 *
 * @author aliou
 */
@Data
public class GetNotationParameter {
    private String dateCompetition;
    private String username;
    private String NomEpreuve;
    private String Nomjury;
    private Integer note;
    
    public GetNotationParameter(){
        this.dateCompetition="";
        this.username="";
        this.NomEpreuve="";
        this.Nomjury="";
        this.note=0;
    }
}
