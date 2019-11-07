package com.pas.zad2mvc;

public abstract class Login
{
    private UserList userList;
    private boolean userExists;

    public Login()
    {
       userList = new UserList();
    }

    public UserList getUserlist()
    {
        return userList;
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
        return userList.getUsers().get(0).getUsername();
    }
}
