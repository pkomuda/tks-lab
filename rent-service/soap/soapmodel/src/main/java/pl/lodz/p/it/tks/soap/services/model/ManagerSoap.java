package pl.lodz.p.it.tks.soap.services.model;

public class ManagerSoap extends UserSoap {

    public ManagerSoap() {
        super();
    }

    public ManagerSoap(String username, String password, String firstName, String lastName, boolean active) {
        super(username, password, firstName, lastName, active);
    }
}
