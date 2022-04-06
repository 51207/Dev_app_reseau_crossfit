/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package isib.demo.crossfit.Repository;

import isib.demo.crossfit.Tables.Competition;
import isib.demo.crossfit.Tables.Epreuve;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author aliou
 */
@Repository
public interface EpreuveRepository extends CrudRepository<Epreuve, Integer> {
    
     public Long GetEpreuveCount();
    public Optional<Epreuve> GetEpreuvebyNom(String nomEpreuve);
   
    //recuperer la note , l'epreuve , le jury qui a donné la note
    //select k.nom,e.nEpreuve,t.note,t.testPK.tDates
    @Query(value="select e,t,i from Competition c   join c.inscritCollection i join i.clients k join  k.testCollection  t join t.epreuve e where e.nEpreuve=:epreuve and k.nom=:nomclient  and t.testPK.tDates=:dates")
     public List<Object[]> GetNoteepreuve(@Param("nomclient")String nomclient ,@Param("epreuve")String epreuve ,@Param("dates") String dates);
     
     
     //select c.nom, n.nomJury, t.note,t.testPK.tDates
       @Query(value="select c, n, t from  Clients c join c.testCollection t join  t.jury n Where t.testPK.tnie=:Idepreuve and c.nom=:nomclient and t.testPK.tDates=:dates") 
     public List<Object[]> GetJuryWhoJudgeOneEpreuve(@Param("nomclient")String nomclient , @Param("Idepreuve") Integer Idepreuve,@Param("dates") String date);
        
}
