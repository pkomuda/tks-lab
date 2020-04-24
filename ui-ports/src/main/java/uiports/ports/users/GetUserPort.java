package uiports.ports.users;

import pl.lodz.p.it.model.users.AdminWeb;
import pl.lodz.p.it.model.users.ClientWeb;
import pl.lodz.p.it.model.users.ManagerWeb;
import pl.lodz.p.it.model.users.UserWeb;

import java.util.List;

public interface GetUserPort {

    UserWeb getUser(String username);
    List<AdminWeb> getAdmins();
    List<ManagerWeb> getManagers();
    List<ClientWeb> getClients();
}
