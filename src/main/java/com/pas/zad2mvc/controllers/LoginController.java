package com.pas.zad2mvc.controllers;

import com.pas.zad2mvc.data.User;
import com.pas.zad2mvc.services.UserService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class LoginController
{
    @Inject
    private UserService userService;
    private boolean userExists;
    private String userType;
    private String username;

    public String getUsername() { return username; }
    public void setUsername(String name) { this.username = name; }

    public void checkIfUserExists(String username)
    {
        for(User user : userService.getUsers())
        {
            if(username.equals(user.getUsername()))
            {
                userType = user.getClass().getName();
                userExists = true;
                return;
            }
        }
        userExists = false;
    }

    public String redirect()
    {
        if (userExists)
        {
            switch (userType)
            {
                case "com.pas.zad2mvc.data.Admin":
                    return "admin";
                case "com.pas.zad2mvc.data.Manager":
                    return "manager";
                case "com.pas.zad2mvc.data.Client":
                    return "client";
            }
        }
        return "login.xhtml";
    }

    public String goBack()
    {
        return "back";
    }
}
