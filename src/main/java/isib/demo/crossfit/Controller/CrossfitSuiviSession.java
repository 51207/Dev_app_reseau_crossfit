/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isib.demo.crossfit.Controller;

import isib.demo.crossfit.OtherClass.ListDesNotes;
import org.springframework.stereotype.Controller;
import isib.demo.crossfit.OtherClass.NotationClientInscrit;
import isib.demo.crossfit.OtherClass.StringMessage;
import isib.demo.crossfit.OtherClass.dateObject;
import isib.demo.crossfit.OtherClass.listNotationClientInscrit;
import isib.demo.crossfit.Tables.Clients;
import isib.demo.crossfit.Tables.Competition;
import isib.demo.crossfit.Tables.Epreuve;
import isib.demo.crossfit.Tables.Jury;
import isib.demo.crossfit.Tables.Test;
import isib.demo.crossfit.service.ClientService;
import isib.demo.crossfit.service.CompetitionService;
import isib.demo.crossfit.service.ComporteService;
import isib.demo.crossfit.service.EpreuveService;
import isib.demo.crossfit.service.InscritService;
import isib.demo.crossfit.service.JuryService;
import isib.demo.crossfit.service.testService;
import isib.demo.crossfit.suivisession.GetNotationParameter;
import isib.demo.crossfit.suivisession.Notation;
import isib.demo.crossfit.suivisession.NotationList;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 *
 * @author aliou
 */

@Controller
@SessionAttributes("notationList")
public class CrossfitSuiviSession {

    @Autowired
    private ClientService clientservice;

    @Autowired
    private InscritService inscritService;

    @Autowired
    private CompetitionService competitionService;

    @Autowired
    private ComporteService comporteService;

    @Autowired
    private testService testService;
    @Autowired
    private JuryService juryservice;
    @Autowired
    private EpreuveService epreuveservice;

    Integer positionOfNotationInList=0;
    
    
    //******Notation******
    @GetMapping("/DateNotation")
    public String getNotation(Model model) {
       try{
        model.addAttribute("dateNotation", new dateObject());

        return "DateNotation";
       }catch(NullPointerException e){
           return "redirect:login";
       }
    }

    //***page qui va permettre l'encodage de la note du client par l'oganisateur
    @GetMapping("/Notation")
    public String getNotationForClient(@RequestParam String StringVariable, Model model) {
        try {
            String date = StringVariable;
            List<String> listAllName = new ArrayList<>();
            List<Integer> list = inscritService.getAllInscritByDate(StringVariable);
            if (!list.isEmpty()) {

                for (var item : list) {

                    Clients s = clientservice.GetByNicId(item);
                    if (s != null) {
                        //je recupere les username car les username sont tous differents
                        //je considere qu'ils font leur competition avec leurs username
                        listAllName.add(s.getUsername());
                    }
                }

            }
            
            List<String> ListAlljury = juryservice.getAllJury();
            model.addAttribute("Tdates", date);
            model.addAttribute("listClientsInscrit", listAllName);
            model.addAttribute("listAlljury", ListAlljury);
            model.addAttribute("listAllEpreuve", epreuveservice.getalldataonEpreuve());
            model.addAttribute("EncodingAllvalue", new GetNotationParameter());

            return "Notation";

        } catch (NullPointerException e) {
            return "redirect:DateNotation";
        }
    }

    //*****on veut ajouter un element dans la liste qui concerne le suivi de session
    @PostMapping("/NotationClientAdd")
    public String AddNotation(@ModelAttribute GetNotationParameter notationparameter, Model model, HttpSession session, HttpServletRequest request,NotationList notationList) {
        //dans un premier temps on stocke tous les users de la listenotatlion.getNotations() dans une liste
        List<String> idOfInscrit = new ArrayList<String>();
        for(var it :notationList.getNotations() ){
            
            if(!idOfInscrit.contains(it.getNic().getUsername())){
              idOfInscrit.add(it.getNic().getUsername());
            }
        }
       
        try {
          int count = 0; 
          //on verifie le nombre de fois qu'on a noté sur une matière un participant ne depasse pas 2
          for(var item2 :idOfInscrit) {
             
             if(Objects.equals( notationparameter.getUsername(),item2)){
                  
                 for(var item : notationList.getNotations()){
                   
                     if(Objects.equals( item.getNic().getUsername(),item2) && Objects.equals(notationparameter.getNomEpreuve(),item.getNec().getNEpreuve()) ){
                     count++;
                     }
                 }
                
                
             }
          }
          
          //recherche du client ,de l'epreuve et du jury
            Optional<Clients> c = clientservice.ForgotPassword(notationparameter.getUsername());
            Epreuve e = epreuveservice.GetEpreuvebyNomEpreuve(notationparameter.getNomEpreuve());
            Jury j = juryservice.GetIDJurybyNomJury(notationparameter.getNomjury());
            
            //on verifie aussi le nombre de fois qu'on a noté ce partipant à cette matiere dans la BD
          count= count + CheckNumberOfEpreuve(c.get().getNic(),e.getNie(),notationparameter.getDateCompetition() );
          if(count <2){

            if (c.isPresent() && c.get() != null && e != null && j != null) {
                Notation notation = new Notation();
                
                notation.setDate(notationparameter.getDateCompetition());
                notation.setNic(c.get());
                notation.setNec(e);
                notation.setNIJury(j);
                notation.setNote(notationparameter.getNote());
                
               
               notation.setRang(++positionOfNotationInList);
               notationList.addNotation(notation);
                
           
                count=0;
                return "shownoteByEpreuveOrganisateur";
                
            }
          }
            return "PageAccueilOrganisateur";
        } catch (NullPointerException e) {
            return "redirect:login";
        }

    }
    
    public Integer CheckNumberOfEpreuve(Integer nclient,Integer idEpreuve,String date ){
        //renvoie le nombre de fois qu'on a noté ce participant à une epreuve  dans la base de données.
       Integer count = 0;
       count = testService.checkNumberOfEpreuve(nclient, idEpreuve, date);
        
        return count;
    }
    
    

    //***affichage du contenu du suivi de session
    @GetMapping("/shownoteByEpreuveOrganisateur")
    public String AffichageSuivideSession(Model model, HttpSession session) {
        
       try{ 
        model.addAttribute("notationList", (NotationList) session.getAttribute("notationList"));

        return "shownoteByEpreuveOrganisateur";
       }catch(NullPointerException e){
           return "redirect:login";
       }
    }
    
    
    
    //on veut supprimer la ligne qu'on a ajouté dans notationlist.getNotations() en appuyant sur delete
      @GetMapping("/deletestock/{rang}/delete")
    public String DeleteElementOfStockage(@PathVariable Integer rang,Model model,HttpSession session, NotationList notationlist ){
        try{
         notationlist = (NotationList) session.getAttribute("notationList");
         int i=0;
        for( Notation item : notationlist.getNotations()){
            
           if(Objects.equals(rang, item.getRang())){
               i = notationlist.getNotations().indexOf(item);
            
           }
        }
        notationlist.getNotations().remove(i);
      
        }catch(NullPointerException e){
       
        }finally{
        return "shownoteByEpreuveOrganisateur";
        }
    }

}
