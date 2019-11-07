/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pas.zad2mvc;

import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author przemek
 */
@Named(value = "loginClient")
@ApplicationScoped
public class LoginClient extends Login
{
    public LoginClient()
    {
        super();
    }

    @Override
    public void checkIfUserExists(String username)
    {
        for(User user : getUserlist().getUsers()){
            if(username.equals(user.getUsername()) && user.getClass().getName().equals("Client"))
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
        users.add(new Client("herb", true));
        users.add(new Client("papaj", true));
        if (!getUserlist().getUsers().containsAll(users))
            getUserlist().getUsers().addAll(users);
    }

    @Override
    public String redirect()
    {
        if (isUserExists())
            return "index.html";
        return "clientlogin.xhtml";
    }
}
