package pl.lodz.p.it.tks.soap.services;

import pl.lodz.p.it.tks.soap.model.AdminSoap;
import pl.lodz.p.it.tks.soap.model.ClientSoap;
import pl.lodz.p.it.tks.soap.model.ManagerSoap;

import javax.jws.WebService;
import java.util.List;

@WebService
public interface UserFilterSoapService {

    List<AdminSoap> filterAdmins(String adminFilter);
    List<ManagerSoap> filterManagers(String managerFilter);
    List<ClientSoap> filterClients(String clientFilter);
}
