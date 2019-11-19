package com.pas.zad2mvc.controllers;

import com.pas.zad2mvc.data.Admin;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;

@Named
@ConversationScoped
public class AddAdminController extends AddUserController
{
    private Admin admin;

    @Override
    public String add()
    {
        getConversation().begin();
        return "";
    }

    @Override
    public String confirm()
    {
        getUserService().addAdmin("", true);
        getConversation().end();
        return "";
    }
}
