/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isib.demo.crossfit.service;

import isib.demo.crossfit.Repository.ClientsRepository;
import isib.demo.crossfit.Tables.Clients;
import isib.demo.crossfit.Tables.Inscrit;
import isib.demo.crossfit.Tables.Test;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

/**
 *
 * @author aliou
 */
@Service
public class ClientService {

    private final ClientsRepository clientRepository;

    @Autowired
    public ClientService(ClientsRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @PersistenceContext
    private EntityManager em;
    
    private testService testservice;
    private InscritService incritService;
    
    //get all clients
    public Iterable<Clients> GetFindAll() {

        Iterable<Clients> result = clientRepository.findAll();

        return result;
    }

    //update
    public void UpdateClients(Clients client) {

        //control de l'existe du NIC
        Clients c = em.find(Clients.class, client.getNic())  ;
        Optional<Clients> result = Optional.of(c);
        if(result.isPresent()){
            clientRepository.save(c);}
        //return result;
    }
     public Clients UpdateClient(Clients client) {
        Clients c = clientRepository.findById(client.getNic()).orElseThrow();
         
        clientRepository.save(c);
        return c;
    }

    //Get Client by nic
    public Clients GetByNic(Clients c) {

        Clients s = em.find(Clients.class, c.getNic());
        return s;
    }

    //Create
    public void CreateClients(Clients client) {

        clientRepository.save(client);

    }

    //get clients by cp
    public Iterable<Clients> findByCp(String cp) {
        
        Iterable<Clients> result = em.createNamedQuery("Clients.findByCp", Clients.class)
                .setParameter("cp", cp)
                .getResultList();

        return result;
    }

    //recuperer NIC en encodant nom , prenom , tel 
    public Clients findByLastAndFirstName(String nom, String prenom, String tel) {

        Clients result = em.createNamedQuery("Clients.findByLastAndFirstName", Clients.class)
                .setParameter(2, nom)
                .setParameter(3, prenom)
                .setParameter(8, tel)
                .getSingleResult();

        return result;
    }
    
 
    //nombres de clients in DB
    public Long findClientsCount() {

        return (Long) em.createNamedQuery("Clients.findClientsCount").getSingleResult();
    }

    
    //find client by adresse=>Rue
    public Clients findByRue(String rue) {
        Clients c = em.getReference(Clients.class, 4L);
       
        if (c != null) {
            return c;
        } else {
            return null;
        }
    }

    
  
    //delete client
    public void DeleteClient(Optional<List<Test>> test, Optional<List<Inscrit>> inscrit, Clients client) {

        //control de l'existe du NIC
        Clients c = em.find(Clients.class, client.getNic());
        Optional<Clients> result = Optional.of(c);
        //verification si le client est inscrit dans une competition s'il a des notes aussi

        //supprimer toutes ses notes
        if (test.isPresent()) {

            for (var item : test.get()) {
                testservice.DeleteTest(item);
            }
            //supprimer ces inscriptions
          if(inscrit.isPresent()){ 
            for (var item2 : inscrit.get()) {

                incritService.DeleteInscrit(item2);
            }
          }
          
          if(result.isEmpty()){
          
                clientRepository.delete(result.get());
          }
          
        }
        
      
    }
     public void DeleteClients( Clients client) {

        //control de l'existe du NIC
       
                clientRepository.delete(client);
          
          
        }
        
      
    
     public Optional<List<String>> GetListJury(String nomClient ,String date){
         
        // TypedQuery<List<String>> query = em.createQuery("Select j.nomJury from Clients c join c.testCollection t join t.jury j where c.nom=:nom and t.testPK.tDates=:date");
          List<String> c = clientRepository.GetListJury(nomClient, date);
          Optional<List<String>> result = Optional.of(c);
         return result;
     }
    
    
     //recuper l'id à travers le username et le password pour recuperer l'id
    public Clients GetLogin(String username,String password){
    
        Clients c = clientRepository.GetLogin(username, password);
        //Optional<Clients> result = Optional.of(c);
        
        return c;
    }
    
    
    //recuperer le client qui a comme username:String username
      public  Optional<Clients> ForgotPassword(@Param("username") String username){
      
        Clients c = clientRepository.ForgotPassword(username);
        Optional<Clients> result = Optional.of(c);
        
        return result;
      }
      
      

}
