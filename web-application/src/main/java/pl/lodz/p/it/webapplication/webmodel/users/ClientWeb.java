package pl.lodz.p.it.webapplication.webmodel.users;

public class ClientWeb extends UserWeb {

    public ClientWeb() {
        super();
    }

    public ClientWeb(String username, String password, String firstName, String lastName, boolean active) {
        super(username, password, firstName, lastName, active);
    }
}
