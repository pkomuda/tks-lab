package com.pas.zad2mvc.data;

public class Admin extends User
{
    public Admin(String username, boolean active) { super(username, active); }

    @Override
    public String getType() { return "Admin"; }

    @Override
    public String toString() { return "Admin(username: " + getUsername() + ", active: " + isActive() + ")"; }
}
