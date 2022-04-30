/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isib.demo.crossfit.Controller;

import isib.demo.crossfit.OtherClass.NotationClientInscrit;
import isib.demo.crossfit.OtherClass.StringMessage;
import isib.demo.crossfit.OtherClass.dateObject;

import isib.demo.crossfit.OtherClass.listNotationClientInscrit;
import isib.demo.crossfit.RestAPIclass.ApiClient;
import isib.demo.crossfit.RestAPIclass.apiListClient;
import isib.demo.crossfit.RestAPIclass.apiListTest;
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
import isib.demo.crossfit.suivisession.NotationList;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author aliou
 */
@Controller

public class CrossfiClassement {

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

    /**
     * *classement**
     */
    /**
     * ****Classement partie Organisateur *******
     */
    @GetMapping("/classementOrganisateur")
    public String DateChoisiClassement(Model model, HttpSession session) {
        try {
            //on verifie si on s'est bien connecté sinon on va directement dans le catch 
            Optional<Clients> c = clientservice.ForgotPassword(session.getAttribute("loginusername").toString());
            //on met dans le model la date
            model.addAttribute("dateClassement", new dateObject());

            return "classementOrganisateur";
        } catch (NullPointerException e) {
            return "redirect:login";
        }
    }

    @GetMapping("/Notationclient")
    public String Classement(@ModelAttribute dateObject StringVariable, Model model) {
        //je recupère la date 
        String date = StringVariable.getStringVariable();
        //je recupere la liste des notes de tous les clients à une certaine date
        List<Test> listTest = testService.getAllTestByDates(date);
        try {
            //on verifie que la liste n'est pas null           
            if (listTest != null && !(listTest.isEmpty())) {
                
                
                listNotationClientInscrit listNotationClientInscrit = new listNotationClientInscrit();
                listNotationClientInscrit.ClearProducts();
                
                for (var item : listTest) {
                    //j'initialise les différentes variables
                    NotationClientInscrit Notationclient = new NotationClientInscrit();
                    Notationclient.setDateCompetition(date);
                    Notationclient.setIdJury(item.getTestPK().getTJury());
                    Notationclient.setIdNomEpreuve(item.getTestPK().getTnie());
                    Notationclient.setIdclient(item.getTestPK().getTnic());
                    Clients c = clientservice.GetByNicId(item.getTestPK().getTnic());
                    Epreuve e = epreuveservice.FindEpreuve(item.getTestPK().getTnie());
                    Jury j = juryservice.getIdJury(item.getTestPK().getTJury());
                    Notationclient.setNom(c.getNom());
                    Notationclient.setPrenom(c.getPrenom());
                    Notationclient.setPremiereNote(item.getNote());
                    Notationclient.setUsername(c.getUsername());
                    Notationclient.setNomEpreuve(e.getNEpreuve());
                    Notationclient.setNomjury(j.getNomJury());
                    //on ajoute dans la liste <<products>>
                    listNotationClientInscrit.setProduct(Notationclient);

                }
                
                
                List<ArrayList> list = listNotationClientInscrit.GetAllNote();
                //on recupere le classement ici
                List<ArrayList> secondlist = listNotationClientInscrit.SortAllNote(list);
                model.addAttribute("listTest", secondlist);
                //il faut ajouter d'autres variables dans les différentes liste
                this.methodSort(model, listNotationClientInscrit);

                //il me manque aussi de variable dans la liste getAllNomEpreuve 
                //je vais utiliser cette liste comme en^tête de la table de classement
                //du coup il faut inserer à l'index 0 , String rang  et dernier element de la liste string moyenne
                /* List<String> EnteteTable = listNotationClientInscrit.GetallEpreuveName();
                //index 0
                EnteteTable.add(0, "Username");
                EnteteTable.add(0, "rang");
                //dernier index
                EnteteTable.add("moyenne");
                model.addAttribute("EnteteTable", EnteteTable);*/
                return "shownoteByOrganisateur";

            } else {

                //sinon on demande au service rest de nous fournir ce qu'on veut
                listNotationClientInscrit listNotationClientInscrit = new listNotationClientInscrit();
                listNotationClientInscrit.ClearProducts();
                ClientRestApi api = new ClientRestApi();
                // api.GetAlltESTbyDate(date);
                //si le service rest retourne une list non vide
                List<NotationClientInscrit> listNoteRest = api.GetAlltESTbyDate(date).getList();
                if (!(listNoteRest.isEmpty())) {
                    //on ajoute chaque element de la liste reçu du serviceRest dans la liste products
                    for (var itemvar : listNoteRest) {

                        listNotationClientInscrit.setProduct(itemvar);
                    }

                    this.methodSort(model, listNotationClientInscrit);
                    
                    
                    return "shownoteByOrganisateur";

                }
                return "classementOrganisateur";
            }
        } catch (NullPointerException e) {

            return "PageAccueilOrganisateur";
        }
    }
    
