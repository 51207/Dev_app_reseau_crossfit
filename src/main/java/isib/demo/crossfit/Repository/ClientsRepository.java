/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package isib.demo.crossfit.Repository;

import isib.demo.crossfit.Tables.Clients;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author aliou
 */
@Repository
public interface ClientsRepository extends CrudRepository<Clients, Integer> {
    
// public void InsertClient();
 public void DeleteClient(Clients c , Integer nic);
 public void UpdateClientNamePrenom(Integer nic);
 
  public Long findClientsCount(EntityManager em);
  public void findByLastAndFirstName(EntityManager em, String nom, String prenom);
  
}
