package pl.lodz.p.it.tks.ports.userinterface.controllerconverters;

import pl.lodz.p.it.model.users.AdminWeb;
import pl.lodz.p.it.model.users.ClientWeb;
import pl.lodz.p.it.model.users.ManagerWeb;
import pl.lodz.p.it.model.users.UserWeb;
import pl.lodz.p.it.tks.domainmodel.users.Admin;
import pl.lodz.p.it.tks.domainmodel.users.Client;
import pl.lodz.p.it.tks.domainmodel.users.Manager;
import pl.lodz.p.it.tks.domainmodel.users.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserConverter {

    public static UserWeb domainToWeb(User user ) {
        if (user instanceof Admin) {
            return new AdminWeb(
                    user.getUsername(),
                    user.getPassword(),
                    user.getFirstName(),
                    user.getLastName(),
                    user.isActive()
            );
        }
        if (user instanceof Manager) {
            return new ManagerWeb(
                    user.getUsername(),
                    user.getPassword(),
                    user.getFirstName(),
                    user.getLastName(),
                    user.isActive()
            );
        }
        if (user instanceof Client) {
            return new ClientWeb(
                    user.getUsername(),
                    user.getPassword(),
                    user.getFirstName(),
                    user.getLastName(),
                    user.isActive()
            );
        } else {
            return null;
        }
    }

//    public static User webToDomain(UserWeb userWeb) {
//        if (userWeb instanceof AdminWeb) {
//            return new Admin(
//                    userWeb.getUsername(),
//                    userWeb.getPassword(),
//                    userWeb.getFirstName(),
//                    userWeb.getLastName(),
//                    userWeb.isActive()
//            );
//        }
//        if (userWeb instanceof ManagerWeb) {
//            return new Manager(
//                    userWeb.getUsername(),
//                    userWeb.getPassword(),
//                    userWeb.getFirstName(),
//                    userWeb.getLastName(),
//                    userWeb.isActive()
//            );
//        }
//        if (userWeb instanceof ClientWeb) {
//            return new Client(
//                    userWeb.getUsername(),
//                    userWeb.getPassword(),
//                    userWeb.getFirstName(),
//                    userWeb.getLastName(),
//                    userWeb.isActive()
//            );
//        } else {
//            return null;
//        }
//    }

    public static List<AdminWeb> domainToWebAdmins(List<Admin> admins) {
        return admins
                .stream()
                .map(admin -> (AdminWeb) UserConverter.domainToWeb(admin))
                .collect(Collectors.toList());
    }

    public static List<ManagerWeb> domainToWebManagers(List<Manager> managers) {
        return managers
                .stream()
                .map(manager -> (ManagerWeb) UserConverter.domainToWeb(manager))
                .collect(Collectors.toList());
    }

    public static List<ClientWeb> domainToWebClients(List<Client> clients) {
        return clients
                .stream()
                .map(client -> (ClientWeb) UserConverter.domainToWeb(client))
                .collect(Collectors.toList());
    }
}
