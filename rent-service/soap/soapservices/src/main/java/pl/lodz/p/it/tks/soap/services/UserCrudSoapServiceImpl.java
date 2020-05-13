package pl.lodz.p.it.tks.soap.services;

import pl.lodz.p.it.tks.soap.services.model.AdminSoap;
import pl.lodz.p.it.tks.soap.services.model.ClientSoap;
import pl.lodz.p.it.tks.soap.services.model.ManagerSoap;
import pl.lodz.p.it.tks.soap.services.model.UserSoap;
import soapports.aggregates.UserSoapCrudAdapter;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jws.WebService;

@Named
@WebService(targetNamespace = "http://client.soap.tks.it.p.lodz.pl/", wsdlLocation = "https://raw.githubusercontent.com/pkomuda/tks-lab/master/crud.wsdl")
@RequestScoped
public class UserCrudSoapServiceImpl {

    @Inject
    private UserSoapCrudAdapter userSoapCrudAdapter;

    public void addAdmin(AdminSoap admin) {
        userSoapCrudAdapter.addAdmin(admin);
    }

    public void addManager(ManagerSoap manager) {
        userSoapCrudAdapter.addManager(manager);
    }

    public void addClient(ClientSoap client) {
        userSoapCrudAdapter.addClient(client);
    }

    public void updateUser(String username, UserSoap user) {
        userSoapCrudAdapter.updateUser(username, user);
    }
}
