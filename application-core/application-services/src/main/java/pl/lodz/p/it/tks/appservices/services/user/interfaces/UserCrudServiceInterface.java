package pl.lodz.p.it.tks.appservices.services.user.interfaces;

import pl.lodz.p.it.tks.domainmodel.users.Admin;
import pl.lodz.p.it.tks.domainmodel.users.Client;
import pl.lodz.p.it.tks.domainmodel.users.Manager;
import pl.lodz.p.it.tks.domainmodel.users.User;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public interface UserCrudServiceInterface {

    public void addAdmin(String username, boolean active, String firstName, String lastName, String password);

    public void addManager(String username, boolean active, String firstName, String lastName, String password);

    public void addClient(String username, boolean active, String firstName, String lastName, String password) ;

    public void updateUser(String username, String firstName, String lastName, boolean active);
}
