package pl.lodz.p.it.tks.appservices.services.user.impl;

import pl.lodz.p.it.model.users.AdminWeb;
import pl.lodz.p.it.model.users.ClientWeb;
import pl.lodz.p.it.model.users.ManagerWeb;
import pl.lodz.p.it.tks.appservices.services.user.UserFilterService;
import pl.lodz.p.it.tks.appservices.services.user.UserFilterServiceView;
import pl.lodz.p.it.tks.ports.userinterface.controller.converters.UserConverter;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

public class UserFilterServiceViewImpl implements Serializable, UserFilterServiceView {

    @Inject
    UserFilterService userFilterService;

    public List<AdminWeb> filterAdmins(String adminFilter) {
        return UserConverter.domainToWebAdmins(userFilterService.filterAdmins(adminFilter));
    }

    public List<ManagerWeb> filterManagers(String managerFilter) {
        return UserConverter.domainToWebManagers(userFilterService.filterManagers(managerFilter));
    }

    public List<ClientWeb> filterClients(String clientFilter) {
        return UserConverter.domainToWebClients(userFilterService.filterClients(clientFilter));
    }
}
