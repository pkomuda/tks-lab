package soapports.converters;

import pl.lodz.p.it.tks.rest.domainmodel.users.Admin;
import pl.lodz.p.it.tks.rest.domainmodel.users.Client;
import pl.lodz.p.it.tks.rest.domainmodel.users.Manager;
import pl.lodz.p.it.tks.rest.domainmodel.users.User;
import pl.lodz.p.it.tks.soap.services.model.AdminSoap;
import pl.lodz.p.it.tks.soap.services.model.ClientSoap;
import pl.lodz.p.it.tks.soap.services.model.ManagerSoap;
import pl.lodz.p.it.tks.soap.services.model.UserSoap;

import java.util.List;
import java.util.stream.Collectors;

public class UserSoapConverter {

    public static UserSoap domainToSoap(User user) {
        if (user instanceof Admin) {
            return new AdminSoap(
                    user.getUsername(),
                    user.getPassword(),
                    user.getFirstName(),
                    user.getLastName(),
                    user.isActive()
            );
        }
        if (user instanceof Manager) {
            return new ManagerSoap(
                    user.getUsername(),
                    user.getPassword(),
                    user.getFirstName(),
                    user.getLastName(),
                    user.isActive()
            );
        }
        if (user instanceof Client) {
            return new ClientSoap(
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

    public static User soapToDomain(UserSoap user) {
        if (user instanceof AdminSoap) {
            return new Admin(
                    user.getUsername(),
                    user.getPassword(),
                    user.getFirstName(),
                    user.getLastName(),
                    user.isActive()
            );
        }
        if (user instanceof ManagerSoap) {
            return new Manager(
                    user.getUsername(),
                    user.getPassword(),
                    user.getFirstName(),
                    user.getLastName(),
                    user.isActive()
            );
        }
        if (user instanceof ClientSoap) {
            return new Client(
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

    public static List<AdminSoap> domainToSoapAdmins(List<Admin> admins) {
        return admins
                .stream()
                .map(admin -> (AdminSoap) domainToSoap(admin))
                .collect(Collectors.toList());
    }

    public static List<ManagerSoap> domainToSoapManagers(List<Manager> managers) {
        return managers
                .stream()
                .map(manager -> (ManagerSoap) domainToSoap(manager))
                .collect(Collectors.toList());
    }

    public static List<ClientSoap> domainToSoapClients(List<Client> clients) {
        return clients
                .stream()
                .map(client -> (ClientSoap) domainToSoap(client))
                .collect(Collectors.toList());
    }
}
