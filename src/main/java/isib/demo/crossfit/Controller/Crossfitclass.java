/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package isib.demo.crossfit.Controller;

import isib.demo.crossfit.OtherClass.Message;
import isib.demo.crossfit.OtherClass.StringMessage;
import isib.demo.crossfit.RestAPIclass.ApiClient;
import isib.demo.crossfit.Tables.Clients;
import isib.demo.crossfit.Tables.Inscrit;
import isib.demo.crossfit.service.ClientService;
import isib.demo.crossfit.service.CompetitionService;
import isib.demo.crossfit.service.ComporteService;
import isib.demo.crossfit.service.testService;
import isib.demo.crossfit.service.JuryService;
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
    @Autowired
    private JuryService juryservice;
    @Autowired
    private EpreuveService epreuveservice;

    public List<StringMessage> panier;
    //****** variable utiliser dans la conservateur du user et du password *******
    public static String nomuser = "";
    public String password = "";

    //*****Page d'Accueil******
    @GetMapping("/**")
    public String page(Model model) {

        //return "PageAccueil";
        return "Accueil";
    }

    @GetMapping("/PageAccueil")
    public String pageAccueil(Model model) {

        return "PageAccueil";
    }

    //******Inscription au sport******
    @GetMapping("/Inscription")
    public String Inscrition(Model model) {

        model.addAttribute("inscription", new Clients());
        return "Inscription";
    }

    @PostMapping("/Inscript")
    public String postInscription(@ModelAttribute Clients newclient, Model model) {
        //on verifie pour savoir si ce username existe,sion on peut dès lors crée 

        if (clientservice.ForgotPassword(newclient.getUsername()) == null) {
            //session.getAttribute("loginusername").toString()
            clientservice.CreateClients(newclient);
            return "success";
        } else {

            return "redirect:Inscription";
        }
    }

    //****** Inscription au tournoi ********
    @GetMapping("/Tournoi")
    public String InscritTournoi(Model model, HttpSession session) {
        try {
            //on verifie si on est bien logger en tant qu'utilisateur
            Optional<Clients> c = clientservice.ForgotPassword(session.getAttribute("loginusername").toString());

            model.addAttribute("compet", competitionService.GetAllNameofCompetition().get());
            model.addAttribute("compet2", new StringMessage());
            return "InscriptionTournoi";
        } catch (NullPointerException e) {

            return "redirect:login";
        }
    }

    @GetMapping("/InscritTournoi")
    public String postInscritTournoi(@RequestParam String nom, @RequestParam String prenom, @RequestParam String mdp) {

        Inscrit i = new Inscrit();

        try {

            Optional<Clients> c = clientservice.ForgotPassword(mdp);
            Optional<Integer> comp = competitionService.GetidCompetition(nom, prenom);
            // nomuser = mdp;
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

                        return "success";
                    }
                }
            }

        } catch (NullPointerException e) {

            return "redirect:login";

        }
        return "PageAccueil";

    }

    //********desinscription********
    @GetMapping("/DesinscritTournoi")
    public String DesinscritTournoi(@ModelAttribute StringMessage msg, HttpSession session) {
        Inscrit i = new Inscrit();

        try {
            //on verifie qu'on s'est bien logger
            Optional<Clients> c = clientservice.ForgotPassword(session.getAttribute("loginusername").toString());

            //ensuite on supprime toutes les notes (Test) concernant cette personne
            if (c.get() != null) {

                Optional<List<Test>> list = testService.GetAllTestById(c.get().getNic());
                if (list.get() != null) {
                    testService.DeleteAllTestSelectedById(list);
                }

                //on supprime  toutes ses inscription dans des com
                Optional<List<Inscrit>> listinscrit = inscritService.GetAllInscritById(c.get().getNic());
                if (listinscrit.get() != null) {
                    for (var item : listinscrit.get()) {
                        inscritService.DeleteInscrit(item);
                    }
                }
                //on supprime enfin le client
                clientservice.DeleteClients(c.get());

            }

            return "success";

        } catch (NullPointerException e) {

            return "redirect:login";

        }

    }

    //********modification du client*********
    @GetMapping("/Modificationclient")
    public String UpdateClient(Model model, HttpSession session) {
        //public String UpdateClient(@RequestParam StringMessage mdp, Model model) {

        try {
            // Optional<Clients> c = clientservice.ForgotPassword(nomuser);
            Optional<Clients> c = clientservice.ForgotPassword(session.getAttribute("loginusername").toString());

            model.addAttribute("userclient", c.get());
            if (c.isPresent()) {

                return "Updateclient";
            } else {

                return "PageAccueil";
            }
        } catch (NullPointerException e) {

            return "redirect:login";
        }

    }

    // update client post
    @PostMapping("/MAJclient")
    public String postUpdateInscrit(@ModelAttribute Clients newclient, HttpSession session) {

        try {

            // Optional<Clients> c = clientservice.ForgotPassword(nomuser);
            Optional<Clients> c = clientservice.ForgotPassword(session.getAttribute("loginusername").toString());

            if (c.isPresent()) {
               
                
               //***modification du même client dans le service rest (s'il existe)
              ClientRestApi clientrestapi = new ClientRestApi();
              clientrestapi.UpdateClientServiceRest(newclient,(String)session.getAttribute("loginusername"));
               
                //***modification du client***
                Clients s;
                s = newclient;
                s.setNic(c.get().getNic());
                clientservice.CreateClients(s);
                
                return "successupdatepassword";
            } else {
                return "PageAccueil";
            }
        } catch (Exception e) {
            return "redirect:login";
        }

    }

    //******* supprimer un client *********
    @GetMapping("/deleteclient")
    public String deleteclient(HttpSession session) {
        //public String deleteclient(@RequestParam StringMessage mdp)
        //@ModelAttribute("loginusername") String nomuser
        try {
            // 
            Optional<Clients> c = clientservice.ForgotPassword(session.getAttribute("loginusername").toString());
            if (c.isPresent() && c.get() != null) {
                
                //on supprime dabord le meme client dans le service rest
                ClientRestApi rs = new ClientRestApi();
                String username = (String)session.getAttribute("loginusername");
                rs.DeleClientServiceRest(username);
                
                Clients s;
                s = c.get();
                s.setNic(c.get().getNic());

                //rechercher  quels sont les notes qu'on lui a donnée dans des competitions            
                testService.DeleteAllTestSelectedById(testService.GetAllTestById(c.get().getNic()));
                //rechercher s'il a participer à une compétition
                inscritService.DeleteAllInscritSelectedById(inscritService.GetAllInscritById(c.get().getNic()));
                clientservice.DeleteClients(s);
            }
            return "PageAccueil";
        } catch (NullPointerException e) {

            return "redirect:login";
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
