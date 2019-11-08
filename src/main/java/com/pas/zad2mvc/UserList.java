package com.pas.zad2mvc;

import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;

@Named(value = "userList")
@ApplicationScoped
public class UserList
{
    private List<User> users;

    public UserList()
    {
        users = new ArrayList<>();
    }

    public List<User> getUsers()
    {
        return users;
    }

    public void addAdmin(String username, boolean active)
    {
        if (get(username) == null)
            users.add(new Admin(username, active));
    }

    public void addManager(String username, boolean active)
    {
        if (get(username) == null)
            users.add(new Manager(username, active));
    }

    public void addClient(String username, boolean active)
    {
        if (get(username) == null)
            users.add(new Client(username, active));
    }

    public User get(String username)
    {
        for (User user : users)
        {
            if (user.getUsername().equals(username))
                return user;
        }
        return null;
    }

    public boolean activate(String username)
    {
        if (get(username) != null && !get(username).isActive())
        {
            get(username).setActive(true);
            return true;
        }
        return false;
    }

    public boolean deactivate(String username)
    {
        if (get(username) != null && get(username).isActive())
        {
            get(username).setActive(false);
            return true;
        }
        return false;
    }

    @Override
    public String toString()
    {
        String str = "";
        for (int i = 0; i < users.size(); i++)
        {
            if (i == 0)
                str = str.concat(users.get(i).toString() + "\n");
            else
            {
                str = str.concat("\t\t " + users.get(i).toString());
                if (i != users.size() - 1)
                    str = str.concat("\n");
            }
        }
        return "UserList[" + str + "]";
    }
}
