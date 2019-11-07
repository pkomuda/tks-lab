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
@Named(value = "loginAdmin")
@ApplicationScoped
public class LoginAdmin extends Login{

    /**
     * Creates a new instance of LoginAdmin
     */
    public LoginAdmin(){
        super();
    }
    @Override
    public void checkIfUserExists(String username)
    {
        for(User user : getUserlist().getUsers()){
            if(username.equals(user.getUsername()) && user.getClass().getName().equals("Admin"))
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
        users.add(new Client("pokazny", true));
        users.add(new Client("chefe", true));
        if (!getUserlist().getUsers().containsAll(users))
            getUserlist().getUsers().addAll(users);
    }

}  