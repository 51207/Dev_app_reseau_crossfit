/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isib.demo.crossfit.Controller;

import isib.demo.crossfit.OtherClass.NotationClientInscrit;
import isib.demo.crossfit.OtherClass.StringMessage;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author aliou
 */
@Controller
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
    
    //login de l'organisateur
     @GetMapping("/PageAccueilOrganisateur")
    public String login(Model model) {
       
        return "PageAccueilOrganisateur";
    }
    
     //*****Créer un tournoi ************
    @GetMapping("/Create")
    public String Create(Model model) {
        model.addAttribute("tournoi", new Competition());
        return "CreationTournoi";
    }

    @PostMapping("/InscriptCreation")
    public String postIncript(@ModelAttribute Competition competition) {

        if (clientservice.ForgotPassword(competition.getUser()) == null) {
         
            competitionService.CreateCompetition(competition);
            return "success";
        } else {

            return "redirect:Create";
        }

    }
    
    
    
    
   
    
    //********modification du client*********
    @GetMapping("/PersonnelOrganisateur")
    public String UpdateOrganisateur( Model model,HttpSession session) {


        try {
            // Optional<Clients> c = clientservice.ForgotPassword(nomuser);
            Optional<Competition> c = competitionService.ForgotPassword(session.getAttribute("loginusername").toString());

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

            // Optional<Clients> c = clientservice.ForgotPassword(nomuser);
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
            return "redirect:PageAccueilOrganisateur";
        }

    }

}
