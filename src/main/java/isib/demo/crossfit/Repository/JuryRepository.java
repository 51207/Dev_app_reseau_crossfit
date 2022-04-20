/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package isib.demo.crossfit.Repository;

import isib.demo.crossfit.Tables.Epreuve;
import isib.demo.crossfit.Tables.Jury;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author aliou
 */
@Repository
public interface JuryRepository extends CrudRepository<Jury, Integer> {
     public Long GetJuryCount();
    public Jury GetJurybyNomPrenom();
    
    @Query(value="Select j.nomJury from Jury j ")
     public List<String> getAllJury();
     
       @Query(value="Select j from Jury j where j.nIJury =:nIJury")
     public Jury getIdJury(@Param("nIJury") Integer IDJury);
     
     
     //recuperer l'objet jury
     @Query(value="select j from Jury j where  j.nomJury=:nomJury ")
      public Jury GetIDJurybyNomJury(@Param("nomJury") String nomJury);
    
}
