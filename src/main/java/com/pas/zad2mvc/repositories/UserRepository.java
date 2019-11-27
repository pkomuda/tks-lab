package com.pas.zad2mvc.repositories;

import com.pas.zad2mvc.data.*;

import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Named
@ApplicationScoped
public class UserRepository {
    private LinkedHashMap<String, User> users = new LinkedHashMap<>();

    public void addAdmin(String username, boolean active, String firstName, String lastName) {
        if (getUser(username) == null) {
            users.put(username, new Admin(username, active, firstName, lastName));
        }
    }

    public void addManager(String username, boolean active, String firstName, String lastName) {
        if (getUser(username) == null) {
            users.put(username, new Manager(username, active, firstName, lastName));
        }
    }

    public void addClient(String username, boolean active, String firstName, String lastName) {
        if (getUser(username) == null) {
            users.put(username, new Client(username, active, firstName, lastName));
        }
    }

    public User getUser(String username) {
        return users.get(username);
    }

    public List<User> getUsers() {
        return new ArrayList<>(users.values());
    }

    public void updateUserInfo(String username, UserInfo userInfo) {
        if (getUser(username) != null) {
            getUser(username).setInfo(userInfo);
        }
    }

    public boolean activateUser(String username) {
        if (getUser(username) != null && !getUser(username).isActive()) {
            getUser(username).setActive(true);
            return true;
        } else {
            return false;
        }
    }

    public boolean deactivateUser(String username) {
        if (getUser(username) != null && getUser(username).isActive()) {
            getUser(username).setActive(false);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < users.size(); i++) {
            if (i == 0) {
                str = str.concat(getUsers().get(i).toString() + "\n");
            } else {
                str = str.concat("\t\t " + getUsers().get(i).toString());
                if (i != users.size() - 1) {
                    str = str.concat("\n");
                }
            }
        }
        return "UserRepo[" + str + "]";
    }

    @PostConstruct
    private void addUserPool() {
        addAdmin("admin1", true, "Walter", "White");
        addAdmin("admin2", true, "Jesse", "Pinkman");
        addManager("manager1", true, "Marie", "Schrader");
        addManager("manager2", true,"Jimmy", "McGill");
        addClient("client1", true, "Kim", "Wexler");
        addClient("client2", true, "Gustavo", "Fring");
    }
}
