package com.pas.zad2mvc.controllers;

import com.pas.zad2mvc.data.User;
import com.pas.zad2mvc.data.UserInfo;
import com.pas.zad2mvc.services.UserService;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Named
@ConversationScoped
public class ListUsersController implements Serializable {
    @Inject
    private UserService userService;
    @Inject
    private Conversation conversation;
    private List<User> users;
    private User selectedUser;
    private String userFilter;

    public String prepareUserInfo(User selectedUser) {
        beginConversation();
        setSelectedUser(selectedUser);
        return "edit";
    }

    public void filterUsers() {
        users = userService.getUsers()
                .stream()
                .filter(user -> StringUtils.containsIgnoreCase(user.toString(), userFilter))
                .collect(Collectors.toList());
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

    public boolean getSelectedUserActivity() {
        return selectedUser.isActive();
    }

    public UserInfo getSelectedUserInfo() {
        return selectedUser.getInfo();
    }

    public String getUserFilter() {
        return userFilter;
    }
    //endregion

    //region setters
    public void setSelectedUser(User selectedUser) {
        this.selectedUser = selectedUser;
    }

    public void setUserFilter(String userFilter) {
        this.userFilter = userFilter;
    }
    //endregion

    @PostConstruct
    public void loadUsers() {
        users = userService.getUsers();
    }
}
