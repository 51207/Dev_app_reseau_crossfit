/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package isib.demo.crossfit.Controller;

import isib.demo.crossfit.OtherClass.Message;
import isib.demo.crossfit.OtherClass.StringMessage;
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
import isib.demo.crossfit.service.InscritService;
import isib.demo.crossfit.service.testService;
import org.springframework.web.bind.annotation.DeleteMapping;

/**
 *
 * @author aliou
 */
@Controller
public class Crossfitclass {

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

    //****** variable utiliser dans la conservateur du user et du password *******
    public String nomuser = "";
    public String password = "";

  

    //*****Page d'Accueil******
    @GetMapping("/**")
    public String page(Model model) {

        return "PageAccueil";
    }

    @GetMapping("/PageAccueil")
    public String pageAccueil(Model model) {

        return "PageAccueil";
    }

    //*****Créer un tournoi ************
    @GetMapping("/Create")
    public String Create(Model model) {
        model.addAttribute("tournoi", new Competition());
        return "CreationTournoi";
    }

    @PostMapping("/InscriptCreation")
    public String postIncript(@ModelAttribute Competition competition) {
        competitionService.CreateCompetition(competition);

        return "successCreation";
    }

    //******Inscription au sport******
    @GetMapping("/Inscription")
    public String Inscrition(Model model) {

        model.addAttribute("inscription", new Clients());
        return "Inscription";
    }

    @PostMapping("/Inscript")
    public String postInscription(@ModelAttribute Clients newclient,Model model) {
            //on verifie pour savoir si ce username existe,sion on peut dès lors crée 
        if (clientservice.ForgotPassword(newclient.getUsername()) == null) {
            clientservice.CreateClients(newclient);
            return "success";
        } else {
          
            return "redirect:Inscription";
        }
    }

    //****** Inscription au tournoi ********
    @GetMapping("/Tournoi")
    public String InscritTournoi(Model model) {
        model.addAttribute("compet", competitionService.GetAllNameofCompetition().get());
        model.addAttribute("compet2", new StringMessage());
        return "InscriptionTournoi";
    }

    /*  @PostMapping("/InscritTournoi")
     public String Post2InscritCompetition(@ModelAttribute StringMessage msg ){
           Inscrit i = new Inscrit();
           i= this.postInscritTournoi(msg.getNom(), msg.getPrenom(),msg.getMdp());
         
           return "success";
     }*/
    @GetMapping("/InscritTournoi")
    public String postInscritTournoi(@RequestParam String nom, @RequestParam String prenom, @RequestParam String mdp) {

        Inscrit i = new Inscrit();

        try {

            Optional<Clients> c = clientservice.ForgotPassword(mdp);
            Optional<Integer> comp = competitionService.GetidCompetition(nom, prenom);
            nomuser = mdp;
            if (comp.get() == 0) {

                Optional<Clients> d = clientservice.ForgotPassword(mdp);
                Optional<Competition> comp2 = competitionService.GetCompetitionByName(nom);
                if (c.isPresent() && comp.isPresent()) {

                    //  Inscrit i = new Inscrit();
                    i.setClients(d.get());
                    i.setCompetition(comp2.get());

                    InscritPK p = new InscritPK();

                    p.setINCompetition(comp2.get().getNCompetition());
                    p.setINic(d.get().getNic());
                    p.setIdate(prenom);
                    i.setInscritPK(p);

                    inscritService.CreateInscription(i);
                  
                    nom="";   prenom=""; mdp="";
                    return "success";

                } else {

                    if (c.isPresent() && comp.isPresent()) {

                        // Inscrit i = new Inscrit();
                        i.setClients(c.get());
                        i.setCompetition(competitionService.GetCompetitionById(comp.get()).get());

                        InscritPK p = new InscritPK();

                        p.setINCompetition(comp.get());
                        p.setINic(c.get().getNic());
                        p.setIdate(prenom);
                        i.setInscritPK(p);

                        inscritService.CreateInscription(i);
                        
                         nom="";   prenom=""; mdp="";
                        return "success";
                    }
                }
            }

        } catch (NullPointerException e) {

            return "PageAccueil";

        }
        return "PageAccueil";

    }

    
    //********modification du client*********
    @GetMapping("/Modificationclient/")
    public String UpdateClient(@RequestParam StringMessage mdp, Model model) {

        try {
            Optional<Clients> c = clientservice.ForgotPassword(nomuser);

            model.addAttribute("userclient", c.get());
            if (c.isPresent()) {

                return "Updateclient";
            } else {

                return "PageAccueil";
            }
        } catch (Exception e) {

            return "PageAccueil";
        }

    }

    // update client post
    @PostMapping("/MAJclient")
    public String postUpdateInscrit(@ModelAttribute Clients newclient) {

        try {

            Optional<Clients> c = clientservice.ForgotPassword(nomuser);

            if (c.isPresent()) {
                Clients s;
                s = newclient;
                s.setNic(c.get().getNic());
                clientservice.CreateClients(s);
                return "successupdatepassword";
            } else {
                return "PageAccueil";
            }
        } catch (Exception e) {
            return "PageAccueil";
        }

    }

    //******* supprimer un client *********
    @GetMapping("/deleteclient")
    public String deleteclient(@RequestParam StringMessage mdp) {

        try {
            Optional<Clients> c = clientservice.ForgotPassword(nomuser);
            if (c.isPresent()) {
                Clients s;
                s = c.get();
                s.setNic(c.get().getNic());
                clientservice.DeleteClients(s);
            }
            return "PageAccueil";
        } catch (Exception e) {

            return "PageAccueil";
        }
    }

    //****** success ******
    @GetMapping("/success")
    public String success() {

        return "success";
    }

    //****** password modifié *******
    @GetMapping("/successupdatepassword")
    public String successmodification() {

        return "successupdatepassword";
    }

    //exple http://localhost/:8080/api/employee/1/john
    //dans le get on aura @Getmapping("/employee/{id}/{name}")
    //et dans les parametre: (@PathVariable long id, @PathVariable String name)
    //exple http://localhost/:8080/api/employee?id=1&name=john
    //dans le Get on aura @Getmapping("employee/")
    //et dans les parametre: (@RequestParam  long id,@RequestParam String name)
}
