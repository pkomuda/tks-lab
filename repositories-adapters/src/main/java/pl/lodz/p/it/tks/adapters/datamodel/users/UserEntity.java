package pl.lodz.p.it.tks.adapters.datamodel.users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@NoArgsConstructor
@AllArgsConstructor
@Entity(name="Account")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract @Data class UserEntity {

    @Id
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private boolean active;
}
