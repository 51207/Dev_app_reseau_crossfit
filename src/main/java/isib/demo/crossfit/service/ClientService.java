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

    private final ClientsRepository clientRepository;

    @Autowired
    public ClientService(ClientsRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @PersistenceContext
    private EntityManager em;
    
    
    //get all clients
    public Iterable<Clients> GetFindAll() {

        Iterable<Clients> result = clientRepository.findAll();

        return result;
    }

    //update
    public Clients UpdateClients(Clients client) {

        //control de l'existe du NIC
        Clients c = em.find(Clients.class, client.getNic());
        if (c != null) {
            c = clientRepository.save(client);
        }

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
    public void DeleteClients(Integer NIC) {

        //control de l'existe du NIC
        Clients c = em.find(Clients.class, NIC);
        
         //verification si le client est inscrit dans une competition 
        //à faire après
        try {
            System.out.println("======");
            System.out.println("nic: " + c.getNic().toString());
            if (c != null) {
                clientRepository.delete(c);
                System.out.println("======");
                System.out.println("clients deleted");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
