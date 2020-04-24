package pl.lodz.p.it.tks.appservices.services.user;



import pl.lodz.p.it.model.users.AdminWeb;
import pl.lodz.p.it.model.users.ClientWeb;
import pl.lodz.p.it.model.users.ManagerWeb;

import java.util.List;

public interface UserFilterServiceView {

    List<AdminWeb> filterAdmins(String adminFilter);
    List<ManagerWeb> filterManagers(String managerFilter);
    List<ClientWeb> filterClients(String clientFilter);
}
