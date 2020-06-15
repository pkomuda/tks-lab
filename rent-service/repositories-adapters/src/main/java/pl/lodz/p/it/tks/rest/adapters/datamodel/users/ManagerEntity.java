package pl.lodz.p.it.tks.rest.adapters.datamodel.users;

import lombok.ToString;

import javax.persistence.Entity;

@Entity(name = "Manager")
@ToString(callSuper = true, includeFieldNames = false)
public class ManagerEntity extends UserEntity {

    public ManagerEntity() {
        super();
    }

    public ManagerEntity(String username,  String firstName, String lastName, boolean active) {
        super(username,  firstName, lastName, active);
    }
}
