/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isib.demo.crossfit.RestAPIclass;

import lombok.Data;

/**
 *
 * @author aliou
 */
@Data
public class ApiClient {

    private Integer nic;
    private String nom;
    private String prenom;
    private String Rue;
    private String Numero;
    private String cp;
    private String commune;
    private String tel;
    private String username;
    private String password;

    public ApiClient() {
    }

}
