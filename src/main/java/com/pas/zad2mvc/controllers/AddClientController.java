package com.pas.zad2mvc.controllers;

import com.pas.zad2mvc.data.Client;
import com.pas.zad2mvc.data.User;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;

@Named
@ConversationScoped
public class AddClientController extends AddUserController
{
    private Client client;

    @PostConstruct
    private void init()
    {
        client = new Client();
    }

    @Override
    public User getUser()
    {
        return client;
    }

    @Override
    public String add()
    {
        getConversation().begin();
        return "addClient";
    }

    @Override
    public String confirm()
    {
        getUserService().addAdmin(client.getUsername(), client.isActive());
        getConversation().end();
        return "home";
    }
}
