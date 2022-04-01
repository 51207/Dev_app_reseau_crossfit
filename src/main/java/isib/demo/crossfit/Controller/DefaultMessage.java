/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package isib.demo.crossfit.Controller;


import isib.demo.crossfit.OtherClass.Message;
import java.util.ArrayList;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author aliou
 */
@Controller
public class DefaultMessage {
    
    ArrayList<Message> list = new ArrayList<>();
     ArrayList<Message> list1 = new ArrayList<>();
    
    @GetMapping("/**")
    public String page(Model model) {
        Message  msg = new Message("Aliou diallo","Tournoi Crossit");
        list1.add(msg);
        model.addAttribute("usermsg",list1);
        //model est un conteneur qui va stocker sous le nom usermsg une liste 
        //à la page html , spring va scanner le conteneur (model) et verifiera s'il existe un attribut qui se nomme usermsg qu'on peut reutiliser
        //et là on peut le reutiliser
        return "defaults";
    }
    
     @GetMapping("/msg")
    public String showmessage(Model model) {
        
        model.addAttribute("usermsg2",list);
        model.addAttribute("newMessage",new Message());
        //newMessage  : cc'est l'objet qu'on va créer avec le formulaire (cad les differents paramètre qu'on a encodéqui forme un objet nommé ici newMessage )
        // cet attribut correspond à un = > new Message(); 
        return "message";
    }
     @PostMapping("/msg")
    public String postmessage(@ModelAttribute Message newmsg)  {
        //ModelAttribute Message newmsg : c'est l'object qui va contenir les données du formulaire et qui est de type Message 
        //cad on recupere cet attribut
        list.add(newmsg);
         //comme on veut afficher sur la meme page le message qui correspond au GetMapping en haut ,on fait redirect:msg
        // return "redirect:msg"; 
        
        //soit on fait un redirect vers le getmapping urlmsg qui est une nouvelle page
         return "redirect:urlmsg";  
    }
    
      @GetMapping("/urlmsg")
    public String urlpostmsg(Model model) {
    
        model.addAttribute("usermsg",list);
        //model est un conteneur qui va stocker sous le nom usermsg une liste 
        //à la page html , spring va scanner le conteneur (model) et verifiera s'il existe un attribut qui se nomme usermsg qu'on peut reutiliser
        //et là on peut le reutiliser
        return "message2";
    }
}
