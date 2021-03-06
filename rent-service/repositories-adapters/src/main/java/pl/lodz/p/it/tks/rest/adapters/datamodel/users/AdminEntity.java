package pl.lodz.p.it.tks.rest.adapters.datamodel.users;

import lombok.ToString;

import javax.persistence.Entity;

@Entity(name = "Admin")
@ToString(callSuper = true, includeFieldNames = false)
public class AdminEntity extends UserEntity {

    public AdminEntity() {
        super();
    }

    public AdminEntity(String username,  String firstName, String lastName, boolean active) {
        super(username, firstName, lastName, active);
    }
}
