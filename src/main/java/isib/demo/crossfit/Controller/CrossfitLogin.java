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
import isib.demo.crossfit.Tables.Clients;
import isib.demo.crossfit.Tables.Competition;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import isib.demo.crossfit.service.ClientService;
import isib.demo.crossfit.service.CompetitionService;
import isib.demo.crossfit.service.ComporteService;
import isib.demo.crossfit.service.EpreuveService;
import isib.demo.crossfit.service.InscritService;
import isib.demo.crossfit.service.testService;
import isib.demo.crossfit.suivisession.NotationList;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;

/**
 *
 * @author aliou
 */
@Controller

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
    public boolean errorLogin = false;

    //*****login***
    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("login", new Clients());

        if (errorLogin == true) {
            model.addAttribute("errorLogin", "le nom d'utilisateur ou le mot de passe est erroné");
            
            this.errorLogin = false;
        } else {
            model.addAttribute("errorLogin", " ");
           
        }
        return "login";
    }

    @PostMapping("/login")
    public String logins(@ModelAttribute Clients client, Model model, HttpSession session, HttpServletRequest request) {
        //on verifie si c'est client ou si c'est une compétition ou si c'est un orgranisateur
       
        Clients c = clientservice.GetLogin(client.getUsername(), client.getPasswordclient());
        Competition comp = competitionService.GetLogin(client.getUsername(), client.getPasswordclient());
       
        
      
        if (c != null) {

            session = request.getSession();
            session.setAttribute("loginusername", client.getUsername());


            return "PageAccueil";

        } else if (comp != null) {

            session = request.getSession();
            session.setAttribute("loginusername", comp.getUser());
            session.setAttribute("notationList", new NotationList());

            return "PageAccueilOrganisateur";

        } else {

            this.errorLogin = true;

            return "redirect:login";
        }
        
        
    }

    @GetMapping("/deconnexion")
    public void Deconnexion(HttpSession session) {

        System.out.println("=>" + session.getAttribute("loginusername"));

        session.invalidate();

        // return "Deconnexion";
    }

    @GetMapping("/personnel")
    public String DonneePersonnel(HttpSession session) {

        try {
              //on verifie si on est bien logger en tant qu'utilisateur
            Optional<Clients> c = clientservice.ForgotPassword(session.getAttribute("loginusername").toString());
            
            return "DonneePersonnel";
        } catch (NullPointerException e) {
            return "redirect:login";
        }
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

    public String postforgot(@ModelAttribute StringMessage name, Model model) {

        //je cherche parmis les clients le client qui a comme username name.getNom()
        try {
            Optional<Clients> c = clientservice.ForgotPassword(name.getNom());

          
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
    public String putforgot(@RequestParam StringMessage mdp, HttpSession session) {

        try {
            //là aussi on verifie si le user est un client ou un organisateur
            Optional<Clients> c = clientservice.ForgotPassword(session.getAttribute("loginusername").toString());
            Optional<Competition> comp = competitionService.ForgotPassword(session.getAttribute("loginusername").toString());

            if (c.isPresent() && c.get() != null) {
                Clients s;
                s = c.get();

                if (mdp != null && mdp.getNom() != null && mdp.getNom().length() > 0 && !Objects.equals(s.getPasswordclient(), mdp.getNom())) {
                    s.setPasswordclient(mdp.getNom());
                    clientservice.UpdateClients(s);
                    return "successupdatepassword";
                } else {

                    return "redirect:login";
                }

            } else if (comp.isPresent() && comp.get() != null) {
                Competition competition;
                competition = comp.get();
                if (mdp != null && mdp.getNom() != null && mdp.getNom().length() > 0 && !Objects.equals(competition.getPassword(), mdp.getNom())) {
                    competition.setPassword(mdp.getNom());
                    competitionService.UpdateCompetition(competition);
                    return "successupdatepasswordOrganisateur";
                }

            } else {

                return "PageAccueil";
            }
        } catch (Exception e) {

            return "PageAccueil";
        }
        return "PageAccueil";
    }
    
   

}
