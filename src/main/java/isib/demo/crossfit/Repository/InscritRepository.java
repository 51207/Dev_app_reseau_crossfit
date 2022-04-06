/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package isib.demo.crossfit.Repository;

import isib.demo.crossfit.Tables.Clients;
import isib.demo.crossfit.Tables.Epreuve;
import isib.demo.crossfit.Tables.Inscrit;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author aliou
 */
public interface InscritRepository extends CrudRepository<Inscrit, Integer> {
    
    
    public Inscrit GetInscritbyNomDateCompetition();
    public Long GetInscritCount();
}
