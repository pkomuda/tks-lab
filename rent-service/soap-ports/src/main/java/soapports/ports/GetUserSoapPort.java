package soapports.ports;

import pl.lodz.p.it.tks.soap.services.model.AdminSoap;
import pl.lodz.p.it.tks.soap.services.model.ClientSoap;
import pl.lodz.p.it.tks.soap.services.model.ManagerSoap;
import pl.lodz.p.it.tks.soap.services.model.UserSoap;

import java.util.List;

public interface GetUserSoapPort {

    UserSoap getUser(String username);
    List<AdminSoap> getAdmins();
    List<ManagerSoap> getManagers();
    List<ClientSoap> getClients();
}
