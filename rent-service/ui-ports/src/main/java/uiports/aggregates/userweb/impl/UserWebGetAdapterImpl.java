package uiports.aggregates.userweb.impl;

import pl.lodz.p.it.model.users.AdminWeb;
import pl.lodz.p.it.model.users.ClientWeb;
import pl.lodz.p.it.model.users.ManagerWeb;
import pl.lodz.p.it.model.users.UserWeb;
import pl.lodz.p.it.tks.appservices.services.user.UserGetService;
import uiports.aggregates.userweb.UserWebGetAdapter;
import uiports.converters.UserWebConverter;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@RequestScoped
public class UserWebGetAdapterImpl implements UserWebGetAdapter {

    @Inject
    private UserGetService userGetService;

    @Override
    public UserWeb getUser(String username) {
        return UserWebConverter.domainToWeb(userGetService.getUser(username));
    }

    @Override
    public List<AdminWeb> getAdmins() {
        return UserWebConverter.domainToWebAdmins(userGetService.getAdmins());
    }

    @Override
    public List<ManagerWeb> getManagers() {
        return UserWebConverter.domainToWebManagers(userGetService.getManagers());
    }

    @Override
    public List<ClientWeb> getClients() {
        return UserWebConverter.domainToWebClients(userGetService.getClients());
    }
}
