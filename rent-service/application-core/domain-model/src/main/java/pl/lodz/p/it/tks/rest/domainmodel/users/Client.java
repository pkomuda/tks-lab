package pl.lodz.p.it.tks.rest.domainmodel.users;

public class Client extends User {

    public Client() {
        super();
    }

    public Client(String username,  String firstName, String lastName, boolean active) {
        super(username, firstName, lastName, active);
    }
}
