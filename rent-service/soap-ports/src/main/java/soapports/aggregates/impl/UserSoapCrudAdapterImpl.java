package soapports.aggregates.impl;

import pl.lodz.p.it.tks.appservices.services.user.UserCrudService;
import pl.lodz.p.it.tks.soap.services.model.AdminSoap;
import pl.lodz.p.it.tks.soap.services.model.ClientSoap;
import pl.lodz.p.it.tks.soap.services.model.ManagerSoap;
import pl.lodz.p.it.tks.soap.services.model.UserSoap;
import soapports.aggregates.UserSoapCrudAdapter;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@Dependent
public class UserSoapCrudAdapterImpl implements UserSoapCrudAdapter {

    @Inject
    private UserCrudService userCrudService;

    @Override
    public void addAdmin(AdminSoap admin) {
        userCrudService.addAdmin(admin.getUsername(),
                admin.isActive(),
                admin.getFirstName(),
                admin.getLastName(),
                admin.getPassword());
    }

    @Override
    public void addManager(ManagerSoap manager) {
        userCrudService.addManager(manager.getUsername(),
                manager.isActive(),
                manager.getFirstName(),
                manager.getLastName(),
                manager.getPassword());
    }

    @Override
    public void addClient(ClientSoap client) {
        userCrudService.addClient(client.getUsername(),
                client.isActive(),
                client.getFirstName(),
                client.getLastName(),
                client.getPassword());
    }

    @Override
    public void updateUser(String username, UserSoap user) {
        userCrudService.updateUser(username,
                user.getFirstName(),
                user.getLastName(),
                user.isActive());
    }
}
