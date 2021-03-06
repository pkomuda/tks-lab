package pl.lodz.p.it.tks.soap.services;

import pl.lodz.p.it.tks.soap.services.model.AdminSoap;
import pl.lodz.p.it.tks.soap.services.model.ClientSoap;
import pl.lodz.p.it.tks.soap.services.model.ManagerSoap;
import soapports.aggregates.UserSoapFilterAdapter;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jws.WebService;
import java.util.List;

@Named
@WebService(targetNamespace = "http://client.soap.tks.it.p.lodz.pl/", wsdlLocation = "https://raw.githubusercontent.com/pkomuda/tks-lab/master/rent-service/soap/soapservices/src/main/java/pl/lodz/p/it/tks/soap/services/filter.wsdl")
@RequestScoped
public class UserFilterSoapServiceImpl {

    @Inject
    private UserSoapFilterAdapter userSoapFilterAdapter;

    public List<AdminSoap> filterAdmins(String adminFilter) {
        return userSoapFilterAdapter.filterAdmins(adminFilter);
    }

    public List<ManagerSoap> filterManagers(String managerFilter) {
        return userSoapFilterAdapter.filterManagers(managerFilter);
    }

    public List<ClientSoap> filterClients(String clientFilter) {
        return userSoapFilterAdapter.filterClients(clientFilter);
    }
}
