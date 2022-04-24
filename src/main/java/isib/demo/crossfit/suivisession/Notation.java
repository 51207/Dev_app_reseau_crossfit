/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isib.demo.crossfit.suivisession;

import isib.demo.crossfit.Tables.Clients;
import isib.demo.crossfit.Tables.Epreuve;
import isib.demo.crossfit.Tables.Jury;
import lombok.Data;

/**
 *
 * @author aliou
 */

@Data
public class Notation {
  
    private Clients nic;
    private Epreuve nec;
    private Jury nIJury;
    private Integer note;
    public Notation(){}

}
