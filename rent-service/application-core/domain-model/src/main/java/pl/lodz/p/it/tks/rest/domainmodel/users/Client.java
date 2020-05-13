package pl.lodz.p.it.tks.rest.domainmodel.users;

public class Client extends User {

    public Client() {
        super();
    }

    public Client(String username, String password, String firstName, String lastName, boolean active) {
        super(username, password, firstName, lastName, active);
    }
}
