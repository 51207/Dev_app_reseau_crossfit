/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package isib.demo.crossfit.Repository;

import isib.demo.crossfit.Tables.Clients;
import isib.demo.crossfit.Tables.Jury;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author aliou
 */
@Repository
public interface ClientsRepository extends CrudRepository<Clients, Integer> {
    
  public Long findClientsCount();
  public void findByLastAndFirstName(String nom, String prenom,String tel);
  
  //liste des jury qui ont jugé sa performence
 @Query(value="Select j.nomJury from Clients c join c.testCollection t join t.jury j where c.nom=:nom and t.testPK.tDates=:date")
  public List<String> GetListJury( @Param("nom")String nomclient , @Param("date") String date);
  
  
  @Query(value="Select c from Clients c where c.username=:username and c.passwordclient=:passwordclient")
  public Clients GetLogin(@Param("username") String username, @Param("passwordclient") String password);
  
    @Query(value="Select c from Clients c where c.username=:username")
  public Clients ForgotPassword(@Param("username") String username);
  
   @Query(value="Select c from Clients c where c.nic =:nic")
     public Clients getClientsByID(@Param("nic") Integer IDClient);
}
