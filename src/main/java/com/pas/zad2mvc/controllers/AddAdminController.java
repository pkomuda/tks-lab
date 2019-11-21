package com.pas.zad2mvc.controllers;

import com.pas.zad2mvc.data.Admin;
import com.pas.zad2mvc.data.User;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;

@Named
@ConversationScoped
public class AddAdminController extends AddUserController
{
    private Admin admin;

    @PostConstruct
    private void init()
    {
        admin = new Admin();
    }

    @Override
    public User getUser()
    {
        return admin;
    }

    @Override
    public String add()
    {
        getConversation().begin();
        return "addAdmin";
    }

    @Override
    public String confirm()
    {
        getUserService().addAdmin(admin.getUsername(), admin.isActive());
        getConversation().end();
        return "home";
    }
}
