/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isib.demo.crossfit;


import isib.demo.crossfit.Repository.ClientsRepository;
import isib.demo.crossfit.Tables.Clients;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 *
 * @author aliou
 */

@Component
@Transactional
public class Testclass implements CommandLineRunner {

    @PersistenceContext
    EntityManager em;
    //  EntityManagerFactory emf = Persistence.createEntityManagerFactory("isib.demo_crossfit_jar_0.0.1-SNAPSHOTPU");
    // EntityManager em = emf.createEntityManager();
@Override
public void run(String... args) throws Exception {


// EntityManagerFactory emf = Persistence.createEntityManagerFactory("isib.demo_crossfit_jar_0.0.1-SNAPSHOTPU");
// EntityManager em = emf.createEntityManager();
System.out.println("============TEST bd ================");
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

}