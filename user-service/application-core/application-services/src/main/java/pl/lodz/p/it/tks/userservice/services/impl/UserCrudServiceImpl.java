package pl.lodz.p.it.tks.userservice.services.impl;

import pl.lodz.p.it.tks.userservice.adapters.aggregates.UserCrudAdapter;
import pl.lodz.p.it.tks.userservice.domainmodel.Type;
import pl.lodz.p.it.tks.userservice.domainmodel.User;
import pl.lodz.p.it.tks.userservice.services.UserCrudService;
import pl.lodz.p.it.tks.userservice.services.UserGetService;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.enterprise.credential.Password;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Named
@Dependent
public class UserCrudServiceImpl implements UserCrudService {


    @Inject
    private UserCrudAdapter userCrudAdapter;


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

    @Override
    public void addUser(String username, boolean active, String firstName, String lastName, String password, Type type) {
        User user = new User();
        user.setActive(active);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPassword(password);
        user.setUsername(username);
        user.setType(type);
        userCrudAdapter.addUser(user);
    }

    @Override
    public void updateUser(String username, String firstName, String lastName, boolean active, Type type) {
        User user = new User();
        user.setActive(active);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setType(type);
        userCrudAdapter.updateUser(username, user);
    }
}
