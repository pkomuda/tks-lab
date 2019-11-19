package com.pas.zad2mvc.controllers;

import com.pas.zad2mvc.services.UserService;

import javax.enterprise.context.Conversation;
import javax.inject.Inject;
import java.io.Serializable;

public abstract class AddUserController implements Serializable
{
    @Inject
    private UserService userService;

    @Inject
    private Conversation conversation;

    public UserService getUserService()
    {
        return userService;
    }

    public Conversation getConversation()
    {
        return conversation;
    }

    public abstract String add();
    public abstract String confirm();
}
