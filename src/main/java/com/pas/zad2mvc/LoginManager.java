package com.pas.zad2mvc;

import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;

@Named(value = "loginManager")
@ApplicationScoped
public class LoginManager extends Login
{
    public LoginManager()
    {
        super();
    }

    @Override
    public void checkIfUserExists(String username)
    {
        for(User user : getUserlist().getUsers())
        {
            if(username.equals(user.getUsername()) && user.getClass().getName().equals("com.pas.zad2mvc.Manager"))
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
        users.add(new Manager("menago", true));
        users.add(new Manager("wozny", true));
        if (!getUserlist().getUsers().containsAll(users))
            getUserlist().getUsers().addAll(users);
    }

    @Override
    public String redirect()
    {
        if (isUserExists())
            return "index.html";
        return "managerlogin.xhtml";
    }
}
