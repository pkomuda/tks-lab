package com.pas.zad2mvc.model;

public class UserInfo {
    private String firstName;
    private String lastName;

    //region constructors
    UserInfo(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    UserInfo(UserInfo other) {
        this.firstName = other.getFirstName();
        this.lastName = other.getLastName();
    }
    //endregion

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
