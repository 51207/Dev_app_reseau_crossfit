/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isib.demo.crossfit.OtherClass;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lombok.Data;

/**
 *
 * @author aliou
 */
@Data
public class NotationClientInscrit {

    private String nom;
    private String Prenom;
    private String username;
    private String NomEpreuve;
    private String Nomjury;
    private String dateCompetition;
    private int PremiereNote;
    private int SecondeNote;
    private int moyenne;
    
    private int IdNomEpreuve;
    private int IdJury;
    private int idclient;

  
    public NotationClientInscrit() {
        this.nom="";
        this.Prenom="";
        this.Nomjury="";
        this.PremiereNote=0;
        this.SecondeNote=0;
        this.moyenne=0;
        this.idclient=0;
        this.IdNomEpreuve=0;
        this.IdJury=0;
    }

    public void setMoyenne() {
        this.moyenne = (int) ((this.PremiereNote + this.SecondeNote) / 2);
    }

   

 
}
