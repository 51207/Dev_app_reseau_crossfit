/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isib.demo.crossfit.service;

import isib.demo.crossfit.Repository.TestRepository;
import isib.demo.crossfit.Tables.Clients;
import isib.demo.crossfit.Tables.Competition;
import isib.demo.crossfit.Tables.Epreuve;
import isib.demo.crossfit.Tables.Jury;
import isib.demo.crossfit.Tables.Test;
import java.util.Iterator;
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
public class testService {

    private final TestRepository testRepository;

    //constructeur
    @Autowired
    public testService(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    //context de persistence
    @PersistenceContext
    private EntityManager em;

    //Get all test 
    public Iterable<Test> GetFindAll() {

        Iterable<Test> result = testRepository.findAll();

        return result;
    }

     //create
    public Test CreateNewTest(Test test) {

        return testRepository.save(test);
    }
    
    
    //update 
    public Test UpdateNote(Test test) {
        //modifier
        Test c = em.find(Test.class, test);
        if (c != null) {
            return testRepository.save(c);
        } else {
            System.out.println("pas encore inscrit");
            return null;
        }
    }


    //delete    
    public void DeleteTest(Test test) {

        Test c = em.createNamedQuery("Test.GetDeletebyNomEpreuveDateJury", Test.class)
                .setParameter(1, test.getTestPK().getTDates())
                .setParameter(2, test.getTestPK().getTnic())
                .setParameter(3, test.getTestPK().getTJury())
                .setParameter(4, test.getTestPK().getTnie())
                .getSingleResult();
        try {

            if (c != null) {
                testRepository.delete(c);
                System.out.println("======");
                System.out.println("competitions deleted");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    //insert test 
    public Test insertTest(String date, Clients client,Jury jury ,Epreuve epreuve,int note){
    
        Test test = new Test();
        test.setNote(note);
        test.getTestPK().setTDates(date);
        test.getTestPK().setTJury(jury.getNIJury());
        test.getTestPK().setTnic(client.getNic());
        test.getTestPK().setTnie(epreuve.getNie());
     
    return test;
    }
    
    //client qui ont une note > 15
    public List<Test> GetClientSupNote(String date, String epreuve, String note) {

        List<Test> c = em.createNamedQuery("Test.GetClientSupNote")
                .setParameter(1, date)
                .setParameter(4, epreuve)
                .setParameter(5, note).getResultList();
        return c;
    }

    
    //liste des clients  qui ont effectués une epreuve à une certaine date 
    //on recupere donc le client , sa note , et le membre du jury qui lui a donné une note
    public List<Test> GetClientbyEpreuve(String date, Epreuve epreuve) {

        List<Test> c = em.createNamedQuery("Test.GetClientEpreuve")
                .setParameter(1, date)
                .setParameter(4, epreuve.getNie())
                .getResultList();
        return c;
    }

    public Optional<List<Test>>GetTestbyEpreuve(String nomEpreuve, String nom){
    
    //TypedQuery<Test> query = em.createQuery("select k.nom ,t.note, e.nEpreuve, c.nomcompetition FROM   Competition c  join  c.inscritCollection i  join i.clients k join k.testCollection t join t.epreuve e  where  e.nEpreuve'"+nomEpreuve+"' and k.nom='"+nom+"'",Test.class);
    //List<Test> c  = query.getResultList();
    // List<Test> = testRepository.GetClientEpreuve(nomEpreuve, nom);
    /*  for(var item : c){
        System.out.println("===============>"+item);  
    }
      
    Optional<List<Test>> result = Optional.of(c);
    */return null;
            }
}
