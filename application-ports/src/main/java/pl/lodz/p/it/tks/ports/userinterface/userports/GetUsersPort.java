package pl.lodz.p.it.tks.ports.userinterface.userports;

import pl.lodz.p.it.tks.domainmodel.users.Admin;
import pl.lodz.p.it.tks.domainmodel.users.Client;
import pl.lodz.p.it.tks.domainmodel.users.Manager;
import pl.lodz.p.it.tks.domainmodel.users.User;

import java.util.List;

public interface GetUsersPort {

    List<User> getUsers();
    List<Admin> getAdmins();
    List<Manager> getManagers();
    List<Client> getClients();
    List<Admin> filterAdmins(String adminFilter);
    List<Manager> filterManagers(String managerFilter);
    List<Client> filterClients(String clientFilter);
}
