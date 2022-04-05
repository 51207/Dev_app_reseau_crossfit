/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isib.demo.crossfit.service;

/**
 *
 * @author aliou
 */
public class InfomationEpreuve {
    private Integer nic;
    private String NomClient;

   
    private String epreuve;
    private int note ; 

    public InfomationEpreuve(Integer nic, String epreuve, int note,String nom) {
        this.nic = nic;
        this.epreuve = epreuve;
        this.note = note;
        this.NomClient = nom;
    }
 
    public String getNomClient() {
        return NomClient;
    }

    public void setNomClient(String NomClient) {
        this.NomClient = NomClient;
    }
    public Integer getNic() {
        return nic;
    }

    public void setNic(Integer nic) {
        this.nic = nic;
    }

    public String getEpreuve() {
        return epreuve;
    }

    public void setEpreuve(String epreuve) {
        this.epreuve = epreuve;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }
    
}
