package pl.lodz.p.it.tks.appservices.services.user.interfaces;

import pl.lodz.p.it.tks.domainmodel.users.Admin;
import pl.lodz.p.it.tks.domainmodel.users.Client;
import pl.lodz.p.it.tks.domainmodel.users.Manager;

import java.util.List;

interface UserFilterService {

    List<Admin> filterAdmins(String adminFilter);
    List<Manager> filterManagers(String managerFilter);
    List<Client> filterClients(String clientFilter);
}
