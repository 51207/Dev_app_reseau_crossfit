/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isib.demo.crossfit.Controller;

import isib.demo.crossfit.OtherClass.AddEpreuveInCompetition;
import isib.demo.crossfit.OtherClass.AddEpreuveInCompetitionList;
import isib.demo.crossfit.OtherClass.NotationClientInscrit;
import isib.demo.crossfit.OtherClass.StringMessage;
import isib.demo.crossfit.OtherClass.StringParameter;
import isib.demo.crossfit.OtherClass.listNotationClientInscrit;
import isib.demo.crossfit.Tables.Clients;
import isib.demo.crossfit.Tables.Competition;
import isib.demo.crossfit.Tables.Comporte;
import isib.demo.crossfit.Tables.ComportePK;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 *
 * @author aliou
 */
@Controller
@SessionAttributes("addEpreuveList")
public class CrossfitLoginOrganisateur {
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
    private EpreuveService epreuveService;
    
      @Autowired
    private JuryService juryservice;
     
    public boolean errorLogin =false;
    
    public Integer rang=0;
    
    //login de l'organisateur
     @GetMapping("/PageAccueilOrganisateur")
    public String login(Model model,HttpSession session) {
        
              
        return "PageAccueilOrganisateur";
    }
    
     //*****Créer un tournoi ************
    @GetMapping("/Create")
    public String Create(Model model,HttpSession session) {
        try{
         //là  on verifie si le user est un client ou un organisateur (si ce n'est pas le cas , on le redirige vers la page de login
          Optional<Competition> c =  competitionService.ForgotPassword(session.getAttribute("loginusername").toString());  
            
        model.addAttribute("tournoi", new Competition());
        return "CreationTournoi";
        }catch(NullPointerException e){
            return "redirect:login";
        }
    }

    @PostMapping("/InscriptCreation")
    public String postIncript(@ModelAttribute Competition competition) {
        //on check si c'est un client 
        if (clientservice.ForgotPassword(competition.getUser()) == null) {
            //si c'est un organisateur , alors il peut créer une competiton
            competitionService.CreateCompetition(competition);
            return "success";
        } else {

            return "redirect:Create";
        }

    }
    
    @GetMapping("/AddEpreuveInCompetition")
    public String AddEpreuveInCompetition(Model model,HttpSession session) {
         try {
            //on verifie si on est bien logger en tant qu'utilisateur
            Optional<Clients> c = clientservice.ForgotPassword(session.getAttribute("loginusername").toString());

            model.addAttribute("compet", competitionService.GetAllNameofCompetition().get());
            model.addAttribute("nameofepreuve", epreuveService.getalldataonEpreuve());
            model.addAttribute("output", new StringParameter());
            return "AddEpreuveInCompetition";
        } catch (NullPointerException e) {

            return "redirect:login";
        }
    }
    
    @GetMapping("/AddEpreuveInCompetitions")
    public String AddInLIST(@ModelAttribute StringParameter valueParameter,Model model,HttpSession session, AddEpreuveInCompetitionList addEpreuveList) {
         try {
            //on verifie si on est bien logger en tant qu'utilisateur
            Optional<Clients> d = clientservice.ForgotPassword(session.getAttribute("loginusername").toString());
             //AddEpreuveInCompetitionList addEpreuveList = new AddEpreuveInCompetitionList();
               
             AddEpreuveInCompetition epreuveIncompetition = new AddEpreuveInCompetition();
            
             
             String nomcompetition = valueParameter.getStringVar2();
             String nomEpreuve= valueParameter.getStringVar1();
            Competition competition = competitionService.ForgotNameOfCompetition(nomcompetition);
            Epreuve epreuve = epreuveService.GetEpreuvebyNomEpreuve(nomEpreuve);
            
            if( competition != null && epreuve != null ){
            
                 Comporte c=  comporteService.GetComporteByIDCompetition(competition.getNCompetition(),epreuve.getNie());
                 if( c == null ){
                     
                     addEpreuveList=(AddEpreuveInCompetitionList) session.getAttribute("addEpreuveList");
                     epreuveIncompetition.setCompet(competition);
                     epreuveIncompetition.setEpreuve(epreuve);
                     epreuveIncompetition.setIdcompet(competition.getNCompetition());
                     epreuveIncompetition.setIdepreuve(epreuve.getNie());
                     epreuveIncompetition.setRang(++rang);
                     addEpreuveList.addNotation(epreuveIncompetition);
                     
                    // session.setAttribute("addEpreuveList", addEpreuveList);
                    // model.addAttribute("addEpreuveList", addEpreuveList);
                     return "showAddEpreuveToCompetition";
                }
                
            }
            return "PageAccueilOrganisateur";
          
            
            
        } catch (NullPointerException e) {

            return "redirect:PageAccueilOrganisateur";
        }
    }
    
    
    //***affichage du contenu du suivi de session
    @GetMapping("/showAddEpreuveToCompetition")
    public String AffichageSuivideSession(Model model, HttpSession session) {
        
       try{ 
        model.addAttribute("addEpreuveList", (AddEpreuveInCompetitionList) session.getAttribute("addEpreuveList"));

        return "showAddEpreuveToCompetition";
       }catch(NullPointerException e){
           return "redirect:PageAccueilOrganisateur";
       }
    }
    
