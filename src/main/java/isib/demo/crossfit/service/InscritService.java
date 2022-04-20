/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isib.demo.crossfit.service;

import isib.demo.crossfit.Repository.InscritRepository;
import isib.demo.crossfit.Tables.Clients;
import isib.demo.crossfit.Tables.Competition;
import isib.demo.crossfit.Tables.Inscrit;
import isib.demo.crossfit.Tables.InscritPK;
import isib.demo.crossfit.Tables.Test;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

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
public class InscritService {

    private final InscritRepository inscritRepository;

    //constructeur
    @Autowired
    public InscritService(InscritRepository inscritRepository) {
        this.inscritRepository = inscritRepository;
    }

    //context de persistence
    @PersistenceContext
    private EntityManager em;

    //Get all inscrit 
    public Iterable<Inscrit> GetFindAll() {

        Iterable<Inscrit> result = inscritRepository.findAll();

        return result;
    }

    //create
    public Inscrit CreateInscription(Inscrit Inscrit) {

        return inscritRepository.save(Inscrit);

    }
    
     //delete    
    public void DeleteInscription(Inscrit inscrit, String date) {

        Inscrit c = em.createNamedQuery("Inscrit.GetInscritbyNomDateCompetition", Inscrit.class)
                .setParameter(1, inscrit.getInscritPK().getINCompetition())
                .setParameter(2, inscrit.getInscritPK().getINic())
                .setParameter(3, inscrit.getInscritPK().getIdate())
                .getSingleResult();
        try {

            if (c != null) {
                inscritRepository.delete(c);
                System.out.println("======");
                System.out.println("competitions deleted");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    //DELETE
     public void DeleteInscrit(Inscrit inscrit) {

                inscritRepository.delete(inscrit);        
    }
    
    //update 
    public Inscrit UpdateInscrit(Inscrit inscrit) {
        //modifier

        Inscrit c = em.find(Inscrit.class, inscrit.getClients());
        if (c != null) {
            return inscritRepository.save(c);
        } else {
            System.out.println("pas encore inscrit");
            return null;
        }
    }

    
    //recuperer tous les objets inscrit dont le nic = id
     public Optional<List<Inscrit>> GetAllInscritById(Integer id) {
         try{
        Optional<List<Inscrit>> result=null;
         List<Inscrit> s ;
         s= inscritRepository.getAllInscritById(id);
         result = Optional.of(s);
        return result; 
        
       }
        catch(NullPointerException e){return null;}
    }
    
        //pour supprimer tous les test trouvée par rapport à un id 
    public  void DeleteAllInscritSelectedById(Optional<List<Inscrit>> list){
    
        if( !list.get().isEmpty()){
        
            try{
            
                for(var item : list.get()){
                        inscritRepository.delete(item);
                    
                }
                
            }catch(NullPointerException e){}
        }
    
    }
     
     
   //nouvelle inscription
    public Inscrit InsertToCompetition(Competition compet,Clients client ,String date) {

        Inscrit inscription = new Inscrit();
        inscription.setClients(client);
        inscription.setCompetition(compet);
        inscription.getInscritPK().setINCompetition(compet.getNCompetition());
        inscription.getInscritPK().setINic(client.getNic());
        inscription.getInscritPK().setIdate(date);
     
        return inscription;
    }
    
   
   
    
    
    
    //nombre de personne inscrite par date de competition
    public Long GetInscritCount(String date) {

        return (Long) em.createNamedQuery("Inscrit.GetInscritCount").setParameter(3, date).getSingleResult();
    }

    
    
    //liste personnes inscrite par date competition 
    public List<Inscrit> GetInscritByDate(String date) {
        List<Inscrit> c = em.createNamedQuery("Inscrit.findByIdate", Inscrit.class).setParameter(3, date).getResultList();
        return c;
    }

    
    
   
    
    
    //liste des personnes qui se sont inscrit dans une competition
    public List<Inscrit> GetInscritByIdCompetition(Inscrit inscrit) {
        List<Inscrit> c = em.createNamedQuery("Inscrit.findByINCompetition", Inscrit.class).setParameter(1, inscrit.getInscritPK().getINCompetition()).getResultList();
        return c;
    }
    
    
    

    //obtenir l'objet Inscrit  en encodant le nom DE LA COMPET
    public Inscrit GetInscrit(Competition competition, Clients client, String date) {

        Inscrit c = em.createNamedQuery("Inscrit.GetInscritbyNomDateCompetition", Inscrit.class)
                .setParameter(1, competition.getNCompetition())
                .setParameter(2, client.getNom())
                .setParameter(3, date)
                .getSingleResult();

        if (c != null) {
            return c;
        } else {
            return null;
        }
    }
    
    
    //obtenir tous les id des clients qui participent à cette competition à cette date ci
    public List<Integer> getAllInscritByDate(String dates) {

        try {
            List<Integer> c = inscritRepository.getAllInscritByDate(dates);
            return c;
        } catch (NullPointerException e) {
            return null;
        }

    }

   
    
    
   
}
