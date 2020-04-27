package soapports.ports;

import pl.lodz.p.it.tks.soap.model.AdminSoap;
import pl.lodz.p.it.tks.soap.model.ClientSoap;
import pl.lodz.p.it.tks.soap.model.ManagerSoap;
import pl.lodz.p.it.tks.soap.model.UserSoap;

import java.util.List;

public interface GetUserSoapPort {

    UserSoap getUser(String username);
    List<AdminSoap> getAdmins();
    List<ManagerSoap> getManagers();
    List<ClientSoap> getClients();
}
