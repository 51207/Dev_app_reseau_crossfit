/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isib.demo.crossfit.service;

import isib.demo.crossfit.Repository.JuryRepository;
import isib.demo.crossfit.Tables.Epreuve;
import isib.demo.crossfit.Tables.Jury;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

/**
 *
 * @author aliou
 */
@Service
public class JuryService {
    
    private final JuryRepository juryRepository;
    
    //constructeur
    @Autowired
    public JuryService(JuryRepository juryRepository) {
        this.juryRepository = juryRepository;
    }
    
    //context de persistence
    @PersistenceContext
    private EntityManager em;

    
    //Get all Epreuve 
    public Iterable<Jury> GetFindAll() {

        Iterable<Jury> result = juryRepository.findAll();

        return result;
    }
        
   

    //update 
    public Jury UpdateJury(Jury jury) {
        //verifier que le client existe dans la bd
        Jury c = em.find(Jury.class, jury.getNIJury());
        if (c != null) {
            
            //modifier
            return juryRepository.save(jury);
        } else {
            return null;
        }
    }

    //create
    public void CreateJury(Jury jury) {

        juryRepository.save(jury);
        System.out.println("inserted");
    }

    
    //nombre de competition crée
     public Long GetJuryCount() {

        return (Long) em.createNamedQuery("Jury.GetJuryCount").getSingleResult();
    }
     
     
     //obtenir l'objet jury en encodant le nom 
    public Jury GetJury(String nom,String prenom) {
        
        Jury c = em.createNamedQuery("Jury.GetJurybyNomPrenom", Jury.class)
                .setParameter(2, nom)
                .setParameter(3, prenom)
                .getSingleResult();

        if (c != null) {
            return c;
        } else {
            return null;
        }
    }
 
 
    //delete    
    public void DeleteJury(Integer NIE) {

        Jury c = em.find(Jury.class, NIE);
        try {
            System.out.println("======");
            System.out.println("nic: " + c.getNIJury().toString());
            if (c != null) {
                juryRepository.delete(c);
                System.out.println("======");
                System.out.println("competitions deleted");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    public List<String> getAllJury(){
        List<String> list = juryRepository.getAllJury();
        return list;
    }
    
    //recuperer l'objet jury pour enfin  avoir l'id  
    public Jury GetIDJurybyNomJury(@Param("nomJury") String nomJury) {

        try {

            Jury j = juryRepository.GetIDJurybyNomJury(nomJury);
            if (j != null) {
                return j;
            } else {
                return null;
            }
        } catch (NullPointerException e) {
            return null;
        }

    }
    
    //recuperer le jury 
     public Jury getIdJury(Integer IDJury){
     
      try {

            Jury j = juryRepository.getIdJury(IDJury);
            if (j != null) {
                return j;
            } else {
                return null;
            }
        } catch (NullPointerException e) {
            return null;
        }
     }
}
