package uiports.ports.userweb;

import pl.lodz.p.it.model.users.AdminWeb;
import pl.lodz.p.it.model.users.ClientWeb;
import pl.lodz.p.it.model.users.ManagerWeb;

import java.util.List;

public interface FilterUserWebPort {

    List<AdminWeb> filterAdmins(String adminFilter);
    List<ManagerWeb> filterManagers(String managerFilter);
    List<ClientWeb> filterClients(String clientFilter);
}
