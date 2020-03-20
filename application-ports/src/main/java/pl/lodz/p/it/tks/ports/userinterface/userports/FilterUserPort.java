package pl.lodz.p.it.tks.ports.userinterface.userports;

import pl.lodz.p.it.tks.domainmodel.users.Admin;
import pl.lodz.p.it.tks.domainmodel.users.Client;
import pl.lodz.p.it.tks.domainmodel.users.Manager;
import pl.lodz.p.it.tks.domainmodel.users.User;

import java.util.List;

public interface FilterUserPort {

    List<Admin> filterAdmins(String adminFilter);
    List<Manager> filterManagers(String managerFilter);
    List<Client> filterClients(String clientFilter);
}
