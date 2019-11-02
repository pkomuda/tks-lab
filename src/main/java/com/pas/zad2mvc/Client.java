package com.pas.zad2mvc;

import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;

@Named(value = "client")
@ApplicationScoped
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
        return "Client(id: " + getId() + ", username: " + getUsername() + ", active: " + isActive() + ")";
    }
}
