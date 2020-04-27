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
@WebService
@RequestScoped
public class UserGetSoapService {

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
