package com.pas.zad2mvc.controllers;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class HomepageController
{
//    @Inject
//    private UserService userService;

//    public HomepageController()
//    {
//        userService = new UserService();
//    }

//    @PostConstruct
//    public void showUsers()
//    {
//        System.out.println("herb");
//        for (User u : userService.getUsers())
//        {
//            System.out.println(u);
//        }
//    }

    public String admin()
    {

        return "admin";
    }

    public String manager()
    {
        return "manager";
    }

    public String client()
    {
        return "client";
    }
}
