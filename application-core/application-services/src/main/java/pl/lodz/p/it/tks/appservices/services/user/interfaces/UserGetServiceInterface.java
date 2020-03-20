package pl.lodz.p.it.tks.appservices.services.user;

import pl.lodz.p.it.tks.domainmodel.users.Admin;
import pl.lodz.p.it.tks.domainmodel.users.Client;
import pl.lodz.p.it.tks.domainmodel.users.Manager;
import pl.lodz.p.it.tks.domainmodel.users.User;

import java.util.List;

public interface UserGetServiceInterface {

    public User getUser(String username);

    public List<Admin> getAdmins();

    public List<Manager> getManagers();

    public List<Client> getClients();
}
