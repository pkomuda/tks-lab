package soapports.ports;

import pl.lodz.p.it.tks.soap.model.AdminSoap;
import pl.lodz.p.it.tks.soap.model.ClientSoap;
import pl.lodz.p.it.tks.soap.model.ManagerSoap;

public interface AddUserSoapPort {

    void addAdmin(AdminSoap admin);
    void addManager(ManagerSoap manager);
    void addClient(ClientSoap client);
}
