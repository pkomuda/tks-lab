package pl.lodz.p.it.tks.adapters.datamodel.users;

import javax.persistence.Entity;

@Entity(name = "Manager")
public class ManagerEntity extends UserEntity {

    public ManagerEntity() {
        super();
    }

    public ManagerEntity(String username, String password, String firstName, String lastName, boolean active) {
        super(username, password, firstName, lastName, active);
    }
}
