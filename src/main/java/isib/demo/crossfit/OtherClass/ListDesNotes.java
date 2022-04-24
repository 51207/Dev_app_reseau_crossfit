/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isib.demo.crossfit.OtherClass;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lombok.Data;
import org.springframework.stereotype.Component;


/**
 *
 * @author aliou
*/

@Data
public class ListDesNotes {
     private String nom;
    private String Prenom;
    private String username;
    private String NomEpreuve;
    private String Nomjury;
    private String dateCompetition;
    private int PremiereNote;
     private int IdNomEpreuve;
    private int IdJury;
    private int idclient;
    
   private List<NotationClientInscrit> listNote;
   

    public ListDesNotes() {
         this.nom="";
        this.Prenom="";
        this.Nomjury="";
        this.PremiereNote=0;
     
        this.idclient=0;
        this.IdNomEpreuve=0;
        this.IdJury=0;
       this.listNote = new ArrayList();
       
    }
    public void ClearListDesNotes(){
        this.listNote.clear();
    }
  public List<NotationClientInscrit> getListNote(){
  
      return this.listNote;
  }

  
    
    
    
     public void setListNote(NotationClientInscrit notationClientInscrit){
        // this.listNote.add(notationClientInscrit);
         
         if (!(this.listNote.isEmpty())) {
               int counter = 0;
            for (int i = ((this.listNote.size()) - 1); i >= 0; i--) {
                //on veut savoir s'il l'element notationClientInscrit qu'on veut ajouter n'a pas le meme id, la meme matiere et la meme date qu'un element dans la liste this.products
                if (Objects.equals(notationClientInscrit.getNomEpreuve(), this.listNote.get(i).getNomEpreuve()) && Objects.equals(notationClientInscrit.getNomjury(), this.listNote.get(i).getNomjury()) && Objects.equals(notationClientInscrit.getDateCompetition(), this.listNote.get(i).getDateCompetition()) ) {
                    //si c'est le cas , on modifiera juste la seconde note et la moyenne de l'element qui se trouve dans la liste
                    counter++;
                    break;
                }
            }
            
              if(counter ==0){
              //this.getListNote().add(notationClientInscrit);
              this.listNote.add(notationClientInscrit);
            }
        } else {
            this.listNote.add(notationClientInscrit);
           // this.getListNote().add(notationClientInscrit);
        }
    }

}
