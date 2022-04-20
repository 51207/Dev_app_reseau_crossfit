/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isib.demo.crossfit.Controller;

import isib.demo.crossfit.OtherClass.NotationClientInscrit;
import isib.demo.crossfit.OtherClass.StringMessage;
import isib.demo.crossfit.OtherClass.dateObject;
import isib.demo.crossfit.OtherClass.listNotationClientInscrit;
import isib.demo.crossfit.Tables.Clients;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 *
 * @author aliou
 */
@Controller
@SessionAttributes("listNotationClientInscrit")
public class CrossfiSuiviDeSession {

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

    //******Notation******
    @GetMapping("/DateNotation")
    public String getNotation(Model model) {

        model.addAttribute("dateNotation", new StringMessage());
        return "DateNotation";
    }

    @GetMapping("/Notation")
    public String getNotationForClient(@RequestParam StringMessage nom, Model model) {
        try {

            List<String> listAllName = new ArrayList<>();
            List<Integer> list = inscritService.getAllInscritByDate(nom.getNom());
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
            model.addAttribute("listClientsInscrit", listAllName);
            model.addAttribute("listAlljury", ListAlljury);
            model.addAttribute("listAllEpreuve", epreuveservice.getalldataonEpreuve());
            model.addAttribute("EncodingAllvalue", new NotationClientInscrit());

            return "Notation";

        } catch (NullPointerException e) {
            return "redirect:DateNotation";
        }
    }

    @PostMapping("/NotationClientAdd")
    public String AddNotation(@ModelAttribute NotationClientInscrit msg, listNotationClientInscrit listnotation, Model model) {
        try {
            //recherche du client 
            Optional<Clients> c = clientservice.ForgotPassword(msg.getUsername());
            Epreuve e = epreuveservice.GetEpreuvebyNomEpreuve(msg.getNomEpreuve());
            Jury j = juryservice.GetIDJurybyNomJury(msg.getNomjury());
            //listnotation.ClearListNote();
            if (c.isPresent() && c.get() != null && e != null && j != null) {
                msg.setNom(c.get().getNom());
                msg.setPrenom(c.get().getPrenom());
                msg.setIdJury(j.getNIJury());
                msg.setIdclient(c.get().getNic());
                msg.setIdNomEpreuve(e.getNie());

                //on verifie pour savoir si le test qu'on veut ajouter dans la liste product n'existe pas déjà dans la BD
                Test t = testService.getAllTestbyAllParameter(msg.getDateCompetition(), msg.getIdclient(), msg.getIdNomEpreuve(), msg.getIdJury());
                if (t == null) {
                    //on met dans la liste 
                    listnotation.setListNote(msg);
                }
                //besoin de la date pour retourner à la même page
            }
            model.addAttribute("sessionattribute", listnotation);
            return "shownoteByEpreuveOrganisateur";

        } catch (NullPointerException e) {
            return "redirect:PageAccueilOrganisateur";
        }

    }

}
