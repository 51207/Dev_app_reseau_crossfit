/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isib.demo.crossfit;


import isib.demo.crossfit.Repository.ClientsRepository;
import isib.demo.crossfit.Tables.Clients;
import isib.demo.crossfit.service.ClientService;
import java.util.Iterator;
import java.util.List;
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

/**
 *
 * @author aliou
 */

@Component
@Transactional
public class Testclass implements CommandLineRunner {

    @Autowired 
    private ClientService clientservice;
    @PersistenceContext
    EntityManager em;
    
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    //  EntityManagerFactory emf = Persistence.createEntityManagerFactory("isib.demo_crossfit_jar_0.0.1-SNAPSHOTPU");
    // EntityManager em = emf.createEntityManager();
@Override
public void run(String... args) throws Exception {
int x = 0;
System.out.println("============findall==============");  
for(Clients item  : clientservice.GetFindAll()){  
    x+=1;
System.out.println("clients "+x+" : "+ item.getNom()+" "+item.getPrenom() +" nic :"+item.getNic());


//clients.findByCp(em,"1000");
}
System.out.println("=============findcp=============");

  for(Clients item  : clientservice.findByCp("1000")){  
//body of for-each loop
        System.out.println("client habitant dans le code postal  1000 = "+ item.getNom());
//clients.findByCp(em,"1000");
    }
  
  System.out.println("=============insert=============");
  Clients c = new Clients();
  c.setNic(10007);
  c.setNom("patson");
  c.setPrenom("kaba");
  c.setRue("rue du shooping");
  c.setNumero("21");
  c.setCommune("Ixelles");
  c.setCp("1050");
  c.setTel("0465897845");
  c.setUsername("kat");
  c.setPasswordclient("isib");
  //clientservice.insertWithQuery(c);
  clientservice.CreateClients(c);
 // clientservice.DeleteClients(10011);
   // c.setNom("dawson");
  // clientservice.UpdateClients(c);
 //clientservice.DeleteClients(100);
 clientservice.UpdateNomPrenom(10010, "fabinhoo", "marc");
 
 
 
 int j = 0;
System.out.println("============findall==============");  
for(Clients item  : clientservice.GetFindAll()){  
    j+=1;
System.out.println("clients "+j+" : "+ item.getNom()+" "+item.getPrenom() +" nic :"+item.getNic());

//clients.findByCp(em,"1000");
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



}