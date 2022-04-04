/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isib.demo.crossfit.service;

import isib.demo.crossfit.Repository.SitesRepository;
import isib.demo.crossfit.Tables.Sites;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author aliou
 */
@Service

public class SitesServices {
    
    
        
    private final SitesRepository sitesRepository;
    
    //constructeur
    @Autowired
    public SitesServices(SitesRepository sitesRepository) {
        this.sitesRepository = sitesRepository;
    }
    
    //context de persistence
    @PersistenceContext
    private EntityManager em;

    
    //Get all Epreuve 
    public Iterable<Sites> GetFindAll() {

        Iterable<Sites> result = sitesRepository.findAll();

        return result;
    }
        
   

    //update 
    public Sites UpdateSites(Sites site) {
        //verifier que le client existe dans la bd
        Sites c = em.find(Sites.class, site.getNSites());
        if (c != null) {
            
            //modifier
            return sitesRepository.save(site);
        } else {
            return null;
        }
    }

    //create
    public void CreateSites(Sites site) {

        sitesRepository.save(site);
        System.out.println("inserted");
    }

    
    //nombre de competition crée
     public Long GetSitesCount() {

        return (Long) em.createNamedQuery("Sites.GetSitesCount").getSingleResult();
    }
     
     
     //obtenir l'objet Epreuve en encodant le nom 
    public Sites GetSitesbyCp(String cp) {
        
        Sites c = em.createNamedQuery("Sites.findByCPSites", Sites.class)
                .setParameter(2, cp)
                .getSingleResult();

        if (c != null) {
            return c;
        } else {
            return null;
        }
    }
 
 
    //delete    
    public void DeleteSites(Integer NIE) {

        Sites c = em.find(Sites.class, NIE);
        try {
            System.out.println("======");
            System.out.println("nic: " + c.getNSites().toString());
            if (c != null) {
                sitesRepository.delete(c);
                System.out.println("======");
                System.out.println("competitions deleted");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
