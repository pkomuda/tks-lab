package com.pas.zad2mvc;

import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;

@Named(value = "manager")
@ApplicationScoped
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
        return "Manager(id: " + getId() + ", username: " + getUsername() + ", active: " + isActive() + ")";
    }
}
