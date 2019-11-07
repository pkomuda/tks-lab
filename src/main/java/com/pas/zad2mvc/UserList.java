package com.pas.zad2mvc;

import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

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
        try
        {
            get(username);
        }
        catch (NoSuchElementException e)
        {
            users.add(new Admin(username, active));
            return;
        }
        throw new IllegalArgumentException("User with username: " + username + " already exists.");
    }

    public void addManager(String username, boolean active)
    {
        try
        {
            get(username);
        }
        catch (NoSuchElementException e)
        {
            users.add(new Manager(username, active));
            return;
        }
        throw new IllegalArgumentException("User with username: " + username + " already exists.");
    }

    public void addClient(String username, boolean active)
    {
        try
        {
            get(username);
        }
        catch (NoSuchElementException e)
        {
            users.add(new Client(username, active));
            return;
        }
        throw new IllegalArgumentException("User with username: " + username + " already exists.");
    }

    public User get(String username)
    {
        for (User user : users)
        {
            if (user.getUsername().equals(username))
                return user;
        }
        throw new NoSuchElementException("No user with username: " + username + " found.");
    }

    public boolean activate(String username)
    {
        if (!get(username).isActive())
        {
            get(username).setActive(true);
            return true;
        }
        return false;
    }

    public boolean deactivate(String username)
    {
        if (get(username).isActive())
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
        for (int i=0; i<users.size(); i++)
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
