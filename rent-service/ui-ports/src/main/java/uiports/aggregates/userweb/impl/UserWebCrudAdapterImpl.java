package uiports.aggregates.userweb.impl;

import pl.lodz.p.it.model.users.AdminWeb;
import pl.lodz.p.it.model.users.ClientWeb;
import pl.lodz.p.it.model.users.ManagerWeb;
import pl.lodz.p.it.model.users.UserWeb;
import pl.lodz.p.it.tks.appservices.services.user.UserCrudService;
import uiports.aggregates.userweb.UserWebCrudAdapter;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class UserWebCrudAdapterImpl implements UserWebCrudAdapter {

    @Inject
    private UserCrudService userCrudService;

    @Override
    public void addUser(UserWeb user) {
        if (user instanceof AdminWeb) {
            userCrudService.addAdmin(user.getUsername(),
                    user.isActive(),
                    user.getFirstName(),
                    user.getLastName());
        } else if (user instanceof ManagerWeb) {
            userCrudService.addManager(user.getUsername(),
                    user.isActive(),
                    user.getFirstName(),
                    user.getLastName());
        } else if (user instanceof ClientWeb) {
            userCrudService.addClient(user.getUsername(),
                    user.isActive(),
                    user.getFirstName(),
                    user.getLastName());
        }
    }

    @Override
    public void updateUser(String username, UserWeb user) {
        userCrudService.updateUser(username,
                user.getFirstName(),
                user.getLastName(),
                user.isActive());
    }
}
