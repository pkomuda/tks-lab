package pl.lodz.p.it.tks.rest.domainmodel.users;

public class Admin extends User {

    public Admin() {
        super();
    }

    public Admin(String username,  String firstName, String lastName, boolean active) {
        super(username, firstName, lastName, active);
    }
}
