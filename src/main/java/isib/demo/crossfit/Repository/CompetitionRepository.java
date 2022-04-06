/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package isib.demo.crossfit.Repository;

import isib.demo.crossfit.Tables.Competition;
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
public interface CompetitionRepository extends CrudRepository<Competition, Integer> {
    
    public Long GetCompetitionCount();
    public Competition GetCompetitionbyNameCompetition(String nom ,String prenom);
  
    
    @Query(value="SELECT distinct c FROM Inscrit i join i.competition c WHERE c.nomcompetition =:nomcompetition   and i.inscritPK.idate = :dates")
   public Competition GetIDbyCompetition( @Param("nomcompetition")String nomcompetition , @Param("dates") String dates);
   
    @Query(value="select distinct i.inscritPK.idate from Inscrit i ")
   public Optional<List<String>> GetAllDateCompetition();
    
   @Query(value="select  c.nomcompetition from Competition c ")
   public Optional<List<String>> GetAllNameofCompetition();
}
