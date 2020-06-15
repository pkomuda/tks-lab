
package uiports.converters;


import pl.lodz.p.it.model.users.AdminWeb;
import pl.lodz.p.it.model.users.ClientWeb;
import pl.lodz.p.it.model.users.ManagerWeb;
import pl.lodz.p.it.model.users.UserWeb;
import pl.lodz.p.it.tks.rest.domainmodel.users.Admin;
import pl.lodz.p.it.tks.rest.domainmodel.users.Client;
import pl.lodz.p.it.tks.rest.domainmodel.users.Manager;
import pl.lodz.p.it.tks.rest.domainmodel.users.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserWebConverter {

    public static UserWeb domainToWeb(User user ) {
        if (user instanceof Admin) {
            return new AdminWeb(
                    user.getUsername(),
                    "",
                    user.getFirstName(),
                    user.getLastName(),
                    user.isActive()
            );
        }
        if (user instanceof Manager) {
            return new ManagerWeb(
                    user.getUsername(),
                    "",
                    user.getFirstName(),
                    user.getLastName(),
                    user.isActive()
            );
        }
        if (user instanceof Client) {
            return new ClientWeb(
                    user.getUsername(),
                    "",
                    user.getFirstName(),
                    user.getLastName(),
                    user.isActive()
            );
        } else {
            return null;
        }
    }

    public static List<AdminWeb> domainToWebAdmins(List<Admin> admins) {
        return admins
                .stream()
                .map(admin -> (AdminWeb) UserWebConverter.domainToWeb(admin))
                .collect(Collectors.toList());
    }

    public static List<ManagerWeb> domainToWebManagers(List<Manager> managers) {
        return managers
                .stream()
                .map(manager -> (ManagerWeb) UserWebConverter.domainToWeb(manager))
                .collect(Collectors.toList());
    }

    public static List<ClientWeb> domainToWebClients(List<Client> clients) {
        return clients
                .stream()
                .map(client -> (ClientWeb) UserWebConverter.domainToWeb(client))
                .collect(Collectors.toList());
    }
}
