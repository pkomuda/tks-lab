package pl.lodz.p.it.tks.userservice.domainmodel;

import lombok.Data;

public @Data class User {

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private boolean active;
    private Type type;
}
