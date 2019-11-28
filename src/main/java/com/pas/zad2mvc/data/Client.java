package com.pas.zad2mvc.data;
import java.util.ArrayList;
import java.util.List;

public class Client extends User {
    //region constructors
    private List<Rent> rentList;
    
    public Client(String username, boolean active, String firstName, String lastName) {
        super(username, active, firstName, lastName);
        this.rentList=new ArrayList<>();
    }
    public List<Rent> getRentList(){
        return rentList;
    }

    public Client(User other) {
        super(other);
    }
    
    public addRent(int id){
        rentList.add(e)
    }
    public getRent(){
        
    }
    //endregion

    @Override
    public String getType() {
        return "Client";
    }

    @Override
    public String toString() {
        return "Client(username: " + getUsername() + ", active: " + isActive() + ", firstName: " + getFirstName() + ", lastName: " + getLastName() + ")";
    }
}
