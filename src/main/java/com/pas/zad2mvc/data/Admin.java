package com.pas.zad2mvc.data;

public class Admin extends User {
    public Admin(String username, boolean active, String firstName, String lastName) {
        super(username, active, firstName, lastName);
    }

    public Admin(User other) {
        super(other);
    }

    @Override
    public String getType() {
        return "Admin";
    }

    @Override
    public String toString() {
        return "Admin(username: " + getUsername() + ", active: " + isActive() + ")";
    }
}
