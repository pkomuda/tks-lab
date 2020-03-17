package pl.lodz.p.it.tks.ports.infrastructure;

import pl.lodz.p.it.tks.adapters.datamodel.users.AdminEntity;
import pl.lodz.p.it.tks.adapters.datamodel.users.ClientEntity;
import pl.lodz.p.it.tks.adapters.datamodel.users.ManagerEntity;
import pl.lodz.p.it.tks.adapters.datamodel.users.UserEntity;
import pl.lodz.p.it.tks.domainmodel.users.Admin;
import pl.lodz.p.it.tks.domainmodel.users.Client;
import pl.lodz.p.it.tks.domainmodel.users.Manager;
import pl.lodz.p.it.tks.domainmodel.users.User;

import javax.enterprise.context.Dependent;
import javax.inject.Named;

@Named
@Dependent
public class UserOutput {
    
    public UserEntity convert(User user) {
        if (user instanceof Admin) {
            return new AdminEntity(
                    user.getUsername(),
                    user.getPassword(),
                    user.getFirstName(),
                    user.getLastName(),
                    user.isActive()
            );
        } else if (user instanceof Manager) {
            return new ManagerEntity(
                    user.getUsername(),
                    user.getPassword(),
                    user.getFirstName(),
                    user.getLastName(),
                    user.isActive()
            );
        } else if (user instanceof Client) {
            return new ClientEntity(
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
}
