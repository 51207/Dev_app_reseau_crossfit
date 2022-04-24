/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isib.demo.crossfit.Controller;

import isib.demo.crossfit.RestAPIclass.apiListTest;
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
    
    public ClientRestApi(){}

    RestTemplate rst = new RestTemplate();
 
    
 
    public apiListTest GetAlltESTbyDate(String date) {
        System.out.println("************************RestApi****************************");
        
        apiListTest api = rst.getForObject("http://localhost:8081/apijson/listTest/"+date, apiListTest.class);

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
}
