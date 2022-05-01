/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package isib.demo.crossfit.Repository;

import isib.demo.crossfit.Tables.Clients;
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
    
   @Query(value="select distinct c.nomcompetition from Competition c ")
   public Optional<List<String>> GetAllNameofCompetition();
   
   @Query(value="select distinct c from Competition c where  c.nomcompetition=:nomcompetition")
   public Optional<Competition> GetCompetitionByName(@Param("nomcompetition")String nomcompetition);
   
    @Query(value="Select c from Competition c where c.user=:user and c.password=:password")
  public Competition GetLogin(@Param("user") String username, @Param("password") String password);
  
  
   @Query(value="Select c from Competition c where c.user=:user")
  public Competition ForgotPassword(@Param("user") String username);
  
     @Query(value="Select c.user from Competition c where c.nomcompetition=:nomcompetition")
  public String GetUserByNameOFcompetition(@Param("nomcompetition") String nomcompetition);
  
   @Query(value="Select c from Competition c where c.nomcompetition=:nomcompetition")
  public Competition ForgotNameOfCompetition(@Param("nomcompetition") String nomcompetition);
  
  
}

    