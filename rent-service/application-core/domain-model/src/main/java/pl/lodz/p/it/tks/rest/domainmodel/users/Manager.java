package pl.lodz.p.it.tks.rest.domainmodel.users;

public class Manager extends User {

    public Manager() {
        super();
    }

    public Manager(String username,  String firstName, String lastName, boolean active) {
        super(username, firstName, lastName, active);
    }
}
