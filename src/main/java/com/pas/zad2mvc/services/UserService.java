package com.pas.zad2mvc.services;

import com.pas.zad2mvc.model.*;
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
        if (getUser(username) == null) {
            userRepository.addUser(new Admin(username, active, firstName, lastName));
        }
    }

    public void addManager(String username, boolean active, String firstName, String lastName) {
        if (getUser(username) == null) {
            userRepository.addUser(new Manager(username, active, firstName, lastName));
        }
    }

    public void addClient(String username, boolean active, String firstName, String lastName) {
        if (getUser(username) == null) {
            userRepository.addUser(new Client(username, active, firstName, lastName));
        }
    }

    public User getUser(String username) {
        return userRepository.getUser(username);
    }

    public List<User> getUsers() {
        return userRepository.getUsers();
    }

    public List<Admin> getAdmins() {
        return userRepository.getAdmins();
    }

    public List<Manager> getManagers() {
        return userRepository.getManagers();
    }

    public List<Client> getClients() {
        return userRepository.getClients();
    }

    public List<User> filterUsers(String userFilter) {
        return userRepository.filterUsers(userFilter);
    }

    public List<Admin> filterAdmins(String adminFilter) {
        return userRepository.filterAdmins(adminFilter);
    }

    public List<Manager> filterManagers(String managerFilter) {
        return userRepository.filterManagers(managerFilter);
    }

    public List<Client> filterClients(String clientFilter) {
        return userRepository.filterClients(clientFilter);
    }

    public void updateUserInfo(String username, UserInfo userInfo) {
        if (getUser(username) != null) {
            getUser(username).setInfo(userInfo);
        }
    }

    public void setUserActivity(String username, boolean active) {
        if (getUser(username) != null) {
            getUser(username).setActive(active);
        }
    }

    public void activateUser(String username) {
        if (getUser(username) != null && !getUser(username).isActive()) {
            getUser(username).setActive(true);
        }
    }

    public void deactivateUser(String username) {
        if (getUser(username) != null && getUser(username).isActive()) {
            getUser(username).setActive(false);
        }
    }

    @Override
    public String toString() {
        return userRepository.toString();
    }
}
