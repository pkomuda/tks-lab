package com.pas.zad2mvc.repositories;

import com.pas.zad2mvc.data.Admin;
import com.pas.zad2mvc.data.Client;
import com.pas.zad2mvc.data.Manager;
import com.pas.zad2mvc.data.User;

import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Named
@ApplicationScoped
public class UserRepository
{
    private LinkedHashMap<String, User> users = new LinkedHashMap<>();

    public List<User> getUsers() { return new ArrayList<>(users.values()); }

    public User get(String username) { return users.get(username); }

    public void addAdmin(String username, boolean active)
    {
        if (get(username) == null)
            users.put(username, new Admin(username, active));
    }

    public void addManager(String username, boolean active)
    {
        if (get(username) == null)
            users.put(username, new Manager(username, active));
    }

    public void addClient(String username, boolean active)
    {
        if (get(username) == null)
            users.put(username, new Client(username, active));
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
                str = str.concat(getUsers().get(i).toString() + "\n");
            else
            {
                str = str.concat("\t\t " + getUsers().get(i).toString());
                if (i != users.size() - 1)
                    str = str.concat("\n");
            }
        }
        return "UserRepo[" + str + "]";
    }

    @PostConstruct
    private void addUserPool()
    {
        addAdmin("admin1", true);
        addAdmin("admin2", true);
        addManager("manager1", true);
        addManager("manager2", true);
        addClient("client1", true);
        addClient("client2", true);
    }
}
