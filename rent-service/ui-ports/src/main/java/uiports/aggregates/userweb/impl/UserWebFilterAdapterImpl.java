package uiports.aggregates.userweb.impl;

import pl.lodz.p.it.model.users.AdminWeb;
import pl.lodz.p.it.model.users.ClientWeb;
import pl.lodz.p.it.model.users.ManagerWeb;
import pl.lodz.p.it.tks.appservices.services.user.UserFilterService;
import uiports.aggregates.userweb.UserWebFilterAdapter;
import uiports.converters.UserWebConverter;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@RequestScoped
public class UserWebFilterAdapterImpl implements UserWebFilterAdapter {

    @Inject
    private UserFilterService userFilterService;

    @Override
    public List<AdminWeb> filterAdmins(String adminFilter) {
        return UserWebConverter.domainToWebAdmins(userFilterService.filterAdmins(adminFilter));
    }

    @Override
    public List<ManagerWeb> filterManagers(String managerFilter) {
        return UserWebConverter.domainToWebManagers(userFilterService.filterManagers(managerFilter));
    }

    @Override
    public List<ClientWeb> filterClients(String clientFilter) {
        return UserWebConverter.domainToWebClients(userFilterService.filterClients(clientFilter));
    }
}
