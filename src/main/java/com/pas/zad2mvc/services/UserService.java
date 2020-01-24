package com.pas.zad2mvc.services;

import com.pas.zad2mvc.model.users.*;
import com.pas.zad2mvc.repositories.UserRepository;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Named
@Dependent
public class UserService implements Serializable {
    @Inject
    private UserRepository userRepository;

    private String sha256(String password) {
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] hash = new byte[0];
        if (digest != null) {
            hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
        }
        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public void addAdmin(String username, boolean active, String firstName, String lastName, String password) {
        if (getUser(username) == null) {
            userRepository.addUser(new Admin(username, active, firstName, lastName));
            userRepository.updateUserPassword(username, sha256(password));
        }
    }

    public void addManager(String username, boolean active, String firstName, String lastName, String password) {
        if (getUser(username) == null) {
            userRepository.addUser(new Manager(username, active, firstName, lastName));
            userRepository.updateUserPassword(username, sha256(password));
        }
    }

    public void addClient(String username, boolean active, String firstName, String lastName, String password) {
        if (getUser(username) == null) {
            userRepository.addUser(new Client(username, active, firstName, lastName));
            userRepository.updateUserPassword(username, sha256(password));
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
