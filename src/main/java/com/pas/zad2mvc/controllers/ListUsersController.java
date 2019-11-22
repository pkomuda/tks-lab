package com.pas.zad2mvc.controllers;

import com.pas.zad2mvc.data.User;
import com.pas.zad2mvc.services.UserService;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@ViewScoped
public class ListUsersController implements Serializable
{
    @Inject
    private UserService userService;
    private List<User> users;

    public List<User> getUsers() { return users; }

    // Nie ma usuwania bo do uzytkownikow ma byc tylko CRU

    @PostConstruct
    public void loadUsers() { users = userService.getUsers(); }
}
