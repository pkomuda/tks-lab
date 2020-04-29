package pl.lodz.p.it.tks.soap.services;

import pl.lodz.p.it.tks.soap.services.model.AdminSoap;
import pl.lodz.p.it.tks.soap.services.model.ClientSoap;
import pl.lodz.p.it.tks.soap.services.model.ManagerSoap;
import pl.lodz.p.it.tks.soap.services.model.UserSoap;
import soapports.aggregates.UserSoapGetAdapter;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jws.WebService;
import java.util.List;

@Named
@WebService(targetNamespace = "http://client.soap.tks.it.p.lodz.pl/", wsdlLocation = "https://raw.githubusercontent.com/pkomuda/tks-lab/master/get.wsdl")
@RequestScoped
public class UserGetSoapServiceImpl {

    @Inject
    private UserSoapGetAdapter userSoapGetAdapter;

    public UserSoap getUser(String username) {
        return userSoapGetAdapter.getUser(username);
    }

    public List<AdminSoap> getAdmins() {
        return userSoapGetAdapter.getAdmins();
    }

    public List<ManagerSoap> getManagers() {
        return userSoapGetAdapter.getManagers();
    }

    public List<ClientSoap> getClients() {
        return userSoapGetAdapter.getClients();
    }
}
