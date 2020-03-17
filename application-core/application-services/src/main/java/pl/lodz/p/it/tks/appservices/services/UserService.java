package pl.lodz.p.it.tks.appservices.services;

import pl.lodz.p.it.tks.domainmodel.users.Admin;
import pl.lodz.p.it.tks.domainmodel.users.Client;
import pl.lodz.p.it.tks.domainmodel.users.Manager;
import pl.lodz.p.it.tks.domainmodel.users.User;
import pl.lodz.p.it.tks.ports.aggregates.UserAdapter;

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
    private UserAdapter userAdapter;

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
            userAdapter.addUser(new Admin(username, sha256(password), firstName, lastName, active));
        }
    }

    public void addManager(String username, boolean active, String firstName, String lastName, String password) {
        if (getUser(username) == null) {
            userAdapter.addUser(new Manager(username, sha256(password), firstName, lastName, active));
        }
    }

    public void addClient(String username, boolean active, String firstName, String lastName, String password) {
        if (getUser(username) == null) {
            userAdapter.addUser(new Client(username, sha256(password), firstName, lastName, active));
        }
    }

    public User getUser(String username) {
        return userAdapter.getUser(username);
    }

    public List<Admin> getAdmins() {
        return userAdapter.getAdmins();
    }

    public List<Manager> getManagers() {
        return userAdapter.getManagers();
    }

    public List<Client> getClients() {
        return userAdapter.getClients();
    }

    public List<Admin> filterAdmins(String adminFilter) {
        return userAdapter.filterAdmins(adminFilter);
    }

    public List<Manager> filterManagers(String managerFilter) {
        return userAdapter.filterManagers(managerFilter);
    }

    public List<Client> filterClients(String clientFilter) {
        return userAdapter.filterClients(clientFilter);
    }

    public void updateUser(String username, String firstName, String lastName, boolean active) {
        if (getUser(username) != null) {
            User temp = getUser(username);
            temp.setFirstName(firstName);
            temp.setLastName(lastName);
            temp.setActive(active);
            userAdapter.updateUser(username, temp);
        }
    }
}
