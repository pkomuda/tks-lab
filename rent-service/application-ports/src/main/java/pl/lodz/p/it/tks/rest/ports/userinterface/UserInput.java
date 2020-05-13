package pl.lodz.p.it.tks.rest.ports.userinterface;

import pl.lodz.p.it.tks.rest.adapters.datamodel.users.AdminEntity;
import pl.lodz.p.it.tks.rest.adapters.datamodel.users.ClientEntity;
import pl.lodz.p.it.tks.rest.adapters.datamodel.users.ManagerEntity;
import pl.lodz.p.it.tks.rest.adapters.datamodel.users.UserEntity;
import pl.lodz.p.it.tks.rest.domainmodel.users.Admin;
import pl.lodz.p.it.tks.rest.domainmodel.users.Client;
import pl.lodz.p.it.tks.rest.domainmodel.users.Manager;
import pl.lodz.p.it.tks.rest.domainmodel.users.User;

import javax.enterprise.context.Dependent;
import javax.inject.Named;

@Named
@Dependent
public class UserInput {

    public User convert(UserEntity userEnt) {
        if(userEnt instanceof AdminEntity) {
            return new Admin(
                    userEnt.getUsername(),
                    userEnt.getPassword(),
                    userEnt.getFirstName(),
                    userEnt.getLastName(),
                    userEnt.isActive()
            );
        } else if(userEnt instanceof ClientEntity) {
            return new Client(
                    userEnt.getUsername(),
                    userEnt.getPassword(),
                    userEnt.getFirstName(),
                    userEnt.getLastName(),
                    userEnt.isActive()
            );
        } else if(userEnt instanceof ManagerEntity) {
            return new Manager(
                    userEnt.getUsername(),
                    userEnt.getPassword(),
                    userEnt.getFirstName(),
                    userEnt.getLastName(),
                    userEnt.isActive()
            );
        } else {
            return null;
        }
    }
}
