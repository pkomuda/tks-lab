package pl.lodz.p.it.tks.appservices.services.user.interfaces;

import pl.lodz.p.it.tks.domainmodel.users.Admin;
import pl.lodz.p.it.tks.domainmodel.users.Client;
import pl.lodz.p.it.tks.domainmodel.users.Manager;

import java.util.List;

public interface UserFilterServiceInterface {

    public List<Admin> filterAdmins(String adminFilter);

    public List<Manager> filterManagers(String managerFilter);

    public List<Client> filterClients(String clientFilter);

}
