package pl.lodz.p.it.tks.soap.services;

import pl.lodz.p.it.tks.soap.model.AdminSoap;
import pl.lodz.p.it.tks.soap.model.ClientSoap;
import pl.lodz.p.it.tks.soap.model.ManagerSoap;
import soapports.aggregates.UserSoapFilterAdapter;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jws.WebService;
import java.util.List;

@Named
@WebService(endpointInterface = "pl.lodz.p.it.tks.soap.services.UserFilterSoapService")
@RequestScoped
public class UserFilterSoapServiceImpl implements UserFilterSoapService {

    @Inject
    private UserSoapFilterAdapter userSoapFilterAdapter;

    @Override
    public List<AdminSoap> filterAdmins(String adminFilter) {
        return userSoapFilterAdapter.filterAdmins(adminFilter);
    }

    @Override
    public List<ManagerSoap> filterManagers(String managerFilter) {
        return userSoapFilterAdapter.filterManagers(managerFilter);
    }

    @Override
    public List<ClientSoap> filterClients(String clientFilter) {
        return userSoapFilterAdapter.filterClients(clientFilter);
    }
}
