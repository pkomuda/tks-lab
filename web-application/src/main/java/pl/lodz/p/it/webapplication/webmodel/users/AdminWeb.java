package pl.lodz.p.it.webapplication.webmodel.users;

public class AdminWeb extends UserWeb {

    public AdminWeb() {
        super();
    }

    public AdminWeb(String username, String password, String firstName, String lastName, boolean active) {
        super(username, password, firstName, lastName, active);
    }
}