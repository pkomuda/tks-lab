package com.pas.zad2mvc;

import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;

@Named(value = "loginClient")
@ApplicationScoped
public class LoginClient extends Login
{
    public LoginClient()
    {
        super();
    }

    @Override
    public void checkIfUserExists(String username)
    {
        for(User user : getUserlist().getUsers())
        {
            if(username.equals(user.getUsername()) && user.getClass().getName().equals("com.pas.zad2mvc.Client"))
            {
                setUserExists(true);
                return;
            }
        }
        setUserExists(false);
    }

    @Override
    public void addUserPool()
    {
        List<User> users = new ArrayList<>();
        users.add(new Client("herb", true));
        users.add(new Client("papaj", true));
        if (!getUserlist().getUsers().containsAll(users))
            getUserlist().getUsers().addAll(users);
    }

    @Override
    public String redirect()
    {
        if (isUserExists())
            return "clientpanel.xhtml";
        return "clientlogin.xhtml";
    }
}
