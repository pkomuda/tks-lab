package com.pas.zad2mvc;

import com.pas.zad2mvc.repositories.UserRepository;

public abstract class Login
{
    private UserRepository userRepository;
    private boolean userExists;

    public Login()
    {
       userRepository = new UserRepository();
    }

    public UserRepository getUserlist()
    {
        return userRepository;
    }
    public boolean isUserExists()
    {
        return userExists;
    }

    public void setUserExists(boolean userExists)
    {
        this.userExists = userExists;
    }

    public abstract void addUserPool();
    public abstract void checkIfUserExists(String username);
    public abstract String redirect();

    //do sprawdzenia
    public String getOneUsername()
    {
        return userRepository.getUsers().get(0).getUsername();
    }
}
