package pl.lodz.p.it.tks.appservices.services.user;

import pl.lodz.p.it.tks.rest.domainmodel.users.Admin;
import pl.lodz.p.it.tks.rest.domainmodel.users.Client;
import pl.lodz.p.it.tks.rest.domainmodel.users.Manager;

import java.util.List;

public interface UserFilterService {

    List<Admin> filterAdmins(String adminFilter);
    List<Manager> filterManagers(String managerFilter);
    List<Client> filterClients(String clientFilter);
}
