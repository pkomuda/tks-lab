package pl.lodz.p.it.tks.ports.userinterface.userports;

import pl.lodz.p.it.tks.domainmodel.users.Admin;
import pl.lodz.p.it.tks.domainmodel.users.Client;
import pl.lodz.p.it.tks.domainmodel.users.Manager;
import pl.lodz.p.it.tks.domainmodel.users.User;

import java.util.List;

public interface GetUserPort {

    User getUser(String username);
    List<User> getUsers();
    List<Admin> getAdmins();
    List<Manager> getManagers();
    List<Client> getClients();
}
