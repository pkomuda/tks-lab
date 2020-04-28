package pl.lodz.p.it.tks.soap.services;

import pl.lodz.p.it.tks.soap.model.AdminSoap;
import pl.lodz.p.it.tks.soap.model.ClientSoap;
import pl.lodz.p.it.tks.soap.model.ManagerSoap;
import pl.lodz.p.it.tks.soap.model.UserSoap;

import javax.jws.WebService;
import java.util.List;

@WebService
public interface UserGetSoapService {

    UserSoap getUser(String username);
    List<AdminSoap> getAdmins();
    List<ManagerSoap> getManagers();
    List<ClientSoap> getClients();
}
