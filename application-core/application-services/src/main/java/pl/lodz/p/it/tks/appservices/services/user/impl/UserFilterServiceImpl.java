package pl.lodz.p.it.tks.appservices.services.user.impl;

import pl.lodz.p.it.tks.appservices.services.user.UserFilterService;
import pl.lodz.p.it.tks.domainmodel.users.Admin;
import pl.lodz.p.it.tks.domainmodel.users.Client;
import pl.lodz.p.it.tks.domainmodel.users.Manager;
import pl.lodz.p.it.tks.ports.aggregates.user.UserRepoFilterAdapter;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@Dependent
public class UserFilterServiceImpl implements Serializable, UserFilterService {

    @Inject
    UserRepoFilterAdapter userRepoFilterAdapter;

    public List<Admin> filterAdmins(String adminFilter) {
        return userRepoFilterAdapter.filterAdmins(adminFilter);
    }

    public List<Manager> filterManagers(String managerFilter) {
        return userRepoFilterAdapter.filterManagers(managerFilter);
    }

    public List<Client> filterClients(String clientFilter) {
        return userRepoFilterAdapter.filterClients(clientFilter);
    }
}
