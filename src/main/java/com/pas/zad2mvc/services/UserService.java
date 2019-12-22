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

    public void addAdmin(String username, boolean active, String firstName, String lastName, String password) {
        if (getUser(username) == null) {
            userRepository.addUser(new Admin(username, active, firstName, lastName));
            userRepository.setUserPassword(username, password);
        }
    }

    public void addManager(String username, boolean active, String firstName, String lastName, String password) {
        if (getUser(username) == null) {
            userRepository.addUser(new Manager(username, active, firstName, lastName));
            userRepository.setUserPassword(username, password);
        }
    }

    public void addClient(String username, boolean active, String firstName, String lastName, String password) {
        if (getUser(username) == null) {
            userRepository.addUser(new Client(username, active, firstName, lastName));
            userRepository.setUserPassword(username, password);
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
            User temp = getUser(username);
            temp.setInfo(userInfo);
            userRepository.updateUser(username, temp);
        }
    }

    public void setUserActivity(String username, boolean active) {
        if (getUser(username) != null) {
            User temp = getUser(username);
            temp.setActive(active);
            userRepository.updateUser(username, temp);
        }
    }

    public void activateUser(String username) {
        if (getUser(username) != null && !getUser(username).isActive()) {
            User temp = getUser(username);
            temp.setActive(true);
            userRepository.updateUser(username, temp);
        }
    }

    public void deactivateUser(String username) {
        if (getUser(username) != null && getUser(username).isActive()) {
            User temp = getUser(username);
            temp.setActive(false);
            userRepository.updateUser(username, temp);
        }
    }

    @Override
    public String toString() {
        return userRepository.toString();
    }
}
