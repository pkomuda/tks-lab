package com.pas.zad2mvc.data;

public class Client extends User {
    public Client(String username, boolean active, String firstName, String lastName) {
        super(username, active, firstName, lastName);
    }

    public Client(User other) {
        super(other);
    }

    @Override
    public String getType() {
        return "Client";
    }

    @Override
    public String toString() {
        return "Client(username: " + getUsername() + ", active: " + isActive() + ")";
    }
}
