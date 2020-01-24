package com.pas.zad2mvc.repositories;

import com.pas.zad2mvc.model.users.Admin;
import com.pas.zad2mvc.model.users.Client;
import com.pas.zad2mvc.model.users.Manager;
import com.pas.zad2mvc.model.users.User;
import org.apache.commons.lang3.StringUtils;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Named
@ApplicationScoped
public class UserRepository {
    private final String url = "jdbc:derby://localhost:1527/PasDB";

    public synchronized void addUser(User user) {
        try (Connection connection = DriverManager.getConnection(url)) {
            PreparedStatement userStatement = connection.prepareStatement(
                    "INSERT INTO USERTABLE(USERNAME, ACTIVITY, FIRSTNAME, LASTNAME) VALUES(?,?,?,?)");
            userStatement.setString(1, user.getUsername());
            userStatement.setBoolean(2, user.isActive());
            userStatement.setString(3, user.getFirstName());
            userStatement.setString(4, user.getLastName());
            PreparedStatement groupStatement = connection.prepareStatement(
                    "INSERT INTO GROUPTABLE VALUES(?,?,?)");
            groupStatement.setString(1, UUID.randomUUID().toString().replace("-", ""));
            groupStatement.setString(2, user.getType().toUpperCase());
            groupStatement.setString(3, user.getUsername());
            userStatement.execute();
            groupStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public synchronized User getUser(String username) {
        User user = null;
        String group, firstName, lastName;
        boolean activity;
        try (Connection connection = DriverManager.getConnection(url)) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT U.ACTIVITY, U.FIRSTNAME, U.LASTNAME, G.GROUPNAME FROM USERTABLE AS U " +
                            "INNER JOIN GROUPTABLE AS G on U.USERNAME = G.USERNAME WHERE U.USERNAME=?");
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                activity = resultSet.getBoolean("ACTIVITY");
                firstName = resultSet.getString("FIRSTNAME");
                lastName = resultSet.getString("LASTNAME");
                group = resultSet.getString("GROUPNAME");
                switch (group) {
                    case "ADMIN":
                        user = new Admin(username, activity, firstName, lastName);
                        break;
                    case "MANAGER":
                        user = new Manager(username, activity, firstName, lastName);
                        break;
                    case "CLIENT":
                        user = new Client(username, activity, firstName, lastName);
                        break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public synchronized void updateUser(String username, User user) {
        try (Connection connection = DriverManager.getConnection(url)) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE USERTABLE SET ACTIVITY=?, FIRSTNAME=?, LASTNAME=? WHERE USERNAME=?");
            preparedStatement.setBoolean(1, user.isActive());
            preparedStatement.setString(2, user.getFirstName());
            preparedStatement.setString(3, user.getLastName());
            preparedStatement.setString(4, username);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public synchronized void updateUserPassword(String username, String password) {
        try (Connection connection = DriverManager.getConnection(url)) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE USERTABLE SET PASSWORD=? WHERE USERNAME=?");
            preparedStatement.setString(1, password);
            preparedStatement.setString(2, username);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public synchronized List<User> getUsers() {
        List<User> users = new ArrayList<>();
        String username, group, firstName, lastName;
        boolean activity;
        try (Connection connection = DriverManager.getConnection(url)) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT U.USERNAME, U.ACTIVITY, U.FIRSTNAME, U.LASTNAME, G.GROUPNAME FROM USERTABLE AS U " +
                            "INNER JOIN GROUPTABLE AS G on U.USERNAME = G.USERNAME");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                username = resultSet.getString("USERNAME");
                activity = resultSet.getBoolean("ACTIVITY");
                firstName = resultSet.getString("FIRSTNAME");
                lastName = resultSet.getString("LASTNAME");
                group = resultSet.getString("GROUPNAME");
                switch (group) {
                    case "ADMIN":
                        users.add(new Admin(username, activity, firstName, lastName));
                        break;
                    case "MANAGER":
                        users.add(new Manager(username, activity, firstName, lastName));
                        break;
                    case "CLIENT":
                        users.add(new Client(username, activity, firstName, lastName));
                        break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public List<Admin> getAdmins() {
        return getUsers()
                .stream()
                .filter(user -> user instanceof Admin)
                .map(user -> (Admin) user)
                .collect(Collectors.toList());
    }

    public List<Manager> getManagers() {
        return getUsers()
                .stream()
                .filter(user -> user instanceof Manager)
                .map(user -> (Manager) user)
                .collect(Collectors.toList());
    }

    public List<Client> getClients() {
        return getUsers()
                .stream()
                .filter(user -> user instanceof Client)
                .map(user -> (Client) user)
                .collect(Collectors.toList());
    }

    public List<User> filterUsers(String userFilter) {
        return getUsers()
                .stream()
                .filter(user -> StringUtils.containsIgnoreCase(user.toString(), userFilter))
                .collect(Collectors.toList());
    }

    public List<Admin> filterAdmins(String adminFilter) {
        return getAdmins()
                .stream()
                .filter(user -> StringUtils.containsIgnoreCase(user.toString(), adminFilter))
                .collect(Collectors.toList());
    }

    public List<Manager> filterManagers(String managerFilter) {
        return getManagers()
                .stream()
                .filter(user -> StringUtils.containsIgnoreCase(user.toString(), managerFilter))
                .collect(Collectors.toList());
    }

    public List<Client> filterClients(String clientFilter) {
        return getClients()
                .stream()
                .filter(user -> StringUtils.containsIgnoreCase(user.toString(), clientFilter))
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < getUsers().size(); i++) {
            if (i == 0) {
                str = str.concat(getUsers().get(i).toString() + "\n");
            } else {
                str = str.concat("\t   " + getUsers().get(i).toString());
                if (i != getUsers().size() - 1) {
                    str = str.concat("\n");
                }
            }
        }
        return "UserRepo[" + str + "]";
    }
}
