/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isib.demo.crossfit;

import isib.demo.crossfit.Repository.ClientsRepository;
import isib.demo.crossfit.Tables.Clients;
import isib.demo.crossfit.Tables.Competition;
import isib.demo.crossfit.service.ClientService;
import isib.demo.crossfit.service.CompetitionService;
import isib.demo.crossfit.Controller.ClientRestApi;
import isib.demo.crossfit.RestAPIclass.apiListTest;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author aliou
 */
@Component
@Transactional
public class Testclass implements CommandLineRunner {

   
    
     @Autowired
    private ClientService clientservice;
    
     @Autowired
    private CompetitionService competitionService;
    
    @PersistenceContext
    EntityManager em;

  
    //  EntityManagerFactory emf = Persistence.createEntityManagerFactory("isib.demo_crossfit_jar_0.0.1-SNAPSHOTPU");
    // EntityManager em = emf.createEntityManager();

    @Override
    public void run(String... args) throws Exception {

        /**/
       //testclient();
        //UpdateClient_test();
        listeclienttest();
        //System.out.println("=============delete=============");
        //clientservice.DeleteClients(10024);
      
        
        
     /*   Optional<List<String>> c = clientservice.GetListJury("Traoré","2021-04-11");
       if( c.isPresent()){
           System.out.println("liste des jury"+c.get());
       };*/
        /*String date = "2019-04-11";
        ClientRestApi api = new ClientRestApi();
        api.GetAlltESTbyDate(date);*/
        
      /*  System.out.println("=======================================");
        RestTemplate rst = new RestTemplate();
        apiListTest api = rst.getForObject("http://localhost:8081/apijson/listTest/" + date, apiListTest.class);

        for (var item : api.getList()) {

            System.out.println(item.toString());
        }*/
    }
    
    
    public void UpdateClient_test() {
//update , on doit fournir la clé primaire pour modifier
        Clients c = new Clients();
        c.setNic(10021);
        c.setNom("virgil");
        c.setPrenom("kaba");
        c.setRue("rue du shooping");
        c.setNumero("21");
        c.setCommune("Ixelles");
        c.setCp("1050");
        c.setTel("0465897845");
        c.setUsername("kat");
        c.setPasswordclient("isib");
        clientservice.UpdateClients(c);
       
            
      
    }

    public void listeclienttest() {
        int x = 0;
        System.out.println("============findall==============");
        for (Clients item : clientservice.GetFindAll()) {
            x += 1;
            System.out.println("clients " + x + " : " + item.getNom() + " " + item.getPrenom() + " nic :" + item.getNic()+ " username="+item.getUsername()+ "  a4password: "+item.getPasswordclient());

            
        }
        
        
        System.out.println("=============findcp=============");

        for (Clients item : clientservice.findByCp("1000")) {
//body of for-each loop
            System.out.println("client habitant dans le code postal  1000 = " + item.getNom());
//clients.findByCp(em,"1000");
        }
        
       /*  Optional<Clients> xq= clientservice.GetLogin("jack1", "isib");
     
            if(xq.isPresent()){
                 System.out.println("login : "+ xq.get()+" ");
                  System.out.println("login : "+ xq.get().getNic()+" ");
           System.out.println("login  "+ xq.get().getPrenom() +" "+ xq.get().getNom());
            }*/

    }

    public void CreateClient_test() {

        Clients c = new Clients();

        //create 
        c.setNom("patson");
        c.setPrenom("kaba");
        c.setRue("rue du shooping");
        c.setNumero("21");
        c.setCommune("Ixelles");
        c.setCp("1050");
        c.setTel("0465897845");
        c.setUsername("kat");
        c.setPasswordclient("isib");
        clientservice.CreateClients(c);
    }
    
    
     
     
     
     
     

     public void testclient(){
     
     
        System.out.println("=============list=============");
        listeclienttest();
        System.out.println();

        System.out.println("=============insert=============");
        //create 
        CreateClient_test();
        System.out.println();
        System.out.println("=============update=============");
        //update , on doit fournir la clé primaire pour modifier
        UpdateClient_test();
        System.out.println();

        //System.out.println("=============delete=============");
        //clientservice.DeleteClients(10023);
       
        System.out.println();

        System.out.println("=============list modifié=============");
        listeclienttest();
        System.out.println();
     }
     
     
     
     
     
   
}

// EntityManagerFactory emf = Persistence.createEntityManagerFactory("isib.demo_crossfit_jar_0.0.1-SNAPSHOTPU");
// EntityManager em = emf.createEntityManager();
/*System.out.println("============TEST bd ================");
Clients clients = new Clients();
for(Clients item  : clients.findAll(em)){  
//body of for-each loop
System.out.println("clients "+ item.getNom());
//clients.findByCp(em,"1000");
}
clients.findByNic(em,10001);

if(clients.findByCp(em,"1000") != null){
    for(Clients item  : clients.findByCp(em,"1000")){  
//body of for-each loop
        System.out.println("client habitant dans le code postal  1000 = "+ item.getNom());
//clients.findByCp(em,"1000");
    }
}
System.out.println("========client count==========");
Long  i = clients.findClientsCount(em);
System.out.println("nombre de client inscrit = "+i.toString());
}
 */

