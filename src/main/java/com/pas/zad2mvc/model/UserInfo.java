package com.pas.zad2mvc.model;

public class UserInfo {
    private String firstName;
    private String lastName;

    UserInfo(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
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
