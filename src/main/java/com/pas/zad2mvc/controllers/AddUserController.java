package com.pas.zad2mvc.controllers;

import com.pas.zad2mvc.services.UserService;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ConversationScoped
public class AddUserController implements Serializable
{
    @Inject
    private UserService userService;
    @Inject
    private Conversation conversation;
    private String username;
    private boolean active;

    //region getters
    public String getUsername() { return username; }
    public boolean isActive() { return active; }
    //endregion

    //region setters
    public void setUsername(String username) { this.username = username; }
    public void setActive(boolean active) { this.active = active; }
    //endregion

    public String add()
    {
        conversation.begin();
        return "add";
    }

    public String confirmAdmin()
    {
        userService.addAdmin(username, active);
        conversation.end();
        return "home";
    }

    public String confirmManager()
    {
        userService.addManager(username, active);
        conversation.end();
        return "home";
    }

    public String confirmClient()
    {
        userService.addClient(username, active);
        conversation.end();
        return "home";
    }
}
