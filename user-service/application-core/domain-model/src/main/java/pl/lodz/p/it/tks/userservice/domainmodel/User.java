package pl.lodz.p.it.tks.userservice.domainmodel;

import lombok.Data;

import java.io.Serializable;

public @Data class User implements Serializable {

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private boolean active;
    private Type type;
}
