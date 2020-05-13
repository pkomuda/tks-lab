package soapports.aggregates.impl;

import pl.lodz.p.it.tks.appservices.services.user.UserGetService;
import pl.lodz.p.it.tks.soap.services.model.AdminSoap;
import pl.lodz.p.it.tks.soap.services.model.ClientSoap;
import pl.lodz.p.it.tks.soap.services.model.ManagerSoap;
import pl.lodz.p.it.tks.soap.services.model.UserSoap;
import soapports.aggregates.UserSoapGetAdapter;
import soapports.converters.UserSoapConverter;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@Dependent
public class UserSoapGetAdapterImpl implements UserSoapGetAdapter {

    @Inject
    private UserGetService userGetService;

    @Override
    public UserSoap getUser(String username) {
        return UserSoapConverter.domainToSoap(userGetService.getUser(username));
    }

    @Override
    public List<AdminSoap> getAdmins() {
        return UserSoapConverter.domainToSoapAdmins(userGetService.getAdmins());
    }

    @Override
    public List<ManagerSoap> getManagers() {
        return UserSoapConverter.domainToSoapManagers(userGetService.getManagers());
    }

    @Override
    public List<ClientSoap> getClients() {
        return UserSoapConverter.domainToSoapClients(userGetService.getClients());
    }
}
