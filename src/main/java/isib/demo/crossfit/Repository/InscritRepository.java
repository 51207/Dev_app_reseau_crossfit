/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package isib.demo.crossfit.Repository;

import isib.demo.crossfit.Tables.Clients;
import isib.demo.crossfit.Tables.Epreuve;
import isib.demo.crossfit.Tables.Inscrit;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author aliou
 */
public interface InscritRepository extends CrudRepository<Inscrit, Integer> {
    
    
    public Inscrit GetInscritbyNomDateCompetition();
    public Long GetInscritCount();
    
    @Query(value="select i from Inscrit i where i.inscritPK.iNic=:tnic")
    public List<Inscrit> getAllInscritById(@Param("tnic") Integer idclients);
    
    
    @Query(value="select i.inscritPK.iNic from Inscrit i where i.inscritPK.idate=:idate")
    public List<Integer> getAllInscritByDate(@Param("idate") String dates);
    
}
