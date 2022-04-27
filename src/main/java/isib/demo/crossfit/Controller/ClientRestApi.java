/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isib.demo.crossfit.Controller;

import isib.demo.crossfit.RestAPIclass.ApiClient;
import isib.demo.crossfit.RestAPIclass.apiListTest;
import isib.demo.crossfit.Tables.Clients;
import isib.demo.crossfit.Tables.Test;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import org.springframework.web.client.RestTemplate;

/**
 *
 * @author aliou
 */
@Component
public class ClientRestApi {

    public ClientRestApi() {
    }

    RestTemplate rst = new RestTemplate();

    public apiListTest GetAlltESTbyDate(String date) {
        System.out.println("************************RestApi****************************");
        //je veux recevoir une information du service Rest (la liste de tous les test)
        apiListTest api = rst.getForObject("http://localhost:8081/apijson/listTest/" + date, apiListTest.class);

        if (api == null) {
            return null;
        } else {

            for (var item : api.getList()) {

                System.out.println(item.toString());
            }
            System.out.println("****************************************************");
            return api;
        }
    }

    public void UpdateClientServiceRest(Clients s, String PreviousUsername) {

        Clients c = rst.getForObject("http://localhost:8081/apijson/UpdateClient/" + PreviousUsername, Clients.class);

        ApiClient api = new ApiClient();
        api.setNic(c.getNic());
        api.setNom(s.getNom());
        api.setPrenom(s.getPrenom());
        api.setRue(s.getRue());
        api.setNumero(s.getNumero());
        api.setCp(s.getCp());
        api.setCommune(s.getCommune());
        api.setTel(s.getTel());
        api.setUsername(s.getUsername());
        api.setPassword(s.getPasswordclient());

        rst.put("http://localhost:8081/apijson/UpdateClients", api, ApiClient.class);

    }

    public void DeleClientServiceRest(String PreviousUsername) {

       /* Clients c = rst.getForObject("http://localhost:8081/apijson/DeleteClient/" + PreviousUsername, Clients.class);
        
        ApiClient api = new ApiClient();
        api.setNic(c.getNic());
        api.setNom(c.getNom());
        api.setPrenom(c.getPrenom());
        api.setRue(c.getRue());
        api.setNumero(c.getNumero());
        api.setCp(c.getCp());
        api.setCommune(c.getCommune());
        api.setTel(c.getTel());
        api.setUsername(c.getUsername());
        api.setPassword(c.getPasswordclient());*/

        rst.g ("http://localhost:8081/apijson/DeleteClient/"+PreviousUsername,String.class);

    }
}
