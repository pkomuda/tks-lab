package soapports.aggregates.impl;

import pl.lodz.p.it.tks.appservices.services.user.UserFilterService;
import pl.lodz.p.it.tks.soap.services.model.AdminSoap;
import pl.lodz.p.it.tks.soap.services.model.ClientSoap;
import pl.lodz.p.it.tks.soap.services.model.ManagerSoap;
import soapports.aggregates.UserSoapFilterAdapter;
import soapports.converters.UserSoapConverter;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@Dependent
public class UserSoapFilterAdapterImpl implements UserSoapFilterAdapter {

    @Inject
    private UserFilterService userFilterService;

    @Override
    public List<AdminSoap> filterAdmins(String adminFilter) {
        return UserSoapConverter.domainToSoapAdmins(userFilterService.filterAdmins(adminFilter));
    }

    @Override
    public List<ManagerSoap> filterManagers(String managerFilter) {
        return UserSoapConverter.domainToSoapManagers(userFilterService.filterManagers(managerFilter));
    }

    @Override
    public List<ClientSoap> filterClients(String clientFilter) {
        return UserSoapConverter.domainToSoapClients(userFilterService.filterClients(clientFilter));
    }
}
