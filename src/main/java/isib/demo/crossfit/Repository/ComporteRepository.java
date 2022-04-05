/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package isib.demo.crossfit.Repository;

import isib.demo.crossfit.Tables.Comporte;
import isib.demo.crossfit.Tables.Inscrit;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author aliou
 */
  @Repository
public interface ComporteRepository extends CrudRepository<Inscrit, Integer> {
   // public Comporte findByCncompetition();
  // public Comporte findByCnie(int cnie);
}
