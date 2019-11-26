package com.pas.zad2mvc.controllers;

import com.pas.zad2mvc.services.UserService;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ConversationScoped
public class AddUserController implements Serializable {
    @Inject
    private UserService userService;
    @Inject
    private Conversation conversation;
    private String userType;
    private String username;
    private boolean active;

    public String add() {
        conversation.begin();
        return "add";
    }

    public String confirm(String userType) {
        switch (userType) {
            case "admin":
                userService.addAdmin(username, active);
                break;
            case "manager":
                userService.addManager(username, active);
                break;
            case "client":
                userService.addClient(username, active);
                break;
        }
        conversation.end();
        return "admin";
    }

    //region getters
    public String getUserType() {
        return userType;
    }

    public String getUsername() {
        return username;
    }

    public boolean isActive() {
        return active;
    }
    //endregion

    //region setters
    public void setUserType(String userType) {
        this.userType = userType;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setActive(boolean active) {
        this.active = active;
    }
    //endregion
}
