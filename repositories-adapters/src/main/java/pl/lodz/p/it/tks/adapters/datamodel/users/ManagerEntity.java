package pl.lodz.p.it.tks.adapters.datamodel.users;

import lombok.ToString;

import javax.persistence.Entity;

@Entity(name = "Manager")
@ToString(callSuper = true, includeFieldNames = false)
public class ManagerEntity extends UserEntity {

    public ManagerEntity() {
        super();
    }

    public ManagerEntity(String username, String password, String firstName, String lastName, boolean active) {
        super(username, password, firstName, lastName, active);
    }
}