    @PostMapping("/InsertNoteBD")
    public String InsertTestToBD(HttpSession session,NotationList notationlist){
        
        try{
            notationlist = (NotationList) session.getAttribute("notationList");
            for( var item : notationlist.getNotations()){
            
                Integer IDClient = item.getNic().getNic();
                Integer IDEpreuve = item.getNec().getNie();
                Integer IDJury= item.getNIJury().getNIJury();
                Integer Note = item.getNote();
                String date = item.getDate();
                Test t= testService.getAllTestbyAllParameter(date,IDClient,IDEpreuve,IDJury);
                if( t == null){
                
                  Test test=  testService.CreateTestAllParametre(date, item.getNic(), item.getNec(), item.getNIJury(), Note);
                  testService.CreateNewTest(test);
                }
            }
            notationlist.getNotations().clear();
           
           
            return "PageAccueilOrganisateur";
            
        }catch(NullPointerException e){
            
            return "redirect:PageAccueilOrganisateur";
        
        }
    
    
        
    }
    

    //On recupere le 
    public void methodSort(Model model, listNotationClientInscrit listNotationClientInscrit) {
        //on recupere toutes les notes 
        List<ArrayList> list = listNotationClientInscrit.GetAllNote();
        //on trie ici (en faisant le classement)
        List<ArrayList> secondlist = listNotationClientInscrit.SortAllNote(list);
        model.addAttribute("listTest", secondlist);

        //il me manque aussi de variable dans la liste getAllNomEpreuve 
        //je vais utiliser cette liste comme en^tête de la table de classement
        //du coup il faut inserer à l'index 0 , String rang  et dernier element de la liste string moyenne
        List<String> EnteteTable = listNotationClientInscrit.GetallEpreuveName();
        //index 0
        EnteteTable.add(0, "Username");
        //
        EnteteTable.add(0, "rang");
        //dernier index
        EnteteTable.add("moyenne");
       
        model.addAttribute("EnteteTable", EnteteTable);

    }

    
  
    
    
    //pour enregistrer tous les clients dans le service rest 
    @GetMapping("/SaveClientInRestService")
    public String ServiceRestPost(@ModelAttribute StringMessage nom, Model model) {
        try {
            //je  recupere tous les clients de la BD
            Iterable<Clients> listclients = clientservice.GetFindAll();
            
            if (listclients != null) {

                RestTemplate rst = new RestTemplate();
                apiListClient list = new apiListClient();

                for (var item : listclients) {
                    ApiClient api = new ApiClient();
                    api.setNic(item.getNic());

                    api.setNom(item.getNom());
                    api.setPrenom(item.getPrenom());

                    api.setRue(item.getRue());
                    api.setNumero(item.getNumero());
                    api.setCp(item.getCp());
                    api.setCommune(item.getCommune());
                    api.setTel(item.getTel());
                    api.setUsername(item.getUsername());
                    api.setPassword(item.getPasswordclient());
                    //on ajoute dans la liste chaque api
                    list.setList2(api);
                }
                //on fait un postMapping 
                rst.postForObject("http://localhost:8081/apijson/PostClients", list, apiListClient.class);
            }

            return "successCreationOrganisateur";
        } catch (NullPointerException e) {

            return "PageAccueilOrganisateur";
        }
    }

    
    
