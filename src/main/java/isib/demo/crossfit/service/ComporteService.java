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
   /* public void CreateComporte(Competition ncompetition, Epreuve nic) {
        Comporte comporte = new Comporte(ncompetition, nic);
    }*/
    
   
}
