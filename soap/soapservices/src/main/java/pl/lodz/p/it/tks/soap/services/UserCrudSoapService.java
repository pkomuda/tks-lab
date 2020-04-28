package pl.lodz.p.it.tks.soap.services;

import pl.lodz.p.it.tks.soap.model.AdminSoap;
import pl.lodz.p.it.tks.soap.model.ClientSoap;
import pl.lodz.p.it.tks.soap.model.ManagerSoap;
import pl.lodz.p.it.tks.soap.model.UserSoap;

import javax.jws.WebService;

@WebService
public interface UserCrudSoapService {

    void addAdmin(AdminSoap admin);
    void addManager(ManagerSoap manager);
    void addClient(ClientSoap client);
    void updateUser(String username, UserSoap user);
}
