package pl.lodz.p.it.tks.soap.services;

import pl.lodz.p.it.tks.soap.model.AdminSoap;
import pl.lodz.p.it.tks.soap.model.ClientSoap;
import pl.lodz.p.it.tks.soap.model.ManagerSoap;
import pl.lodz.p.it.tks.soap.model.UserSoap;
import soapports.aggregates.UserSoapCrudAdapter;

import javax.inject.Inject;
import javax.jws.WebService;

@WebService
public class UserCrudSoapService {

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
