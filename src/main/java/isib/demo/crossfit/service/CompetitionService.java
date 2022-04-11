/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isib.demo.crossfit.service;

import isib.demo.crossfit.Repository.ClientsRepository;
import isib.demo.crossfit.Repository.CompetitionRepository;
import isib.demo.crossfit.Tables.Clients;
import isib.demo.crossfit.Tables.Competition;
import isib.demo.crossfit.Tables.Inscrit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author aliou
 */
@Service
public class CompetitionService {

    private final CompetitionRepository competitionRepository;

    //constructeur
    @Autowired
    public CompetitionService(CompetitionRepository competitionRepository) {
        this.competitionRepository = competitionRepository;
    }

    //context de persistence
    @PersistenceContext
    private EntityManager em;

    //Get all competition 
    public Iterable<Competition> GetFindAll() {

        Iterable<Competition> result = competitionRepository.findAll();

        return result;
    }

    //create
    public void CreateCompetition(Competition competition) {

        competitionRepository.save(competition);
        System.out.println("inserted");
    }

    //delete    
    public void DeleteCompetition(Integer Ncompetition) {

        Competition c = em.find(Competition.class, Ncompetition);
        try {
            System.out.println("======");
            System.out.println("nic: " + c.getNCompetition().toString());
            if (c != null) {
                competitionRepository.delete(c);
                System.out.println("======");
                System.out.println("competitions deleted");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //update 
    public Competition UpdateCompetition(Competition competition) {
        //verifier que le client existe dans la bd
        Competition c = em.find(Competition.class, competition.getNCompetition());
        if (c != null) {

            //modifier
            return competitionRepository.save(competition);
        } else {
            return null;
        }
    }

    //nombre de competition crée
    public Long GetCompetitionCount() {

        return (Long) em.createNamedQuery("Competition.GetCompetitionCount").getSingleResult();
    }

    
    
    //obtenir l'objet (ou l'id) competition en encodant le nom de la competition
    public  Optional<Competition> GetObjectCompetition(String nomcompetition) {

        Competition c = em.createNamedQuery("Competition.findByNomcompetition", Competition.class)
                        .setParameter(4, nomcompetition).getSingleResult();

        Optional<Competition> result = Optional.of(c);
        return result;
    }
    
     //obtenir l'objet  competition en encodant son id de la competition
    public  Optional<Competition> GetCompetitionById(Integer ncompetition) {

        Competition c = em.createNamedQuery("Competition.findByNCompetition", Competition.class)
                        .setParameter(1, ncompetition).getSingleResult();

        Optional<Competition> result = Optional.of(c);
        return result;
    }
    
      //obtenir l'objet competition en encodant le nom de la competition
   
    public Optional<Competition> GetCompetitionByName(String nomcompetition){
        Optional<Competition> c = competitionRepository.GetCompetitionByName(nomcompetition);

       
        return c;
    }
    
    
    
    //je veux trouver toutes les competitions qui ont comme nom nomcompetition et qui commence à la date : dates 
    public Optional<Integer> GetidCompetition(String nomcompetition, String dates) {
         Optional<Integer> result = Optional.of(0);
        try{
            Competition c =competitionRepository.GetIDbyCompetition(nomcompetition,dates);
            
            
       result = Optional.of(c.getNCompetition());
        return result;
        }catch(NullPointerException e){
           
           return result;
        }
      

    }
    
    
    
    
    //trouver toutes les dates de competition
    public Optional<List<String>> GetAllDateOfCompetition() {
        Optional<List<String>> result = competitionRepository.GetAllDateCompetition();
        return result;
    }

    
    
    //trouver toutes les dates de competition
    public  Optional<List<String>> GetAllNameofCompetition() {
        Optional<List<String>> result = competitionRepository.GetAllNameofCompetition();
  
       return result;
    }

}
