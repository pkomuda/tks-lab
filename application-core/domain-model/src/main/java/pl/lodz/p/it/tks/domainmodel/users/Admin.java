package pl.lodz.p.it.tks.domainmodel.users;

public class Admin extends User {

    public Admin() {
        super();
    }

    public Admin(String username, String password, String firstName, String lastName, boolean active) {
        super(username, password, firstName, lastName, active);
    }
}
