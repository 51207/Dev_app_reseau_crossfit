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
import org.springframework.stereotype.Repository;

/**
 *
 * @author aliou
 */
@Repository
public interface EpreuveRepository extends CrudRepository<Epreuve, Integer> {
    
     public Long GetEpreuveCount();
    public Optional<Epreuve> GetEpreuvebyNom(String nomEpreuve);
   
   // public Optional<List<String>> Getepreuve();
}
