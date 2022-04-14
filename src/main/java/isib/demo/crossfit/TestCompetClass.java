/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isib.demo.crossfit;

import isib.demo.crossfit.OtherClass.noteclass;
import isib.demo.crossfit.Repository.ComporteRepository;
import isib.demo.crossfit.Tables.Clients;
import isib.demo.crossfit.Tables.Competition;
import isib.demo.crossfit.Tables.Comporte;
import isib.demo.crossfit.Tables.Epreuve;
import isib.demo.crossfit.Tables.Test;
import isib.demo.crossfit.service.ClientService;
import isib.demo.crossfit.service.CompetitionService;
import isib.demo.crossfit.service.ComporteService;
import isib.demo.crossfit.service.testService;
import static java.lang.System.in;
import java.util.ArrayList;
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
       System.out.println("=============Get all name of competition=============");
       Optional<List<String>> zz= competitionService.GetAllNameofCompetition();
        Iterable<Competition> qq= competitionService.GetFindAll();
       if( zz.isPresent()){
       System.out.println( "name " +zz.get());
           System.out.println( "compet " +qq);
       }
      System.out.println("=============Get id competition=============");
       
      // Optional<Integer> p= competitionService.GetidCompetition("crossfit01","2022-04-11");
       //if(p.isPresent()){
        //   System.out.println("l'id est =>"+p.get());
      // }
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
        
        // Optional<List<Test>> w =testService.GetTestbyEpreuve("Box Jump","Traoré");
/*        Optional<List<Object[]>> w = testService.GetTestbyEpreuve("Squat en arriere 102kg","Traoré","2021-04-11");
        
        for(var item : w.get()){
        Epreuve e =(Epreuve)item[0];
        Clients c = (Clients)item[1];
        Test b = (Test)item[2];
        
        System.out.println(e.getNEpreuve()+"           |         "+ c.getNom()+"            |          "+b.getNote()+  "\n");*/
        //System.out.println((Clients)item[1]+"\n");
       //  System.out.println((Test)item[2]+"\n");
        
       // }
        /*if(w.isPresent()){
           System.out.println("test= "+w.toString()+"\n");
           for(var item : w.get()){
               
           System.out.println("=>"+item+"\n");
           }
        } */ 
        System.out.println("=====================note class  ============");
     /*  
        List<Object[]> kks=  testService.GetTestbyclientAndDates("Traoré","2021-04-11");
        //for(int i = 0 ; i< kk.size(); i++){
          List<noteclass> listnote = new ArrayList<noteclass>();
          
          
        for(var item : kks){
             Epreuve e =(Epreuve)item[0];
        Clients c = (Clients)item[1];
        Test b = (Test)item[2];
              System.out.println( c.getNom()+"           |         "+ e.getNEpreuve()+"            |          "+b.getNote()+  "\n");
           
        noteclass noteclass = new noteclass();
        
        noteclass.setNom(c.getNom());
        noteclass.setNomepreuve( e.getNEpreuve());
        noteclass.setNote1(b.getNote());
     
       
        int count = 0;
        if(listnote.size() >=1){
       for(var item2 : listnote){
           if(item2.getNomepreuve() == noteclass.getNomepreuve()){
               item2.setNote2(noteclass.getNote1());
              item2.setNotetotal((item2.getNote1()+item2.getNote2())/2);
              count++;
           }
          
       }
       
        
        }
        
        
        if(count==0){
             listnote.add(noteclass);
           }
        }*/
            
            
      
     
    } 
        
       public List<noteclass> getAllNote(int idclient, String dates) {
           //je recupere la liste d'objet
           List<Object[]> ListGetObject = testService.GetTestbyclientAndDates(idclient, dates);
          
           //j'intancie la liste dans laquelle les données du clients
           List<noteclass> listnote = new ArrayList<noteclass>();

           for (var item : ListGetObject) {
               Epreuve e = (Epreuve) item[0];
               Clients c = (Clients) item[1];
               Test b = (Test) item[2];

               noteclass noteclass = new noteclass();

               noteclass.setNom(c.getNom());
               noteclass.setNomepreuve(e.getNEpreuve());
               noteclass.setNote1(b.getNote());
              
               int count = 0;
               if (listnote.size() >= 1) {
                   for (var item2 : listnote) {
                       
                       //je verifie si le item2 n' a pas le meme id qu'un element de la liste
                       if (item2.getNomepreuve() == noteclass.getNomepreuve()) {
                          
                           //si c'est le cas, alors j'ajoute la deuxieme note dans un attribut de noteclass
                           item2.setNote2(noteclass.getNote1());
                           item2.setNotetotal((item2.getNote1() + item2.getNote2()) / 2);
                           count++;
                       }

                   }

               }
               
               if (count == 0) {
                   listnote.add(noteclass);
               }
           }

           return listnote;
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
