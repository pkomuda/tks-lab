package pl.lodz.p.it.tks.appservices.services.user.impl;

import pl.lodz.p.it.tks.appservices.services.user.UserCrudService;
import pl.lodz.p.it.tks.rest.domainmodel.users.Admin;
import pl.lodz.p.it.tks.rest.domainmodel.users.Client;
import pl.lodz.p.it.tks.rest.domainmodel.users.Manager;
import pl.lodz.p.it.tks.rest.domainmodel.users.User;
import pl.lodz.p.it.tks.rest.ports.aggregates.user.UserRepoCrudAdapter;
import pl.lodz.p.it.tks.rest.ports.aggregates.user.UserRepoGetAdapter;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Named
@Dependent
public class UserCrudServiceImpl implements Serializable, UserCrudService {

    @Inject
    UserRepoCrudAdapter userRepoCrud;
    @Inject
    UserRepoGetAdapter userRepoGet;

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
        if (userRepoGet.getUser(username) == null) {
            userRepoCrud.addUser(new Admin(username, sha256(password), firstName, lastName, active));
        }
    }

    public void addManager(String username, boolean active, String firstName, String lastName, String password) {
        if (userRepoGet.getUser(username) == null) {
            userRepoCrud.addUser(new Manager(username, sha256(password), firstName, lastName, active));
        }
    }

    public void addClient(String username, boolean active, String firstName, String lastName, String password) {
        if (userRepoGet.getUser(username) == null) {
            userRepoCrud.addUser(new Client(username, sha256(password), firstName, lastName, active));
        }
    }

    public void updateUser(String username, String firstName, String lastName, boolean active) {
        if (userRepoGet.getUser(username) != null) {
            User temp = userRepoGet.getUser(username);
            temp.setFirstName(firstName);
            temp.setLastName(lastName);
            temp.setActive(active);
            userRepoCrud.updateUser(username, temp);
        }
    }
}
