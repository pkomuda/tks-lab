package com.pas.zad2mvc.data;

public class Manager extends User {
    //region constructors
    public Manager(String username, boolean active, String firstName, String lastName) {
        super(username, active, firstName, lastName);
    }

    public Manager(User other) {
        super(other);
    }
    //endregion

    @Override
    public String getType() {
        return "Manager";
    }

    @Override
    public String toString() {
        return "Manager(username: " + getUsername() + ", active: " + isActive() + ", firstName: " + getFirstName() + ", lastName: " + getLastName() + ")";
    }
}
