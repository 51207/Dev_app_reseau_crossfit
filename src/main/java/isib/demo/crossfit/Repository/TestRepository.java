/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package isib.demo.crossfit.Repository;


import isib.demo.crossfit.Tables.Clients;
import isib.demo.crossfit.Tables.Test;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author aliou
 */
public interface TestRepository  extends CrudRepository<Test, String> {
    
    public Test GetClientSupNote(String date ,String epreuve ,String note);
     public Test GetClientEpreuve(String date , String epreuve);
     public Test GetDeletebyNomEpreuveDateJury();
     public Clients GetClientsByDate();
}
