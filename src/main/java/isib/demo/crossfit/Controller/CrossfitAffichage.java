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
import isib.demo.crossfit.service.InscritService;
import isib.demo.crossfit.service.testService;
import java.util.List;
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
    
    
     //****** variable utiliser dans la conservateur du user et du password *******
    public String nomuser = "";
    public String password = "";
    
    
    @GetMapping("/Affichage")
    public String Affichage(Model model){
        model.addAttribute("output", new StringMessage());
        model.addAttribute("nameofcompet", competitionService.GetAllNameofCompetition().get());
    return "affichageResultat";
    }
    
    @GetMapping("/affichageresult")
       public String Affichageresult(@RequestParam String nom, @RequestParam String prenom, @RequestParam String mdp,Model model){
        Optional<Clients> c = clientservice.ForgotPassword(mdp);
        
          List<noteclass> list =   testService.getAllNote(c.get().getNic(), prenom);
          model.addAttribute("resu1t", list);
           return "affichageResultat";
       }
}
