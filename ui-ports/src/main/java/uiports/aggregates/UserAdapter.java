package uiports.aggregates;

import pl.lodz.p.it.model.users.AdminWeb;
import pl.lodz.p.it.model.users.ClientWeb;
import pl.lodz.p.it.model.users.ManagerWeb;
import pl.lodz.p.it.model.users.UserWeb;
import pl.lodz.p.it.tks.appservices.services.user.UserGetService;
import uiports.converters.UserConverter;
import uiports.ports.users.GetUserPort;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@ApplicationScoped
public class UserAdapter implements GetUserPort {

    @Inject
    private UserGetService userGetService;

    @Override
    public UserWeb getUser(String username) {
        return UserConverter.domainToWeb(userGetService.getUser(username));
    }

    @Override
    public List<AdminWeb> getAdmins() {
        return UserConverter.domainToWebAdmins(userGetService.getAdmins());
    }

    @Override
    public List<ManagerWeb> getManagers() {
        return UserConverter.domainToWebManagers(userGetService.getManagers());
    }

    @Override
    public List<ClientWeb> getClients() {
        return UserConverter.domainToWebClients(userGetService.getClients());
    }
}
