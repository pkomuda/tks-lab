package com.pas.zad2mvc.repositories;

import com.pas.zad2mvc.model.Admin;
import com.pas.zad2mvc.model.Client;
import com.pas.zad2mvc.model.Manager;
import com.pas.zad2mvc.model.User;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

@Named
@ApplicationScoped
public class UserRepository {
    private LinkedHashMap<String, User> users = new LinkedHashMap<>();

    public synchronized void addUser(User user) {
        users.put(user.getUsername(), user);
    }

    public synchronized User getUser(String username) {
        return users.get(username);
    }

    public synchronized void updateUser(String username, User user) {
        users.replace(username, user);
    }

    public synchronized List<User> getUsers() {
        return new ArrayList<>(users.values());
    }

    public List<User> filterUsers(String userFilter) {
        return getUsers()
                .stream()
                .filter(user -> StringUtils.containsIgnoreCase(user.toString(), userFilter))
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < users.size(); i++) {
            if (i == 0) {
                str = str.concat(getUsers().get(i).toString() + "\n");
            } else {
                str = str.concat("\t   " + getUsers().get(i).toString());
                if (i != users.size() - 1) {
                    str = str.concat("\n");
                }
            }
        }
        return "UserRepo[" + str + "]";
    }

    @PostConstruct
    private void addUserPool() {
        addUser(new Admin("admin1", true, "Walter", "White"));
        addUser(new Admin("admin2", true, "Jesse", "Pinkman"));
        addUser(new Manager("manager1", true, "Marie", "Schrader"));
        addUser(new Manager("manager2", true,"Jimmy", "McGill"));
        addUser(new Client("client1", true, "Kim", "Wexler"));
        addUser(new Client("client2", true, "Gustavo", "Fring"));
    }
}
