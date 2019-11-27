package com.pas.zad2mvc.services;

import com.pas.zad2mvc.data.User;
import com.pas.zad2mvc.data.UserInfo;
import com.pas.zad2mvc.repositories.UserRepository;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@Dependent
public class UserService implements Serializable {
    @Inject
    private UserRepository userRepository;

    public void addAdmin(String username, boolean active, String firstName, String lastName) {
        userRepository.addAdmin(username, active, firstName, lastName);
    }

    public void addManager(String username, boolean active, String firstName, String lastName) {
        userRepository.addManager(username, active, firstName, lastName);
    }

    public void addClient(String username, boolean active, String firstName, String lastName) {
        userRepository.addClient(username, active, firstName, lastName);
    }

    public User getUser(String username) {
        return userRepository.getUser(username);
    }

    public List<User> getUsers() {
        return userRepository.getUsers();
    }

    public void updateUserInfo(String username, UserInfo userInfo) {
        userRepository.updateUserInfo(username, userInfo);
    }

    public boolean activateUser(String username) {
        return userRepository.activateUser(username);
    }

    public boolean deactivateUser(String username) {
        return userRepository.deactivateUser(username);
    }

    @Override
    public String toString() {
        return userRepository.toString();
    }
}
