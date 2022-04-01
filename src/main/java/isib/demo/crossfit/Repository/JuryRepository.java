/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package isib.demo.crossfit.Repository;

import isib.demo.crossfit.Tables.Jury;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author aliou
 */
public interface JuryRepository extends CrudRepository<Jury, Integer> {
    
}
