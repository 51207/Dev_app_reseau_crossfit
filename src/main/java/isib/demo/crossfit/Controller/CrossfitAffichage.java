/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isib.demo.crossfit.Controller;

/**
 *
 * @author aliou
 */import isib.demo.crossfit.OtherClass.Message;
import isib.demo.crossfit.OtherClass.StringMessage;
import isib.demo.crossfit.OtherClass.noteclass;
import isib.demo.crossfit.Tables.Clients;
import isib.demo.crossfit.Tables.Inscrit;
import isib.demo.crossfit.service.ClientService;
import isib.demo.crossfit.service.CompetitionService;
import isib.demo.crossfit.service.ComporteService;
import isib.demo.crossfit.service.testService;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import isib.demo.crossfit.Tables.Competition;
import isib.demo.crossfit.Tables.Comporte;
import isib.demo.crossfit.Tables.Epreuve;
import isib.demo.crossfit.Tables.Test;
import isib.demo.crossfit.Tables.InscritPK;
import isib.demo.crossfit.service.ClientService;
import isib.demo.crossfit.service.CompetitionService;
import isib.demo.crossfit.service.ComporteService;
import isib.demo.crossfit.service.EpreuveService;
import isib.demo.crossfit.service.InscritService;
import isib.demo.crossfit.service.testService;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.DeleteMapping;

/**
 *
 * @author aliou
 */
@Controller
public class CrossfitAffichage {
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
    
     //****** variable utiliser dans la conservateur du user et du password *******
    public String nomuser = "";
    public String password = "";
    
    
    @GetMapping("/Affichage")
    public String Affichage(Model model){
        model.addAttribute("output", new StringMessage());
        model.addAttribute("nameofcompet", epreuveService.getalldataonEpreuve()  ); 
       
    return "affichageResultat";
    }
    
    @GetMapping("/affichageresulnoteperso")
       public String Affichageresult(@RequestParam String nom, @RequestParam String prenom,Model model,HttpSession session){
           try{
           Optional<Clients> c = clientservice.ForgotPassword( session.getAttribute("loginusername").toString());
          
          List<noteclass> list =   testService.getAllNote(c.get().getNic(), prenom);
          if(list.isEmpty()){
          noteclass note = new noteclass();
          note.setNom("#"); note.setPrenom("#"); note.setNote1(0); note.setNote2(0);note.setNotetotal(0);
          list.add(note);
          }
          for(var item : list){
            System.out.println( item.getNom()+"    |    "+ item.getNomepreuve()+"    |    "+item.getNote1()+"     |   "+item.getNote2()+  "\n");
           
          }
          model.addAttribute("noteclass", list);
          nom=""; prenom=""; //mdp="";
           return "shownote";
           }catch(NullPointerException e){
               return "redirect:login";
           }
       }
    
       
        @GetMapping("/affichageresultallnote")
       public String Affichageallresultbyepreuve(@RequestParam String nom, @RequestParam String prenom,Model model,HttpSession session){
          
          try{
          Optional<Clients> c = clientservice.ForgotPassword( session.getAttribute("loginusername").toString());
          
          
          List<noteclass> list = testService.getAllNotebyepreuve(nom, prenom);
          if(list.isEmpty()){
          noteclass note = new noteclass();
          note.setNom("#"); note.setNomepreuve("#"); note.setNote1(0); note.setNote2(0);note.setNotetotal(0);
          list.add(note);
          }
          for(var item : list){
            System.out.println( item.getNom()+"    |    "+ item.getPrenom()+"    |    "+item.getNote1()+"     |   "+item.getNote2()+  "\n");
           
          }
          model.addAttribute("noteclass", list);
          nom=""; prenom=""; //mdp="";
           return "shownoteByEpreuve";
         
       }catch(NullPointerException e){
           
           return "redirect:login";
       }
       
       }
     
       
       
}
