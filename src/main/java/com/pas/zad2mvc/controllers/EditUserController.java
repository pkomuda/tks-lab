package com.pas.zad2mvc.controllers;

import com.pas.zad2mvc.data.UserInfo;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class EditUserController {
    @Inject
    private ListUsersController listUsersController;
    private UserInfo userInfo;

    public String confirmEdit(String username) {
        listUsersController.getUserService().updateUserInfo(username, userInfo);
        listUsersController.endConversation();
        return "admin";
    }

    //region getters
    public String getFirstName() {
        return userInfo.getFirstName();
    }

    public String getLastName() {
        return userInfo.getLastName();
    }
    //endregion

    //region setters
    public void setFirstName(String firstName) {
        this.userInfo.setFirstName(firstName);
    }

    public void setLastName(String lastName) {
        this.userInfo.setLastName(lastName);
    }
    //endregion

    @PostConstruct
    public void loadUserInfo() {
        userInfo = listUsersController.getSelectedUserInfo();
    }
}
