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
public class listNotationClientInscrit {

    private final List<NotationClientInscrit> products;
    private final List<NotationClientInscrit> listNote;

    public listNotationClientInscrit() {
        this.products = new ArrayList();
        this.listNote = new ArrayList();
    }

    public void setListNote(NotationClientInscrit notationClientInscrit){
        // this.listNote.add(notationClientInscrit);
         
         if (!(this.listNote.isEmpty())) {

            for (int i = ((this.listNote.size()) - 1); i >= 0; i--) {
                //on veut savoir s'il l'element notationClientInscrit qu'on veut ajouter n'a pas le meme id, la meme matiere et la meme date qu'un element dans la liste this.products
                if (Objects.equals(notationClientInscrit.getNomEpreuve(), this.listNote.get(i).getNomEpreuve()) && Objects.equals(notationClientInscrit.getNomjury(), this.listNote.get(i).getNomjury()) && Objects.equals(notationClientInscrit.getDateCompetition(), this.listNote.get(i).getDateCompetition()) ) {
                    //si c'est le cas , on modifiera juste la seconde note et la moyenne de l'element qui se trouve dans la liste
                    break;
                }else{
                    this.listNote.add(notationClientInscrit);
                    break;
                }

            }
        } else {
            this.listNote.add(notationClientInscrit);
        }
    }
    
    public void ClearListNote(){
         this.listNote.clear();
    }
    
    public void setProduct(NotationClientInscrit notationClientInscrit) {
        //on check pour savoir s'il ce même notationClientInscrit n'existe pas dans test
     
       
      //  this.listNote.add(notationClientInscrit);
        if (!(this.products.isEmpty())) {
            int counter = 0;
            for (int i = ((this.products.size()) - 1); i >= 0; i--) {
                //on veut savoir s'il l'element notationClientInscrit qu'on veut ajouter n'a pas le meme id, la meme matiere et la meme date qu'un element dans la liste this.products
                if (Objects.equals(notationClientInscrit.getUsername(), this.products.get(i).getUsername()) && Objects.equals(notationClientInscrit.getNomEpreuve(), this.products.get(i).getNomEpreuve()) && Objects.equals(notationClientInscrit.getDateCompetition(), this.products.get(i).getDateCompetition())) {
                    //si c'est le cas , on modifiera juste la seconde note et la moyenne de l'element qui se trouve dans la liste
                    this.products.get(i).setSecondeNote(notationClientInscrit.getPremiereNote());
                    this.products.get(i).setMoyenne();
                    counter++;
                    break;
                }//else{
                  //  this.products.add(notationClientInscrit);
                 //   break;
                //}

            }
            //le counter permet de savoir s'il existe le meme element dans la liste , si le counter est egale à 0 alors on peut ajouter
            if(counter ==0){
            
              this.products.add(notationClientInscrit);
            }
        } else {
            this.products.add(notationClientInscrit);
        }
    }
    
    
    public void ClearProducts(){
    this.products.clear();
    }

    //ona tous les username classé dans cette methode
    public List<String> Getallusername() {
        List<String> list = new ArrayList();
        //je veux recuperer tous les user encodé
        List<NotationClientInscrit> anotherlist;
        anotherlist = this.products;
        if (!anotherlist.isEmpty()) {
            for (var item : this.products) {
                // si la liste ne contient pas se username
                if (!(list.contains(item.getUsername()))) {
                    list.add(item.getUsername());
                }

            }
        }
        return list;
    }

    //on a tous les epreuve classés dans cette methode
    public List<String> GetallEpreuveName() {
        List<String> list = new ArrayList();
        //je veux recuperer tous les epreuve encodé
        List<NotationClientInscrit> anotherlist;
        anotherlist = this.products;
        if (!(anotherlist.isEmpty())) {
            for (var item : anotherlist) {
                // si la liste ne contient pas se username
                if (!(list.contains(item.getNomEpreuve()))) {
                    list.add(item.getNomEpreuve());
                }

            }
        }
        return list;
    }

    //cette methode recupere dans une list<arraylist>=> cad une double liste
    //chaque arraylist aura  : username,noteEpreuve1, noteEpreuve2, noteEpreuve3 ........,noteEpreuveN,moyennetotal
    public List<ArrayList> GetAllNote() {

        List<ArrayList> listdesclients = new ArrayList();

        try {
            for (var item1 : Getallusername()) {

                ArrayList allnote = new ArrayList();
                //dans allnote , on aura le username, ces notes en fonctions de l'epreuve, et le dernier element de la liste sera la moyenne total dans toute ces epreuves
                allnote.add(item1);
                List<String> GeallNameEpreuve = GetallEpreuveName();
                for (var item2 : GeallNameEpreuve) {

                    //en fonction du username et de l'epreuve ,on recherche dans la liste product, à quelle NotationClientInscrit il correspond
                    //ensuite on ajoute la moyenne des notes données par les memebres du jury qui sont 2 par epreuve dans l'arraylist allnote.
                    for (var item3 : this.products) {

                        if (Objects.equals(item3.getUsername(), item1) && Objects.equals(item3.getNomEpreuve(), item2)) {

                            allnote.add(item3.getMoyenne());
                            break;
                        }
                    }
                }
                 //on additionne toute les moyenne de chaque d'epreuve
                int count = 0;
                for (int i = 1; i < allnote.size(); i++) {

                    count += (int) allnote.get(i);

                }
                //-2 car on retire le premier element car c'est le username
                
                int totalcount = (count / (allnote.size() - 1));
                //la derniere valeur est la moyenne total de toutes les epreuves
               
                allnote.add(totalcount);

                listdesclients.add(allnote);

            }
        } catch (NullPointerException e) {
            return null;
        }
        return listdesclients;
    }

    public void echangePlace(List<ArrayList> list, int i, int j) {
        ArrayList tmp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, tmp);
    }

    //Trie par selection en ordre decroissant
    public List<ArrayList> SortAllNote(List<ArrayList> allnote) {

        int longueur = (allnote.size() );

        for (int i = 0; i < longueur - 1; i++) {
            //
            int min = i;

            for (int j = i + 1; j < longueur; j++) {
                //note est la moyenne total de ce mec
                int note = (int) allnote.get(j).get((allnote.get(j).size() - 1));
                int noteprecedent = (int) allnote.get(min).get((allnote.get(min).size() - 1));
                //par ordre croissant, le signe serait < , mais par ordre decroissant le signe serait >
                if (note > noteprecedent) {

                    min = j;

                }

            }
            this.echangePlace(allnote, min, i);
        }
        
        //mettre le rang de la personne en premier dans la lite 
        for( int i =0 ; i< allnote.size(); i++){
        
            allnote.get(i).add(0, (i+1));
        }
        
        return allnote;
    }

}
