/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isib.demo.crossfit.service;

import isib.demo.crossfit.Repository.EpreuveRepository;
import isib.demo.crossfit.Tables.Epreuve;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

/**
 *
 * @author aliou
 */
@Service
public class EpreuveService {
    
    
    private final EpreuveRepository epreuveRepository;
    
    //constructeur
    @Autowired
    public EpreuveService(EpreuveRepository epreuveRepository) {
        this.epreuveRepository = epreuveRepository;
    }
    
    //context de persistence
    @PersistenceContext
    private EntityManager em;

    
    //Get all Epreuve 
    public Iterable<Epreuve> GetFindAll() {

        Iterable<Epreuve> result = epreuveRepository.findAll();

        return result;
    }
        
   

    //update 
    public Epreuve UpdateEpreuve(Epreuve epreuve) {
        //verifier que le client existe dans la bd
        Epreuve c = em.find(Epreuve.class, epreuve.getNie());
        if (c != null) {
            
            //modifier
            return epreuveRepository.save(epreuve);
        } else {
            return null;
        }
    }

    //create
    public void CreateJury(Epreuve Epreuve) {

        epreuveRepository.save(Epreuve);
        System.out.println("inserted");
    }

      //delete    
    public void DeleteEpreuve(Integer NIE) {

        Epreuve c = em.find(Epreuve.class, NIE);
        try {
            System.out.println("======");
            System.out.println("nic: " + c.getNie().toString());
            if (c != null) {
                epreuveRepository.delete(c);
                System.out.println("======");
                System.out.println("competitions deleted");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    //nombre de competition crée
     public Long GetEpreuveCount() {

        return (Long) em.createNamedQuery("Epreuve.GetEpreuveCount").getSingleResult();
    }
     
     
     //obtenir l'objet Epreuve en encodant le nom 
    public Optional<Epreuve> GetEpreuvebyNom(String nom) {
        
        Epreuve c = em.createNamedQuery("Epreuve.GetEpreuvebyNom", Epreuve.class)
                .setParameter(2, nom)
                .getSingleResult();
        Optional<Epreuve> result = Optional.of(c);
        return result;
    }
 
    
    
 
  
}
