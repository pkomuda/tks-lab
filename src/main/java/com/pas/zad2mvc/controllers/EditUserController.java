package com.pas.zad2mvc.controllers;

import com.pas.zad2mvc.model.UserInfo;
import com.pas.zad2mvc.services.UserService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class EditUserController {
    @Inject
    private UserService userService;
    @Inject
    private ViewAccessController viewAccessController;
    private String username;
    private boolean active;
    private UserInfo userInfo;

    public String confirmEditUser() {
        userService.setUserActivity(username, active);
        userService.updateUserInfo(username, userInfo);
        return "admin";
    }

    public String cancel() {
        return "cancel";
    }

    //region getters
    public String getUsername() {
        return username;
    }

    public boolean isActive() {
        return active;
    }

    public String getFirstName() {
        return userInfo.getFirstName();
    }

    public String getLastName() {
        return userInfo.getLastName();
    }
    //endregion

    //region setters
    public void setUsername(String username) {
        this.username = username;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setFirstName(String firstName) {
        this.userInfo.setFirstName(firstName);
    }

    public void setLastName(String lastName) {
        this.userInfo.setLastName(lastName);
    }
    //endregion

    @PostConstruct
    public void loadUserInfo() {
        username = viewAccessController.getSelectedUsername();
        active = userService.getUser(username).isActive();
        userInfo = userService.getUser(username).getInfo();
    }
}
