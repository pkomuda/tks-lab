/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pas.zad2mvc;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author Szymi
 */
@Named(value = "loginManager")
@ApplicationScoped
public class LoginManager extends Login {

    /**
     * Creates a new instance of LoginManager
     */
    public LoginManager() {
        super();
    }
    
    @Override
    public void checkIfUserExists(String username)
    {
        for(User user : getUserlist().getUsers()){
            if(username.equals(user.getUsername()) && user.getClass().getName().equals("Manager"))
            {
                setUserExists(true);
            }
        }
        setUserExists(false);
    }

    @Override
    public void addUserPool()
    {
        List<User> users = new ArrayList<>();
        users.add(new Manager("menago", true));
        users.add(new Manager("wozny", true));
        if (!getUserlist().getUsers().containsAll(users))
            getUserlist().getUsers().addAll(users);
    }
}


