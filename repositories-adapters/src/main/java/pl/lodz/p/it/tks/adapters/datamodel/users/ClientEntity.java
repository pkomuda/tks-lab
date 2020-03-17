package pl.lodz.p.it.tks.adapters.datamodel.users;

import javax.persistence.Entity;

@Entity(name = "Client")
public class ClientEntity extends UserEntity {

    public ClientEntity() {
        super();
    }

    public ClientEntity(String username, String password, String firstName, String lastName, boolean active) {
        super(username, password, firstName, lastName, active);
    }
}
