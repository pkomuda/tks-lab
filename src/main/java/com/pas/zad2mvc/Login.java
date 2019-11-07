package com.pas.zad2mvc;

public abstract class Login {
     
    private UserList userlist;
    private boolean userExists;

    public Login()
    {
       userlist = new UserList();
    }

    public UserList getUserlist()
    {
        return userlist;
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
        return userlist.getUsers().get(0).getUsername();
    }
}