      @GetMapping("/deleteAddEpreuveCompetition/{rang}/delete")
    public String DeleteEpreuveOfBDInCompetition(@PathVariable Integer rang,Model model,HttpSession session,AddEpreuveInCompetitionList addEpreuveList ){
        int i=0;
        try{
         addEpreuveList = (AddEpreuveInCompetitionList) session.getAttribute("addEpreuveList");
         
        for( AddEpreuveInCompetition item : addEpreuveList.getListepreuve() ){
            
           if(Objects.equals(rang, item.getRang())){
               i = addEpreuveList.getListepreuve().indexOf(item);
               comporteService.DeleteSingleEpreuveByCompetition(item.getCompet().getNCompetition(), item.getEpreuve().getNie());
           }
        }
        
       
        // session.setAttribute("addEpreuveList", addEpreuveList);
        
      
        }catch(NullPointerException e){
            //return "shownoteByEpreuveOrganisateur";
        }finally{
             addEpreuveList.getListepreuve().remove(i);
        return "showAddEpreuveToCompetition";
        }
    } 
    
    @PostMapping("/insertoBDallEpreuve")
    public String insertoBDallEpreuve(Model model,HttpSession session,AddEpreuveInCompetitionList addEpreuveList ){
        int i=0;
        try{
         addEpreuveList = (AddEpreuveInCompetitionList) session.getAttribute("addEpreuveList");
         
        for( AddEpreuveInCompetition item : addEpreuveList.getListepreuve() ){
            
          
             Comporte c  = new Comporte();
             c.setCompetition(item.getCompet());
             c.setEpreuve(item.getEpreuve());
             ComportePK p = new ComportePK();
             p.setCNCompetition(item.getIdcompet());
             p.setCNIE(item.getIdepreuve());
             c.setComportePK(p);
             comporteService.CreateComporte(c);
             
      
        }
            addEpreuveList.getListepreuve().clear();
          return "successAddEpreuveToCompetition";
        }catch(NullPointerException e ){
        
            return "PageAccueilOrganisateur";
        }
    
    }
    
   
    
   
    
    //********modification du client*********
    @GetMapping("/PersonnelOrganisateur")
    public String UpdateOrganisateur( Model model,HttpSession session) {


        try {
            //là  on verifie si le user est un client ou un organisateur (si ce n'est pas le cas , on le redirige vers la page de login
            String username = (String)session.getAttribute("loginusername");
             Optional<Competition> c =  competitionService.ForgotPassword(username);  
            
            
            
            model.addAttribute("userOrganisation", c.get());
            if (c.isPresent()) {

                return "UpdateOrganisateur";
            } else {

                return "PageAccueilOrganisateur";
            }
        } catch (NullPointerException e) {

            return "redirect:login";
        }

    }

    // update client post
    @PostMapping("/MAJOrganisateur")
    public String postUpdateInscrit(@ModelAttribute Competition newclient, HttpSession session) {

        try {

             //là  on verifie si le user est un client ou un organisateur (si ce n'est pas le cas , on le redirige vers la page de login
            Optional<Competition> c = competitionService.ForgotPassword(session.getAttribute("loginusername").toString());
 
            
            if (c.isPresent() && c.get() != null) {
                Competition s;
                s = c.get();

                s.setNCompetition(c.get().getNCompetition());
                s.setNomOrganisateur(newclient.getNomOrganisateur());
                s.setPrenomOrganisateur(newclient.getPrenomOrganisateur());
                s.setUser(newclient.getUser());
                s.setPassword(newclient.getPassword());

                competitionService.UpdateCompetitions(s);
                return "successupdatepasswordOrganisateur";
            } else {
                return "PageAccueilOrganisateur";
            }
        } catch (Exception e) {
            return "redirect:login";
        }

    }
    
    
     
    

}
