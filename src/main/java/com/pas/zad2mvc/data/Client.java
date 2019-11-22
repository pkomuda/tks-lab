package com.pas.zad2mvc.data;

public class Client extends User
{
    public Client(String username, boolean active) { super(username, active); }

    @Override
    public String getType() { return "Client"; }

    @Override
    public String toString() { return "Client(username: " + getUsername() + ", active: " + isActive() + ")"; }
}
