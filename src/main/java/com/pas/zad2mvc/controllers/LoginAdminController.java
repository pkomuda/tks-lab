package com.pas.zad2mvc.controllers;

import com.pas.zad2mvc.AdminPanelPageBean;
import com.pas.zad2mvc.Login;
import com.pas.zad2mvc.data.Admin;
import com.pas.zad2mvc.data.User;
import com.pas.zad2mvc.services.UserService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class LoginAdminController
{
    @Inject
    private UserService userService;
    private boolean userExists;
    private String name;

//    @PostConstruct
//    public void showUsers()
//    {
//        for (User user : userService.getUsers())
//        {
//            System.out.println(user);
//        }
//    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public boolean isUserExists()
    {
        return userExists;
    }

    public void setUserExists(boolean userExists)
    {
        this.userExists = userExists;
    }

    public void checkIfUserExists(String name)
    {
        for(User user : userService.getUsers())
        {
            if(name.equals(user.getUsername())/* && user.getClass().getName().equals("com.pas.zad2mvc.data.Admin")*/)
            {
                setUserExists(true);
                return;
            }
        }
        setUserExists(false);
//        setUserExists(true);
    }

    public String redirect()
    {
        if (isUserExists())
        {
            return "adminpanel.xhtml";
        }
        return "adminlogin.xhtml";
    }
}
