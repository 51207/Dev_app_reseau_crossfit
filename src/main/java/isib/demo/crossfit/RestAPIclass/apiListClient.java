/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isib.demo.crossfit.RestAPIclass;

import isib.demo.crossfit.OtherClass.NotationClientInscrit;
import isib.demo.crossfit.Tables.Test;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;


/**
 *
 * @author aliou
 */@Data
public class apiListClient implements Serializable{
    private List<ApiClient>list;

  
    
    public apiListClient(){
        list = new ArrayList<>();
       
    }
    
    public void setList2(ApiClient notationclient){
    
        this.list.add(notationclient);
    }
    
}

