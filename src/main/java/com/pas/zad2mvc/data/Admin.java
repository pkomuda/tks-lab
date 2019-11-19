package com.pas.zad2mvc.data;

public class Admin extends User
{
    //region constructors
    public Admin()
    {
        super();
    }
    public Admin(String username, boolean active)
    {
        super(username, active);
    }
    //endregion

    @Override
    public String toString()
    {
        return "Admin(username: " + getUsername() + ", active: " + isActive() + ")";
    }
}
