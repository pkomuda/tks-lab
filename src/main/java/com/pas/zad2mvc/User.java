package com.pas.zad2mvc;

import java.util.Objects;
import java.util.UUID;

public abstract class User
{
    private String id;
    private String username;
    private boolean active;

    //region constructors
    public User()
    {
        this.id = UUID.randomUUID().toString().replace("-", "");
    }

    public User(String username, boolean active)
    {
        this.id = UUID.randomUUID().toString().replace("-", "");
        this.username = username;
        this.active = active;
    }
    //endregion

    //region getters
    public String getId()
    {
        return id;
    }
    public String getUsername()
    {
        return username;
    }
    public boolean isActive()
    {
        return active;
    }
    //endregion

    //region setters
    public void setId(String id)
    {
        this.id = id;
    }
    public void setUsername(String username)
    {
        this.username = username;
    }
    public void setActive(boolean active)
    {
        this.active = active;
    }
    //endregion

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return username.equals(user.username);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(username);
    }
}
