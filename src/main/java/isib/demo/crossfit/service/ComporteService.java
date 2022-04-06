/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isib.demo.crossfit.service;

import isib.demo.crossfit.Repository.ComporteRepository;
import isib.demo.crossfit.Tables.Clients;
import isib.demo.crossfit.Tables.Competition;
import isib.demo.crossfit.Tables.Comporte;
import isib.demo.crossfit.Tables.Epreuve;
import isib.demo.crossfit.Tables.Inscrit;
import static java.nio.file.Files.delete;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author aliou
 */
@Service
public class ComporteService {
   
    
   private final ComporteRepository comporteRepository;
    
    //constructeur
    @Autowired
    public ComporteService(ComporteRepository comporteRepository) {
        this.comporteRepository = comporteRepository;
    }

    //context de persistence
    @PersistenceContext
    private EntityManager em;

    //Get all competition 
   public  Optional<List<Comporte>> GetAllComporte(){
   
        Optional<List<Comporte>> result = comporteRepository.GetAllComporte();
  
        return result;
   }
    
   //recuperer toutes les epreuves qui sont dans une competition 
    public Optional<List<String>> GetAllEpreuveByCompetition(String nomcompetition, String dates) {

        Optional< List<String>> result = comporteRepository.GetAllEpreuveByCompetition(nomcompetition, dates);
        return result;
    }
    
    
   
    
     //delete un element dans la table  comporte
    public void DeleteSingleEpreuveByCompetition(Integer NCNIE) {

        Comporte c = em.find(Comporte.class, NCNIE);
        try {
           
            if (c != null) {
                comporteRepository.Deletesingle(NCNIE);
                System.out.println("======");
                System.out.println("competitions deleted");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    
    //delete all comporte
    public void DeleteAllEpreuveByCompetition() {

        comporteRepository.DeleteAlllEpreuveComporte();
    }
}