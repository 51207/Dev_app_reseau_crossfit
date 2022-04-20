/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isib.demo.crossfit.service;

import isib.demo.crossfit.OtherClass.noteclass;
import isib.demo.crossfit.Repository.TestRepository;
import isib.demo.crossfit.Tables.Clients;
import isib.demo.crossfit.Tables.Competition;
import isib.demo.crossfit.Tables.Epreuve;
import isib.demo.crossfit.Tables.Jury;
import isib.demo.crossfit.Tables.Test;
import java.util.ArrayList;
import java.util.Iterator;
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

    
    //recuperer tout les test  à partir d'un id.
    public Optional<List<Test>> GetAllTestById(Integer id) {
       
        try{
        Optional<List<Test>> result=null;
         List<Test> s ;
         s= testRepository.getAllTestById(id);
         result = Optional.of(s);
        return result; 
        
       }
        catch(NullPointerException e){return null;}
    }
    
    
    
    //pour supprimer tous les test trouvée par rapport à un id 
    public  void DeleteAllTestSelectedById(Optional<List<Test>> list){
    
        if( !list.get().isEmpty()){
        
            try{
            
                for(var item : list.get()){
                        testRepository.delete(item);
                    
                }
                
            }
            catch(NullPointerException e){
            
                System.out.println(" error DeleteALLTestSelectedById");
            }
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

    //on recupere les notes de chaque client qui est inscrit à un tournoi et qui a effectué une epreuve
    public Optional<List<Object[]>> GetTestbyEpreuve(@Param("nomEpreuve")String nomEpreuves,@Param("dates") String date) {

        // TypedQuery<Object[]> query = em.createQuery(" select e ,k from Test t join t.epreuve e join e.competitionCollection c  join c.inscritCollection i join i.clients k where e.nEpreuve=:nomEpreuve and k.nom=:nomClient",Object[].class);
        List<Object[]> c = testRepository.GetTestbyEpreuve(nomEpreuves, date);
        Optional<List<Object[]>> result = Optional.of(c);

        return result;
    }
    
    //on recupere les notes d'un clients dans chacune des epreuves qu'il a effectué
    public List<Object[]> GetTestbyclientAndDates(@Param("nomClient") Integer idclients,@Param("dates") String date){
        
        List<Object[]> c = testRepository.GetTestbyclientAndDates( idclients, date);
        Optional<List<Object[]>> result = Optional.of(c);
        
        return c;
    }
    
      //condition pour faire en sorte qu'un membre de jury ne peut pas juger un client sur la meme epreuve et a la meme date plusieurs fois de suite
    public Test getAllTestbyAllParameter (String date, Integer idclients, Integer idepreuve, Integer idjury) {
        try {
            Test t = testRepository.getAllTestbyAllParameter(date, idclients, idepreuve, idjury);

            return t;
        } catch (NullPointerException e) {
            return null;
        }
    }
    
    
    public List<Test> getAllTestByDates(String  date){
    
        try{
        List<Test> list = testRepository.getAllTestByDates(date);
        if( list.isEmpty()){
            return null;
        }else{
            return list;
        }
        }catch(NullPointerException e ){
            return null;
        }
    
    }
    
    
    //methode à part qui me permettra d'avoir les deux notes de chaque client qui a effectué une epreuve
    public List<noteclass> getAllNote(int idclient, String dates) {
         //j'intancie la liste dans laquelle les données du clients
        List<noteclass> listnote = new ArrayList<noteclass>();

        //je recupere la liste d'objet
        List<Object[]> ListGetObject = this.GetTestbyclientAndDates(idclient, dates);
        if(ListGetObject != null){
       

        for (var item : ListGetObject) {
            Epreuve e = (Epreuve) item[0];
            Clients c = (Clients) item[1];
            Test b = (Test) item[2];

            noteclass noteclass = new noteclass();

            noteclass.setNom(c.getNom());
            noteclass.setNomepreuve(e.getNEpreuve());
            noteclass.setNote1(b.getNote());

            int count = 0;
            if (listnote.size() >= 1) {
                
                    for(int i = (listnote.size()-1) ; i >=0 ; i--){
                    noteclass classe= listnote.get(i);
                        if(classe.getNomepreuve()==noteclass.getNomepreuve()){
                    
                      
                        //si c'est le cas, alors j'ajoute la deuxieme note dans un attribut de noteclass
                        classe.setId(listnote.size());
                        classe.setNote2(noteclass.getNote1());
                        classe.setNotetotal((classe.getNote1() + classe.getNote2()) / 2);
                       //count permet de savoir si le meme client a une deuxieme note ,ducoup s'il a une une deuxième note , on l'ajoute pas dans la liste
                       //mais on rajoute juste sa deuxieme note dans l'objet en question
                        count++;
                     
                    }

                }

            }

            if (count == 0) {
                listnote.add(noteclass);
            }
        }

        return listnote;
    }else{
            noteclass note= new noteclass();
            note.setNom("");
            note.setNomepreuve("");
            note.setNote1(0);
            note.setNote2(0);
            note.setNotetotal(0);
            listnote.add(note);
            return listnote;
        
        }
    }

    
   public List<noteclass> getAllNotebyepreuve(String epreuve, String dates) {
         //j'intancie la liste dans laquelle les données du clients
        List<noteclass> listnote = new ArrayList<noteclass>();

        //je recupere la liste d'objet
        List<Object[]> ListGetObject = this.GetTestbyEpreuve(epreuve, dates).get();
        if(ListGetObject != null){
       

        for (var item : ListGetObject) {
            Epreuve e = (Epreuve) item[0];
            Clients c = (Clients) item[1];
            Test b = (Test) item[2];

            noteclass noteclass = new noteclass();

            noteclass.setNom(c.getNom());
            noteclass.setPrenom(c.getPrenom());
            noteclass.setNote1(b.getNote());

            int count = 0;
            if (listnote.size() >= 1) {
                
                    for(int i = (listnote.size()-1) ; i >=0 ; i--){
                        noteclass classe= listnote.get(i);
                        if(classe.getNom()==noteclass.getNom()){
                    
                      
                        //si c'est le cas, alors j'ajoute la deuxieme note dans un attribut de noteclass
                        classe.setId(listnote.size());
                        classe.setNote2(noteclass.getNote1());
                        classe.setNotetotal((classe.getNote1() + classe.getNote2()) / 2);
                       //count permet de savoir si le meme client a une deuxieme note ,ducoup s'il a une une deuxième note , on l'ajoute pas dans la liste
                       //mais on rajoute juste sa deuxieme note dans l'objet en question
                        count++;
                     
                    }

                }

            }

            if (count == 0) {
                listnote.add(noteclass);
            }
        }

        return listnote;
    }else{
            noteclass note= new noteclass();
            note.setNom("");
            note.setNomepreuve("");
            note.setNote1(0);
            note.setNote2(0);
            note.setNotetotal(0);
            listnote.add(note);
            return listnote;
        
        }
    }



}
