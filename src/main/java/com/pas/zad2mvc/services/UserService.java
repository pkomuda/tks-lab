package com.pas.zad2mvc.services;

import com.pas.zad2mvc.data.User;
import com.pas.zad2mvc.repositories.UserRepository;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@Dependent
public class UserService implements Serializable
{
    @Inject
    private UserRepository userRepository;

    public List<User> getUsers()
    {
        return userRepository.getUsers();
    }

    public void addAdmin(String username, boolean active)
    {
        userRepository.addAdmin(username, active);
    }

    public void addManager(String username, boolean active)
    {
        userRepository.addManager(username, active);
    }

    public void addClient(String username, boolean active)
    {
        userRepository.addClient(username, active);
    }

    public User getUser(String username)
    {
        return userRepository.get(username);
    }

    public boolean activateUser(String username)
    {
        return userRepository.activate(username);
    }

    public boolean deactivateUser(String username)
    {
        return userRepository.deactivate(username);
    }
}
