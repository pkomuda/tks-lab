package com.pas.zad2mvc;

import com.pas.zad2mvc.data.Admin;
import com.pas.zad2mvc.data.User;

import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;

@Named
@ApplicationScoped
public class LoginAdmin extends Login
{
    public LoginAdmin()
    {
        super();
    }

    @Override
    public void checkIfUserExists(String username)
    {
        for(User user : getUserlist().getUsers())
        {
            if(username.equals(user.getUsername())/* && user.getClass().getName().equals("com.pas.zad2mvc.data.Admin")*/)
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
        users.add(new Admin("pokazny", true));
        users.add(new Admin("chefe", true));
//        if (!getUserlist().getUsers().containsAll(users))
            getUserlist().getUsers().addAll(users);
    }

    @Override
    public String redirect()
    {
        if (isUserExists())
        {
            AdminPanelPageBean admin = new AdminPanelPageBean(this);
            return "adduser.xhtml";
        }
        return "adminlogin.xhtml";
    }
}
