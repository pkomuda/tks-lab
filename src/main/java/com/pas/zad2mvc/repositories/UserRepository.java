package com.pas.zad2mvc.repositories;

import com.pas.zad2mvc.model.Admin;
import com.pas.zad2mvc.model.Client;
import com.pas.zad2mvc.model.Manager;
import com.pas.zad2mvc.model.User;
import org.apache.commons.lang3.StringUtils;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Named
@ApplicationScoped
public class UserRepository {
    private final String url = "jdbc:derby://localhost:1527/PasDB";

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

    public synchronized void addUser(User user) {
        try (Connection connection = DriverManager.getConnection(url)) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO \"USER\"(USERNAME, ACTIVITY, GROUPNAME, FIRSTNAME, LASTNAME) VALUES(?,?,?,?,?)");
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setBoolean(2, user.isActive());
            preparedStatement.setString(3, user.getType().toUpperCase());
            preparedStatement.setString(4, user.getFirstName());
            preparedStatement.setString(5, user.getLastName());
            preparedStatement.execute();
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
                    "SELECT USERNAME, ACTIVITY, GROUPNAME, FIRSTNAME, LASTNAME FROM \"USER\" WHERE USERNAME = ?");
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                activity = resultSet.getBoolean("ACTIVITY");
                group = resultSet.getString("GROUPNAME");
                firstName = resultSet.getString("FIRSTNAME");
                lastName = resultSet.getString("LASTNAME");
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
                    "UPDATE \"USER\" SET ACTIVITY=?, FIRSTNAME=?, LASTNAME=? WHERE USERNAME=?");
            preparedStatement.setBoolean(1, user.isActive());
            preparedStatement.setString(2, user.getFirstName());
            preparedStatement.setString(3, user.getLastName());
            preparedStatement.setString(4, username);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public synchronized void setUserPassword(String username, String password) {
        try (Connection connection = DriverManager.getConnection(url)) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE \"USER\" SET PASSWORD=? WHERE USERNAME=?");
            preparedStatement.setString(1, sha256(password));
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
                    "SELECT USERNAME, ACTIVITY, GROUPNAME, FIRSTNAME, LASTNAME FROM \"USER\"");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                username = resultSet.getString("USERNAME");
                activity = resultSet.getBoolean("ACTIVITY");
                group = resultSet.getString("GROUPNAME");
                firstName = resultSet.getString("FIRSTNAME");
                lastName = resultSet.getString("LASTNAME");
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
