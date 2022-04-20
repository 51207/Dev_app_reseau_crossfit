/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package isib.demo.crossfit.Repository;


import isib.demo.crossfit.Tables.Clients;
import isib.demo.crossfit.Tables.Test;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author aliou
 */
public interface TestRepository  extends CrudRepository<Test, String> {
    
    public Test GetClientSupNote(String date ,String epreuve ,String note);
     public Test GetClientEpreuve(String date , String epreuve);
     public Test GetDeletebyNomEpreuveDateJury();
     public Clients GetClientsByDate();
     
    // @Query(value=" select  e ,k, t from Test t join t.epreuve e join e.competitionCollection c  join c.inscritCollection i join i.clients k where e.nEpreuve=:nomEpreuve and k.nom=:nomClient and t.testPK.tDates=:dates")
      
     @Query(value="select  e ,k, t from Competition c join c.inscritCollection i join i.clients k join k.testCollection t join t.epreuve e where e.nEpreuve=:nomEpreuve  and t.testPK.tDates=:dates")     
     public List<Object[]> GetTestbyEpreuve(@Param("nomEpreuve")String nomEpreuves ,@Param("dates") String date);
    
       @Query(value="select  e ,k, t from Competition c join c.inscritCollection i join i.clients k join k.testCollection t join t.epreuve e where  k.nic=:idClient and t.testPK.tDates=:dates")     
       public List<Object[]> GetTestbyclientAndDates(@Param("idClient") Integer idclients,@Param("dates") String date);
    
       @Query(value=" select t from Test t where t.testPK.tnic = :tnic")
       public List<Test> getAllTestById(@Param("tnic") Integer idclients);
   
       //condition pour faire en sorte qu'un membre de jury ne peut pas juger un client sur la meme epreuve et a la meme date plusieurs fois de suite
        @Query(value=" select t from Test t where t.testPK.tDates=:dates and t.testPK.tnic = :tnic and t.testPK.tnie=:tnie and t.testPK.tJury = :tJury")
       public Test getAllTestbyAllParameter(@Param("dates") String  date,@Param("tnic") Integer idclients,@Param("tnie") Integer idepreuve,@Param("tJury") Integer idjury);
       
       @Query(value="select t from Test t where t.testPK.tDates=:dates")
       public List<Test> getAllTestByDates(@Param("dates") String  date);
}
