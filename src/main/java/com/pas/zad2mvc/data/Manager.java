package com.pas.zad2mvc.data;

public class Manager extends User
{
    //region constructors
    public Manager()
    {
        super();
    }
    public Manager(String username, boolean active)
    {
        super(username, active);
    }
    //endregion

    @Override
    public String toString()
    {
        return "Manager(username: " + getUsername() + ", active: " + isActive() + ")";
    }
}
