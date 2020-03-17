package pl.lodz.p.it.tks.domainmodel.users;

public class Manager extends User {

    public Manager() {
        super();
    }

    public Manager(String username, String password, String firstName, String lastName, boolean active) {
        super(username, password, firstName, lastName, active);
    }
}
