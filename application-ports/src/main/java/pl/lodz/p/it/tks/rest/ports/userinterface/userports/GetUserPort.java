package pl.lodz.p.it.tks.rest.ports.userinterface.userports;

import pl.lodz.p.it.tks.rest.domainmodel.users.Admin;
import pl.lodz.p.it.tks.rest.domainmodel.users.Client;
import pl.lodz.p.it.tks.rest.domainmodel.users.Manager;
import pl.lodz.p.it.tks.rest.domainmodel.users.User;

import java.util.List;

public interface GetUserPort {

    User getUser(String username);
    List<Admin> getAdmins();
    List<Manager> getManagers();
    List<Client> getClients();
}
