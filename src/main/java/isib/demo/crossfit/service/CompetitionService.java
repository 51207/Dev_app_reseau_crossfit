/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isib.demo.crossfit.service;

import isib.demo.crossfit.Repository.ClientsRepository;
import isib.demo.crossfit.Repository.CompetitionRepository;
import isib.demo.crossfit.Tables.Clients;
import isib.demo.crossfit.Tables.Competition;
import java.util.ArrayList;
import java.util.List;
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
    public Competition GetIdCompetition(String nomcompetition) {

        Competition c = em.createNamedQuery("Competition.findByNomcompetition", Competition.class)
                .setParameter(4, nomcompetition).getSingleResult();

        if (c != null) {
            return c;
        } else {
            return null;
        }
    }

    //get all name o competition
    public List<String> GetAllNameofCompetition() {
        List<String> result = new ArrayList<>();
        for (Competition item : GetFindAll()) {
            result.add(item.getNomcompetition());
        }
        return result;
    }

}
