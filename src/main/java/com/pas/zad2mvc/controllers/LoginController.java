package com.pas.zad2mvc.controllers;

import com.pas.zad2mvc.data.Admin;
import com.pas.zad2mvc.data.Client;
import com.pas.zad2mvc.data.Manager;
import com.pas.zad2mvc.data.User;
import com.pas.zad2mvc.services.UserService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class LoginController {
    @Inject
    private UserService userService;
    private String username;
    private boolean userExists;
    private User user;

    public void checkIfUserExists(String username) {
        for(User u : userService.getUsers()) {
            if(username.equals(u.getUsername())) {
                userExists = true;
                if (u instanceof Admin) {
                    user = new Admin(u);
                } else if (u instanceof Manager) {
                    user = new Manager(u);
                } else if (u instanceof Client) {
                    user = new Client(u);
                }
                return;
            }
        }
        userExists = false;
    }

    public String redirect() {
        if (userExists) {
            return user.getType();
        } else {
            return "login.xhtml";
        }
    }

    public String goBack() {
        return "back";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String name) {
        this.username = name;
    }
}
