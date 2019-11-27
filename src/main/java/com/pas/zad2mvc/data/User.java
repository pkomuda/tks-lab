package com.pas.zad2mvc.data;

import java.util.Objects;

public abstract class User {
    private String username;
    private boolean active;
    private UserInfo info;

    //region constructors
    public User(String username, boolean active, String firstName, String lastName) {
        this.username = username;
        this.active = active;
        this.info = new UserInfo(firstName, lastName);
    }

    public User(User other)
    {
        this.username = other.getUsername();
        this.active = other.isActive();
        this.info = new UserInfo(other.getInfo());
    }
    //endregion

    //region getters
    public String getUsername() {
        return username;
    }

    public boolean isActive() {
        return active;
    }

    public UserInfo getInfo() {
        return info;
    }

    public String getFirstName() {
        return info.getFirstName();
    }

    public String getLastName() {
        return info.getLastName();
    }

    public String getActivity() {
        if (active) {
            return "Active";
        } else {
            return "Inactive";
        }
    }

    public abstract String getType();
    //endregion

    //region setters
    public void setUsername(String username) {
        this.username = username;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setInfo(UserInfo info) {
        this.info = info;
    }

    public void setFirstName(String firstName) {
        info.setFirstName(firstName);
    }

    public void setLastName(String lastName) {
        info.setLastName(lastName);
    }
    //endregion

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return username.equals(user.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }
}
