/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package isib.demo.crossfit.Repository;

import isib.demo.crossfit.Tables.Sites;
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
public interface SitesRepository extends CrudRepository<Sites, Integer> {
    public Long GetSitesCount();
    //public Sites GetSitesbyCp();
    @Query(value="Select distinct s FROM Sites s join s.dNCompetition  c join  c.inscritCollection i where i.inscritPK.idate =:date")
    public Sites GetAdressOfCompetition(@Param("date")String date);
}
