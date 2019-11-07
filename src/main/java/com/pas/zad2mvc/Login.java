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

    public void setUserlist(UserList userlist)
    {
        this.userlist = userlist;
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

    public String redirect()
    {
        if (userExists)
            return "index.html";
        return "index.html";
    }
    //do sprawdzenia
    public String getOneUsername()
    {
        return userlist.getUsers().get(0).getUsername();
    }
}
