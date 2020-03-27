package pl.lodz.p.it.tks.ports.web;

import pl.lodz.p.it.tks.adapters.datamodel.users.AdminEntity;
import pl.lodz.p.it.tks.adapters.datamodel.users.ClientEntity;
import pl.lodz.p.it.tks.adapters.datamodel.users.ManagerEntity;
import pl.lodz.p.it.tks.adapters.datamodel.users.UserEntity;
import pl.lodz.p.it.tks.domainmodel.users.Admin;
import pl.lodz.p.it.tks.domainmodel.users.Client;
import pl.lodz.p.it.tks.domainmodel.users.Manager;
import pl.lodz.p.it.tks.domainmodel.users.User;
import pl.lodz.p.it.webapplication.webmodel.users.AdminWeb;
import pl.lodz.p.it.webapplication.webmodel.users.ClientWeb;
import pl.lodz.p.it.webapplication.webmodel.users.ManagerWeb;
import pl.lodz.p.it.webapplication.webmodel.users.UserWeb;

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
    public static User webToDomain(UserWeb userWeb) {
        if (userWeb instanceof AdminWeb) {
            return new Admin(
                    userWeb.getUsername(),
                    userWeb.getPassword(),
                    userWeb.getFirstName(),
                    userWeb.getLastName(),
                    userWeb.isActive()
            );
        }
        if (userWeb instanceof ManagerWeb) {
            return new Manager(
                    userWeb.getUsername(),
                    userWeb.getPassword(),
                    userWeb.getFirstName(),
                    userWeb.getLastName(),
                    userWeb.isActive()
            );
        }
        if (userWeb instanceof ClientWeb) {
            return new Client(
                    userWeb.getUsername(),
                    userWeb.getPassword(),
                    userWeb.getFirstName(),
                    userWeb.getLastName(),
                    userWeb.isActive()
            );
        } else {
            return null;
        }
    }

        
}
