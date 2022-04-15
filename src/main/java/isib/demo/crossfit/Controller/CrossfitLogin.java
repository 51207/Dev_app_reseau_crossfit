/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isib.demo.crossfit.Controller;

/**
 *
 * @author aliou
 */

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
import java.util.Objects;
import org.springframework.web.bind.annotation.DeleteMapping;

import org.springframework.stereotype.Controller;

import org.springframework.stereotype.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 *
 * @author aliou
 */
@Controller
@SessionAttributes({"loginusername"})
public class CrossfitLogin {
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
    
   public String nomuser = "";
   public String password = "";

    //*****login***
    @GetMapping("/login")
    public String login(Model model) {
       model.addAttribute("login", new Clients());
       
        return "login";
    } 
    
    @PostMapping("/login")
    public String logins(@ModelAttribute Clients client,Model model){
    
        Clients c = clientservice.GetLogin(client.getUsername(), client.getPasswordclient());
        
    if(c != null){
        nomuser= client.getUsername();
        Crossfitclass.nomuser=client.getUsername();
    return "PageAccueil";
    
    }else{
        return "redirect:login";
    }
    
    }
    
    @GetMapping("/personnel")
    public String DonneePersonnel() {

        return "DonneePersonnel";
    }
    
    
    
    //***** modification du mot de passe ******
    @GetMapping("/UpdatePassword")
    public String updatePass() {

        return "UpdatePassword";
    }

    
    
    @GetMapping("/Forgot")
    public String forgotPass(Model model) {
        //String name="";
        model.addAttribute("username", new StringMessage());
        
        return "ForgotPassword";
    }

    //***Post***
    @PostMapping("/Forgot")
    public String postforgot(@ModelAttribute StringMessage name, Model model ) {
        //@ModelAttribute("loginusername") String use
        //je cherche parmis les clients le client qui a comme username name.getNom()
        try {
            Optional<Clients> c = clientservice.ForgotPassword(name.getNom());
            nomuser = name.getNom();
            Crossfitclass.nomuser=name.getNom();
            if (c.isPresent()) {

                // model.addAttribute("users", c.get());
                model.addAttribute("users3", new StringMessage());

                return "UpdatePassword";
            } else {

                return "PageAccueil";
            }
        } catch (Exception e) {

            return "PageAccueil";
        }
    }

    //on modifie le mot de passe
    @GetMapping("/Forgot2")
  
    public String putforgot(@RequestParam StringMessage mdp) {
        //@ModelAttribute("loginusername") String use
        //public String putforgot(@ModelAttribute StringMessage password) {
        try {

            Optional<Clients> c = clientservice.ForgotPassword(nomuser);
           //!Objects.equals(s.getPasswordclient(),mdp.getNom(): je verifie si l'ancien mot de passe n'est pas le meme que le nouveau
           
           if (c.isPresent()) {
                Clients s;
                s = c.get();
                
               if(mdp != null && mdp.getNom() != null && mdp.getNom().length() >0 && !Objects.equals(s.getPasswordclient(),mdp.getNom())){
                s.setPasswordclient(mdp.getNom());
                clientservice.UpdateClients(s);
                 return "successupdatepassword";
               }else{
                   
                     return "redirect:login";
               } 
               
               
               
            } else {

                return "PageAccueil";
            }
        } catch (Exception e) {

            return "PageAccueil";
        }

    }

}
