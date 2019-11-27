package com.pas.zad2mvc.controllers;

import com.pas.zad2mvc.data.User;
import com.pas.zad2mvc.data.UserInfo;
import com.pas.zad2mvc.services.UserService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ConversationScoped
public class ListUsersController implements Serializable {
    @Inject
    private UserService userService;
    @Inject
    private Conversation conversation;
    private List<User> users;
    private User selectedUser;

    public String prepareUserInfo(User selectedUser) {
        beginConversation();
        setSelectedUser(selectedUser);
        return "edit";
    }

    //region conversation
    public void beginConversation() {
        if (!conversation.isTransient()) {
            conversation.end();
        }
        conversation.begin();
    }

    public void endConversation() {
        conversation.end();
    }
    //endregion

    //region getters
    public UserService getUserService() {
        return userService;
    }

    public List<User> getUsers() {
        return users;
    }

    public User getSelectedUser() {
        return selectedUser;
    }

    public String getSelectedUsername() {
        return selectedUser.getUsername();
    }

    public UserInfo getSelectedUserInfo() {
        return selectedUser.getInfo();
    }
    //endregion

    public void setSelectedUser(User selectedUser) {
        this.selectedUser = selectedUser;
    }

    @PostConstruct
    public void loadUsers() {
        users = userService.getUsers();
    }
}
