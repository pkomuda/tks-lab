package com.pas.zad2mvc.controllers;

import com.pas.zad2mvc.model.User;
import com.pas.zad2mvc.model.UserInfo;
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
public class AdminPageController implements Serializable {
    @Inject
    private UserService userService;
    @Inject
    private Conversation conversation;
    private List<User> users;
    private User selectedUser;
    private String userFilter;

    public String prepareUserInfo(User selectedUser) {
        beginConversation();
        this.selectedUser = selectedUser;
        return "edit";
    }

    public void filterUsers() {
        users = userService.filterUsers(userFilter);
    }

    //region conversation
    private void beginConversation() {
        if (!conversation.isTransient()) {
            conversation.end();
        }
        conversation.begin();
    }

    void endConversation() {
        conversation.end();
    }
    //endregion

    //region getters
    UserService getUserService() {
        return userService;
    }

    boolean getSelectedUserActivity() {
        return selectedUser.isActive();
    }

    UserInfo getSelectedUserInfo() {
        return selectedUser.getInfo();
    }

    public String getSelectedUsername() {
        return selectedUser.getUsername();
    }

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
