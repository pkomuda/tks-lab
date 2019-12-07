package com.pas.zad2mvc.controllers;

import com.pas.zad2mvc.model.User;
import com.pas.zad2mvc.services.UserService;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class AdminPageController implements Serializable {
    @Inject
    private UserService userService;
    @Inject
    private ViewAccessController viewAccessController;
    private List<User> users;
    private String userFilter;

    public String addUser() {
        return "add";
    }

    public String prepareUserInfo(User selectedUser) {
        viewAccessController.setSelectedUsername(selectedUser.getUsername());
        return "edit";
    }

    public void filterUsers() {
        users = userService.filterUsers(userFilter);
    }

    //region getters
    public List<User> getUsers() {
        return users;
    }

    public String getUserFilter() {
        return userFilter;
    }
    //endregion

    public void setUserFilter(String userFilter) {
        this.userFilter = userFilter;
    }

    @PostConstruct
    public void loadUsers() {
        users = userService.getUsers();
    }
}
