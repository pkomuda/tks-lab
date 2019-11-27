package com.pas.zad2mvc.controllers;

import com.pas.zad2mvc.data.User;
import com.pas.zad2mvc.data.UserInfo;
import com.pas.zad2mvc.services.UserService;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@ConversationScoped
public class ListUsersController implements Serializable {
    @Inject
    private UserService userService;
    @Inject
    Conversation conversation;
    private List<User> users;
    private String username;
    private String firstName;
    private String lastName;

    public void prepareUserInfo(String username, String firstName, String lastName) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String editUser() {
        if (!conversation.isTransient()) {
            conversation.end();
        }
        conversation.begin();
        return "edit";
    }

    public String confirmEdit() {
        userService.getUser(username).setInfo(new UserInfo(firstName, lastName));
        conversation.end();
        return "admin";
    }

    //region getters
    public List<User> getUsers() {
        return users;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
    //endregion

    //region setters
    public void setUsername(String username) {
        this.username = username;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    //endregion

    @PostConstruct
    public void loadUsers() {
        users = userService.getUsers();
    }
}
