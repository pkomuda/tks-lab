package soapports.ports;

import pl.lodz.p.it.tks.soap.services.model.AdminSoap;
import pl.lodz.p.it.tks.soap.services.model.ClientSoap;
import pl.lodz.p.it.tks.soap.services.model.ManagerSoap;

public interface AddUserSoapPort {

    void addAdmin(AdminSoap admin);
    void addManager(ManagerSoap manager);
    void addClient(ClientSoap client);
}
