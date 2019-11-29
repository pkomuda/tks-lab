package com.pas.zad2mvc.controllers;

import com.pas.zad2mvc.model.Admin;
import com.pas.zad2mvc.model.Client;
import com.pas.zad2mvc.model.Manager;
import com.pas.zad2mvc.model.User;
import com.pas.zad2mvc.services.UserService;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class LoginController implements Serializable {
    @Inject
    private UserService userService;
    private String username;
    private boolean userExists;
    private User user;

    public void checkIfUserExists(String username) {
        for(User u : userService.getUsers()) {
            if(username.equals(u.getUsername()) && u.isActive()) {
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

    public String logOut() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "index";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String name) {
        this.username = name;
    }
}
