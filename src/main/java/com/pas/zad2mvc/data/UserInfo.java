package com.pas.zad2mvc.data;

public class UserInfo {
    private String firstName;
    private String lastName;

    public UserInfo(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public UserInfo(UserInfo other) {
        this.firstName = other.getFirstName();
        this.lastName = other.getLastName();
    }

    //region getters
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
    //endregion

    //region setters
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    //endregion
}
