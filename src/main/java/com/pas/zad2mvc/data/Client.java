package com.pas.zad2mvc.data;

public class Client extends User
{
    //region constructors
    public Client()
    {
        super();
    }
    public Client(String username, boolean active)
    {
        super(username, active);
    }
    //endregion

    @Override
    public String toString()
    {
        return "Client(username: " + getUsername() + ", active: " + isActive() + ")";
    }
}
