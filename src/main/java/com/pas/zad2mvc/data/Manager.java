package com.pas.zad2mvc.data;

public class Manager extends User
{
    public Manager(String username, boolean active) { super(username, active); }

    @Override
    public String toString()
    {
        return "Manager(username: " + getUsername() + ", active: " + isActive() + ")";
    }
}
