/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pas.zad2mvc;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.enterprise.context.Dependent;

/**
 *
 * @author Szymi
 */
@Named(value = "login")
@ApplicationScoped
public class Login {
     
    private UserList userlist;
    private boolean userExists;
    /**
     * Creates a new instance of Login
     */
    public Login() {
       userlist = new UserList();
      
       
    }
    public void addUserPool(){
       userlist.addClient("herb", true);
       userlist.addClient("papaj", true);
    }
    public void checkIfUserExists(String username)
    {
        for(User user : userlist.getUsers()){
            if(username==user.getUsername())
            {
               this.userExists=true;
            }
        }
        this.userExists=false;
    }
    public String redirect()
    {
        if (userExists)
            return "index.html";
        return "index.html";
    }
    //do sprawdzenia
    public String getOneUsername()
    {
        return userlist.getUsers().get(0).getUsername();
    }
    
}
