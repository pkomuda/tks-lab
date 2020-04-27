package soapports.ports;

import pl.lodz.p.it.tks.soap.model.AdminSoap;
import pl.lodz.p.it.tks.soap.model.ClientSoap;
import pl.lodz.p.it.tks.soap.model.ManagerSoap;

import java.util.List;

public interface FilterUserSoapPort {

    List<AdminSoap> filterAdmins(String adminFilter);
    List<ManagerSoap> filterManagers(String managerFilter);
    List<ClientSoap> filterClients(String clientFilter);
}
