package pl.lodz.p.it.tks.rest.adapters.datamodel.users;

import lombok.ToString;

import javax.persistence.Entity;

@Entity(name = "Client")
@ToString(callSuper = true, includeFieldNames = false)
public class ClientEntity extends UserEntity {

    public ClientEntity() {
        super();
    }

    public ClientEntity(String username,  String firstName, String lastName, boolean active) {
        super(username,  firstName, lastName, active);
    }
}
