/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isib.demo.crossfit.service;

import isib.demo.crossfit.Repository.ClientsRepository;
import isib.demo.crossfit.Tables.Clients;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author aliou
 */
@Service
public class ClientService {

    @Autowired
    private ClientsRepository clientRepository;
    
    @PersistenceContext
    private EntityManager em;
    
    public Iterable<Clients> GetFindAll(){
    
    Iterable<Clients> result= clientRepository.findAll();
    
    return result;
    }
    
    
   
    public Clients finbyid(Clients c){
    
     Clients s =  em.find(Clients.class,c.getNic());
     return s;
    }
    
  /* public void DeleteClient(Clients c, Integer nic){
   
       
          System.out.println("======");
       System.out.println("nic: "+finbyid(c).getNic().toString() );
        clientRepository.deleteById(nic);
    
   } */
    
    public void CreateClients(Clients client){
        
        Clients c = em.find(Clients.class,client.getNic());
      
        /*Clients c2 = em.find(Clients.class,2);
        Clients c3 = em.find(Clients.class,3);
        Clients c4 = em.find(Clients.class,4);
        Clients c5 = em.find(Clients.class,5);
        Clients c6 = em.find(Clients.class,6);
        Clients c7 = em.find(Clients.class,7);
        Clients c8 = em.find(Clients.class,8);
        Clients c9 = em.find(Clients.class,9);
        Clients c10 = em.find(Clients.class,10);*/
        
        
        if(c == null){
             System.out.println("dans la condition");
          // if(   c2 != null &&  c3 != null &&  c4 != null  &&  c5 != null &&  c6 != null && c7 != null &&    c8 != null  &&  c9 != null &&  c10 !=null  )
           //{
            clientRepository.save(client);
            System.out.println("inserted");
            
            //}
        
       }else{
            System.out.println("Cet identiiant existe deja");
        }
    }
    
     public Iterable<Clients> findByCp(String cp){
        
       
         //6L : correspond au "cp"
        
          Iterable<Clients> result= em.createNamedQuery("Clients.findByCp",Clients.class)  
            .setParameter("cp", cp)
            .getResultList();
           //setparameter permet de dire que la condition dans la requete est cp dans le where
           return result;
         
      }
    
     public Long findClientsCount(){
         
        return (Long)em.createNamedQuery("Clients.findClientsCount").getSingleResult();
   
     }
  
       public Clients  findByRue( String rue){
            Clients c = em.getReference(Clients.class,4L);
             //4L : correspond au "cp"
            if( c != null){
            return c;
            }else{
                return null;}
       }
    
         public Clients findByLastAndFirstName( String nom, String prenom){
            Clients solution = null;
          
            Clients result= em.createNamedQuery("Clients.findByLastAndFirstName",Clients.class)  
            .setParameter("nom", nom)
            .setParameter("prenom", prenom)
            .getSingleResult();
            
            if(result != null){
              solution= result;
            }
            
          return solution;
       }
         
  
         
         
public void DeleteClients(Integer NIC){
   
        
         Clients c = em.find(Clients.class,NIC);
        try{
         System.out.println("======");
        System.out.println("nic: "+c.getNic().toString() );
         if(c != null){
            clientRepository.delete(c);
         System.out.println("======");
         System.out.println("clients deleted");
         }
       }catch(Exception e){e.printStackTrace();}
}



  
 public void UpdateNomPrenom(Integer nic,String nom, String prenom){
   Clients c = em.find(Clients.class,nic );
   
   if(c != null){
    this.em.createNamedQuery("Clients.UpdateClientNamePrenom",Clients.class).setParameter(2, nom).setParameter(3, prenom).executeUpdate() ;
   }
    /*try{
     
      clientRepository.save(client);  
      System.out.println("updated");
     }
     catch (Exception e)
     {
      e.printStackTrace();
     }*/
  }
         
         
         
}
