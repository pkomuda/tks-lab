package pl.lodz.p.it.tks.userservice.datamodel;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(schema = "user_service")
public @Data class UserEntity {

    @Id
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private boolean active;
    @Enumerated(EnumType.STRING)
    private UserType type;
}
