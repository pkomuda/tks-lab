package com.pas.zad2mvc.controllers;

import com.pas.zad2mvc.services.UserService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class HomepageController
{
    @Inject
    private UserService userService;

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
