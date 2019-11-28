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
    private AdminPageController adminPageController;
    private boolean active;
    private UserInfo userInfo;

    public String confirmEditUser(String username) {
        adminPageController.getUserService().setUserActivity(username, active);
        adminPageController.getUserService().updateUserInfo(username, userInfo);
        adminPageController.endConversation();
        return "admin";
    }

    //region getters
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
    public void setActive(boolean active) {
        this.active = active;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
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
        setActive(adminPageController.getSelectedUserActivity());
        setUserInfo(adminPageController.getSelectedUserInfo());
    }
}
