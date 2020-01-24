package com.pas.zad2mvc.model.users;

public class Client extends User {
    //region constructors
    public Client(String username, boolean active, String firstName, String lastName) {
        super(username, active, firstName, lastName);
    }

    public Client(User other) {
        super(other);
    }
    //endregion

    @Override
    public String getType() {
        return "Client";
    }

    @Override
    public String toString() {
        return "Client(username: " + getUsername() + ", active: " + isActive() + ", firstName: " + getFirstName() + ", lastName: " + getLastName() + ")";
    }
}
