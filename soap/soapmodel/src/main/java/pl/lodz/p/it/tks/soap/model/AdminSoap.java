package pl.lodz.p.it.tks.soap.model;

public class AdminSoap extends UserSoap {

    public AdminSoap() {
        super();
    }

    public AdminSoap(String username, String password, String firstName, String lastName, boolean active) {
        super(username, password, firstName, lastName, active);
    }
}
