package pl.lodz.p.it.tks.soap.services.model;

public class ClientSoap extends UserSoap {

    public ClientSoap() {
        super();
    }

    public ClientSoap(String username, String password, String firstName, String lastName, boolean active) {
        super(username, password, firstName, lastName, active);
    }
}
