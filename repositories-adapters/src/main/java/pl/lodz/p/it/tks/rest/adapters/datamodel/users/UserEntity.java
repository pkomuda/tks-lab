package pl.lodz.p.it.tks.rest.adapters.datamodel.users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity(name="Account")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@NoArgsConstructor
@AllArgsConstructor
@ToString(includeFieldNames = false)
public abstract @Data class UserEntity {

    @Id
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private boolean active;
}
