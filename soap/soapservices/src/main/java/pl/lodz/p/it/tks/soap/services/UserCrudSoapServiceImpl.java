package pl.lodz.p.it.tks.soap.services;

import pl.lodz.p.it.tks.soap.model.AdminSoap;
import pl.lodz.p.it.tks.soap.model.ClientSoap;
import pl.lodz.p.it.tks.soap.model.ManagerSoap;
import pl.lodz.p.it.tks.soap.model.UserSoap;
import soapports.aggregates.UserSoapCrudAdapter;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jws.WebService;

@Named
@WebService(endpointInterface = "pl.lodz.p.it.tks.soap.services.UserCrudSoapService")
@RequestScoped
public class UserCrudSoapServiceImpl implements UserCrudSoapService {

    @Inject
    private UserSoapCrudAdapter userSoapCrudAdapter;

    @Override
    public void addAdmin(AdminSoap admin) {
        userSoapCrudAdapter.addAdmin(admin);
    }

    @Override
    public void addManager(ManagerSoap manager) {
        userSoapCrudAdapter.addManager(manager);
    }

    @Override
    public void addClient(ClientSoap client) {
        userSoapCrudAdapter.addClient(client);
    }

    @Override
    public void updateUser(String username, UserSoap user) {
        userSoapCrudAdapter.updateUser(username, user);
    }
}
