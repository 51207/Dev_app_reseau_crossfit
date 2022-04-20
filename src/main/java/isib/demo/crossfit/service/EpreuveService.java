/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isib.demo.crossfit.service;

import isib.demo.crossfit.Repository.EpreuveRepository;
import isib.demo.crossfit.Tables.Epreuve;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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
    public List<Epreuve> GetFindAll() {

        List<Epreuve> result = (List<Epreuve>) epreuveRepository.findAll();

        return result;
    }
    
    public List<String> getalldataonEpreuve(){
    
         List<String> result =  epreuveRepository.getalldataonEpreuve();
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

    //obtenir l'objet Epreuve en encodant le nom de l'epreuve
    public Optional<Epreuve> GetEpreuvebyNom(String nom) {

        Epreuve c = em.createNamedQuery("Epreuve.GetEpreuvebyNom", Epreuve.class)
                .setParameter(2, nom)
                .getSingleResult();
        Optional<Epreuve> result = Optional.of(c);
        return result;
    }

    //je recupere le nom du client , le jury qui a jugé sa performance , la note, et la date de la competition
    //Idepreuve signifie que je dois mettre l'id de l'epreuve,du coup je prefere utiliser la methode juste en haut qui pourra me renvoyer l'id
    public Optional<List<Object[]>> GetJuryWhoJudgeOneEpreuve(String nomclient, Integer Idepreuve, String date) {

        List<Object[]> c = epreuveRepository.GetJuryWhoJudgeOneEpreuve(nomclient, Idepreuve, date);
        Optional< List<Object[]>> result = Optional.of(c);
        return result;
    }

    //obtenir la note de l'eleve , la date du test , le jury qui a noté
    public Optional<ArrayList> GetNoteepreuve(String nom, String nompreuve, int IdEpreuve, String dates) {

        List<Object[]> c = epreuveRepository.GetNoteepreuve(nom, nompreuve, dates);
        List<Object[]> d = epreuveRepository.GetJuryWhoJudgeOneEpreuve(nom, IdEpreuve, dates);

        ArrayList p = new ArrayList();
        p.add(c);
        p.add(d);
        Optional result = Optional.of(p);

        return result;
    }
    
    //recupere l'id de l'epreuve en donnant le nom 
    public Epreuve GetEpreuvebyNomEpreuve(String nomEpreuve) {
        try {
            Epreuve e = epreuveRepository.GetEpreuvebyNomEpreuve(nomEpreuve);
            if (e != null) {
                return e;
            } else {
                return null;

            }

        } catch (NullPointerException e) {
            return null;
        }
    }
    
    public Epreuve FindEpreuve(@Param("nie") Integer nie){
    
     try {
            Epreuve e = epreuveRepository.FindEpreuve(nie);
            if (e != null) {
                return e;
            } else {
                return null;

            }

        } catch (NullPointerException e) {
            return null;
        }
    
    }

}