    //************partie client classement **************
    //choix de la date pour le classement
    @GetMapping("/classement")
    public String DateChoisiClassementClient(Model model, HttpSession session) {

        try {
            //on verifie si  on est bien connecter avec un nom d'utilisateur qui est stocké dans la session
            Optional<Clients> c = clientservice.ForgotPassword(session.getAttribute("loginusername").toString());
            //choix de la date pour obtenir le classement
            model.addAttribute("dateclassement", new dateObject());
            return "Classement";
        } catch (NullPointerException e) {
            return "redirect:login";
        }

    }

    //permet d'afficher le classement
    @GetMapping("/ClassementSportif")
    public String ClassementSportif(@ModelAttribute dateObject StringVariable, Model model) {
        String date = StringVariable.getStringVariable();
        List<Test> listTest = testService.getAllTestByDates(date);
        try {
            //attention: si la liste est null, cvd qu'il n'existe pas dans la BD de crossfit , donc il va aller chercher dans le service rest
            if (listTest != null && !(listTest.isEmpty())) {
                listNotationClientInscrit listNotationClientInscrit = new listNotationClientInscrit();
                    listNotationClientInscrit.ClearProducts();
                for (var item : listTest) {

                    NotationClientInscrit Notationclient = new NotationClientInscrit();
                    Notationclient.setDateCompetition(date);
                    Notationclient.setIdJury(item.getTestPK().getTJury());
                    Notationclient.setIdNomEpreuve(item.getTestPK().getTnie());
                    Notationclient.setIdclient(item.getTestPK().getTnic());
                    Clients c = clientservice.GetByNicId(item.getTestPK().getTnic());
                    Epreuve e = epreuveservice.FindEpreuve(item.getTestPK().getTnie());
                    Jury j = juryservice.getIdJury(item.getTestPK().getTJury());
                    Notationclient.setNom(c.getNom());
                    Notationclient.setPrenom(c.getPrenom());
                    Notationclient.setPremiereNote(item.getNote());
                    Notationclient.setUsername(c.getUsername());
                    Notationclient.setNomEpreuve(e.getNEpreuve());
                    Notationclient.setNomjury(j.getNomJury());
                    listNotationClientInscrit.setProduct(Notationclient);

                }
                List<ArrayList> list = listNotationClientInscrit.GetAllNote();
                //on recupere le classement ici
                List<ArrayList> secondlist = listNotationClientInscrit.SortAllNote(list);
                model.addAttribute("listTest", secondlist);
                this.methodSort(model, listNotationClientInscrit);

                return "AllClassementClient";

            } else {

                //sinon on demande au service rest de nous fournir ce qu'on veut
                listNotationClientInscrit listNotationClientInscrit = new listNotationClientInscrit();
                listNotationClientInscrit.ClearProducts();
                ClientRestApi api = new ClientRestApi();
                // api.GetAlltESTbyDate(date);
                //si le service rest retourne une list non vide
                List<NotationClientInscrit> listNoteRest = api.GetAlltESTbyDate(date).getList();
                if (!(listNoteRest.isEmpty())) {
                    //on ajoute chaque element de la liste reçu du serviceRest dans la liste products
                    for (var itemvar : listNoteRest) {

                        listNotationClientInscrit.setProduct(itemvar);
                    }

                    this.methodSort(model, listNotationClientInscrit);
                    //on recupere le classement ici
                    /* 
                    //il me manque aussi de variable dans la liste getAllNomEpreuve 
                    //je vais utiliser cette liste comme en^tête de la table de classement
                    //du coup il faut inserer à l'index 0 , String rang  et dernier element de la liste string moyenne
                     */
                    return "AllClassementClient";

                } else {
                    return "PageAccueil";
                }
            }
        } catch (NullPointerException e) {

            return "PageAccueil";
        }
    }

}
