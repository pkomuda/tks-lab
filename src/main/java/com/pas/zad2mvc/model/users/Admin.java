package com.pas.zad2mvc.model.users;

import java.io.Serializable;

public class Admin extends User implements Serializable {
    //region constructors
    public Admin(String username, boolean active, String firstName, String lastName) {
        super(username, active, firstName, lastName);
    }

    public Admin(User other) {
        super(other);
    }
    //endregion

    @Override
    public String getType() {
        return "Admin";
    }

    @Override
    public String toString() {
        return "Admin(username: " + getUsername() + ", active: " + isActive() + ", firstName: " + getFirstName() + ", lastName: " + getLastName() + ")";
    }
}
