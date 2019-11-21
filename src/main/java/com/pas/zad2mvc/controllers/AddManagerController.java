package com.pas.zad2mvc.controllers;

import com.pas.zad2mvc.data.Manager;
import com.pas.zad2mvc.data.User;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;

@Named
@ConversationScoped
public class AddManagerController extends AddUserController
{
    private Manager manager;

    @PostConstruct
    private void init()
    {
        manager = new Manager();
    }

    @Override
    public User getUser()
    {
        return manager;
    }

    @Override
    public String add()
    {
        getConversation().begin();
        return "addManager";
    }

    @Override
    public String confirm()
    {
        getUserService().addAdmin(manager.getUsername(), manager.isActive());
        getConversation().end();
        return "home";
    }
}
