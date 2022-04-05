/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isib.demo.crossfit.service;

import java.util.List;

/**
 *
 * @author aliou
 */
public class InfoStockage {
      private List<String> em ; 
      private List<InfomationEpreuve> ep ; 
    public InfoStockage() {
    
    }
    
    
    
    public void ClearEp() {
         this.ep.clear();
    }

    public void setAddEp(InfomationEpreuve info) {
         this.ep.add(info);
    }
    
}
