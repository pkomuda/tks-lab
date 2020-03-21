package pl.lodz.p.it.tks.appservices.services.user;

import pl.lodz.p.it.tks.domainmodel.users.Admin;
import pl.lodz.p.it.tks.domainmodel.users.Client;
import pl.lodz.p.it.tks.domainmodel.users.Manager;
import pl.lodz.p.it.tks.domainmodel.users.User;

import java.util.List;

public interface UserGetService {

    User getUser(String username);
    List<Admin> getAdmins();
    List<Manager> getManagers();
    List<Client> getClients();
}
