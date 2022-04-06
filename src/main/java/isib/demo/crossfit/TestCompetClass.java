/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isib.demo.crossfit;

import isib.demo.crossfit.Repository.ComporteRepository;
import isib.demo.crossfit.Tables.Competition;
import isib.demo.crossfit.Tables.Comporte;
import isib.demo.crossfit.Tables.Test;
import isib.demo.crossfit.service.ClientService;
import isib.demo.crossfit.service.CompetitionService;
import isib.demo.crossfit.service.ComporteService;
import isib.demo.crossfit.service.testService;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 *
 * @author aliou
 */

   @Component
@Transactional
public class TestCompetClass implements CommandLineRunner {

   
    
    
       @Autowired
    private CompetitionService competitionService;
    
      @Autowired
      private ComporteService comporteService;
       
     @Autowired
     private testService testService;
      
    @PersistenceContext
    EntityManager em;

  
    //  EntityManagerFactory emf = Persistence.createEntityManagerFactory("isib.demo_crossfit_jar_0.0.1-SNAPSHOTPU");
    // EntityManager em = emf.createEntityManager();

    @Override
    public void run(String... args) throws Exception {

     /* CreateCompet_test();
      System.out.println("=============list=============");
      listecompettest();
       System.out.println("=============update=============");
       UpdateCompet_test();
        listecompettest();
       System.out.println("=============delete=============");
       competitionService.DeleteCompetition(4);
       System.out.println("=============list=============");
      listecompettest();*/
      /* System.out.println("=============count=============");
       System.out.println("count =  "+ competitionService.GetCompetitionCount());
      
        System.out.println("=============list des nom de competition=============");
       for (String item : competitionService.GetAllNameofCompetition()){
        System.out.println(item );
       
       }*/
      System.out.println("=============Get id competition=============");
       
       Optional<Integer> p= competitionService.GetidCompetition("crossfit","2022-04-11");
       if(p.isPresent()){
           System.out.println("l'id est =>"+p.get());
       }
       System.out.println("=============Get all dates Competition=============");
       Optional<List<String>> q = competitionService.GetAllDateOfCompetition();
        if(q.isPresent()){
           System.out.println("dates= "+q.toString());
           for(var item : q.get()){
               System.out.println("dates= "+item);
           }
       }
        
        System.out.println("=============Get all dates Comporte=============");
        
         Optional<List<Comporte>> t =comporteService.GetAllComporte();
        if(t.isPresent()){
           System.out.println("ALL COMPORTE = "+t.toString());
        } 
        
          System.out.println("=============Get all epreuve ffor crossffit Competitio;=============");
        
         Optional<List<String>> v =comporteService.GetAllEpreuveByCompetition("crossfit","2022-04-11");
        if(v.isPresent()){
           System.out.println("epreuve= "+v.toString());
        }  
       
          System.out.println("=============get note;=============");
        
         Optional<List<Test>> w =testService.GetTestbyEpreuve("Box Jump","Traoré");
        if(w.isPresent()){
           System.out.println("test= "+w.toString()+"\n");
           for(var item : w.get()){
               
           System.out.println("=>"+item+"\n");
           }
        }  
    } 
        
       public void CreateCompet_test() {
           Competition c = new Competition();

           //create 
           c.setNomOrganisateur("marcus");
           c.setNomcompetition("musculation");
           c.setPrenomOrganisateur("garvey");
           c.setUser("ali1");
           c.setPassword("isib");

           competitionService.CreateCompetition(c);
       }
       
       public void listecompettest() {
           int x = 0;
           System.out.println("============findall==============");
           for (Competition item : competitionService.GetFindAll()) {
               x += 1;
               System.out.println("clients " + x + " : " + item.getNomcompetition() + " organisé par  " + item.getNomOrganisateur() + " username  :" + item.getUser()+ "  NCompetition : "+item.getNCompetition());

           }
       }
       
           public void UpdateCompet_test() {
            //update , on doit fournir la clé primaire pour modifier
           Competition c = new Competition();
           c.setNCompetition(4);
           c.setNomOrganisateur("JOHN");
           c.setNomcompetition("musculation");
           c.setPrenomOrganisateur("garvey");
           c.setUser("ali100");
           c.setPassword("isib");
           competitionService.UpdateCompetition(c);
       }

}
