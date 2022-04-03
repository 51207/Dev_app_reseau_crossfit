/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isib.demo.crossfit.service;

import isib.demo.crossfit.Repository.ClientsRepository;
import isib.demo.crossfit.Repository.CompetitionRepository;
import isib.demo.crossfit.Tables.Clients;
import isib.demo.crossfit.Tables.Competition;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author aliou
 */

@Service
public class CompetitionService {
     @Autowired
    private CompetitionRepository CompetitionRepository;
    
    @PersistenceContext
    private EntityManager em;
    
    public Iterable<Competition> GetFindAll(){
    
    Iterable<Competition> result= CompetitionRepository.findAll();
    
    return result;
    }
    
    
    
    
    
    public Clients finbyid(Clients c){
    
     Clients s =  em.find(Clients.class,c.getNic());
     return s;
    }
    
 
    
    public void CreateCompetition(Competition competition){
        
        Clients c = em.find(Clients.class,competition.getNCompetition());
      
        
        if(c == null){
             System.out.println("dans la condition");
         
            CompetitionRepository.save(competition);
            System.out.println("inserted");
         
       }else{
            System.out.println("Cet identiiant existe deja");
        }
    }
    
     public Iterable<Competition> findByCp(String cp){
        
       
         //6L : correspond au "cp"
        
          Iterable<Competition> result= em.createNamedQuery("Clients.findByCp",Competition.class)  
            .setParameter("cp", cp)
            .getResultList();
           //setparameter permet de dire que la condition dans la requete est cp dans le where
           return result;
         
      }
    
     public Long findCompetitionCount(){
         
        return (Long)em.createNamedQuery("Competition.findClientsCount").getSingleResult();
   
     }
  
       public Competition  findByRue( String rue){
            Competition c = em.getReference(Competition.class,4L);
             //4L : correspond au "cp"
            if( c != null){
            return c;
            }else{
                return null;}
       }
    
        
         
  
         
         
public void DeleteCompetition(Integer NIC){
   
        
         Competition c = em.find(Competition.class,NIC);
        try{
         System.out.println("======");
        System.out.println("nic: "+c.getNCompetition().toString() );
         if(c != null){
            CompetitionRepository.delete(c);
         System.out.println("======");
         System.out.println("clients deleted");
         }
       }catch(Exception e){e.printStackTrace();}
}

}
