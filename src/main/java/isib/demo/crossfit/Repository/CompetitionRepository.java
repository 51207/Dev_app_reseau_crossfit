/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package isib.demo.crossfit.Repository;

import isib.demo.crossfit.Tables.Competition;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author aliou
 */
@Repository
public interface CompetitionRepository extends CrudRepository<Competition, Integer> {
    
    public Long GetCompetitionCount();
    public Competition GetCompetitionbyNameCompetition();
    
}
