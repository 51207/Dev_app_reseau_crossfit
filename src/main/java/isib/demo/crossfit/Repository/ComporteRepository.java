/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package isib.demo.crossfit.Repository;

import isib.demo.crossfit.Tables.Comporte;
import isib.demo.crossfit.Tables.Inscrit;
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
public interface ComporteRepository extends CrudRepository<Inscrit, Integer> {
   // public Comporte findByCncompetition();
  // public Comporte findByCnie(int cnie);
        @Query(value="select  c from Comporte c ")
        public Optional<List<Comporte>> GetAllComporte();
       
        @Query(value= "Select distinct  e.nEpreuve  FROM Inscrit i join  i.competition c  join c.epreuveCollection e   where c.nomcompetition=:nomcompetition AND i.inscritPK.idate=:dates")
        public Optional<List<String>> GetAllEpreuveByCompetition(@Param("nomcompetition")String nomcompetition , @Param("dates") String dates);
        
        @Query(value="Select c from  Comporte c where c.comportePK.CNCompetition=:idcompetition AND c.comportePK.CNIE=:idepreuve")
        public Comporte GetComporteByIDCompetition(@Param("idcompetition")Integer numerocompetition,@Param("idepreuve")Integer numeroEpreuve);
        
        
        @Query(value="DELETE FROM Comporte c" )
        public void DeleteAlllEpreuveComporte();
        
        @Query(value="DELETE  From Comporte c where  c.comportePK.CNCompetition=:idcompetition AND c.comportePK.CNIE=:idepreuve")
        public void Deletesingle(@Param("idcompetition")Integer numerocompetition,@Param("idepreuve")Integer numeroEpreuve);
        
       
        
        
    
        
    
  }
