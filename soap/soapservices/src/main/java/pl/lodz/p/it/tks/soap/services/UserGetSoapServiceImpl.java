package pl.lodz.p.it.tks.soap.services;

import pl.lodz.p.it.tks.soap.model.AdminSoap;
import pl.lodz.p.it.tks.soap.model.ClientSoap;
import pl.lodz.p.it.tks.soap.model.ManagerSoap;
import pl.lodz.p.it.tks.soap.model.UserSoap;
import soapports.aggregates.UserSoapGetAdapter;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jws.WebService;
import java.util.List;

@Named
@WebService(endpointInterface = "pl.lodz.p.it.tks.soap.services.UserGetSoapService")
@RequestScoped
public class UserGetSoapServiceImpl implements UserGetSoapService {

    @Inject
    private UserSoapGetAdapter userSoapGetAdapter;

    @Override
    public UserSoap getUser(String username) {
        return userSoapGetAdapter.getUser(username);
    }

    @Override
    public List<AdminSoap> getAdmins() {
        return userSoapGetAdapter.getAdmins();
    }

    @Override
    public List<ManagerSoap> getManagers() {
        return userSoapGetAdapter.getManagers();
    }

    @Override
    public List<ClientSoap> getClients() {
        return userSoapGetAdapter.getClients();
    }
}
