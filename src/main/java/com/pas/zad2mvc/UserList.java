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
        users.add(new Admin(username, active));
    }

    public void addManager(String username, boolean active)
    {
        users.add(new Manager(username, active));
    }

    public void addClient(String username, boolean active)
    {
        users.add(new Client(username, active));
    }

    public User get(String id)
    {
        for (User user : users)
        {
            if (user.getId().equals(id))
                return user;
        }
        throw new NoSuchElementException("No user with id: " + id + " found.");
    }

    public void update(String id, String username, boolean active)
    {
        get(id).setUsername(username);
        get(id).setActive(active);
    }

    public boolean activate(String id)
    {
        if (!get(id).isActive())
        {
            get(id).setActive(true);
            return true;
        }
        return false;
    }

    public boolean deactivate(String id)
    {
        if (get(id).isActive())
        {
            get(id).setActive(false);
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
