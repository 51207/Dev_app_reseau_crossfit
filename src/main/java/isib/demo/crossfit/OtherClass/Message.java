/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isib.demo.crossfit.OtherClass;

import java.time.LocalDateTime;

/**
 *
 * @author aliou
 */

public class Message {
    
    private String author;
    private LocalDateTime timeStamp;
    private String message ;
    
    public Message(String author ,String message){
    this.author = author ;
    this.timeStamp = LocalDateTime.now();
    this.message = message;
    }
public Message(){
   
    }
    public String getAuthor() {
        return author;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public String getMessage() {
        return message;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
}
