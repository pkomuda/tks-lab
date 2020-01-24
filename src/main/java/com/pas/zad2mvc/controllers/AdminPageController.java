package com.pas.zad2mvc.controllers;

import com.pas.zad2mvc.model.users.Admin;
import com.pas.zad2mvc.model.users.Client;
import com.pas.zad2mvc.model.users.Manager;
import com.pas.zad2mvc.model.users.User;
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
    private List<Admin> admins;
    private List<Manager> managers;
    private List<Client> clients;
    private String userFilter;

    public String addUser() {
        return "add";
    }

    public String prepareUserInfo(User selectedUser) {
        viewAccessController.setSelectedUsername(selectedUser.getUsername());
        return "edit";
    }

    public String prepareRentsInfo(Client selectedClient) {
        viewAccessController.setSelectedUsername(selectedClient.getUsername());
        return "rentsForClient";
    }

    public void filterUsers() {
        admins = userService.filterAdmins(userFilter);
        managers = userService.filterManagers(userFilter);
        clients = userService.filterClients(userFilter);
    }

    //region getters
    public List<Admin> getAdmins() {
        return admins;
    }

    public List<Manager> getManagers() {
        return managers;
    }

    public List<Client> getClients() {
        return clients;
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
        admins = userService.getAdmins();
        managers = userService.getManagers();
        clients = userService.getClients();
    }
}
