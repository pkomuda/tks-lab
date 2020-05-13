package uiports.ports.userweb;

import pl.lodz.p.it.model.users.UserWeb;

public interface UpdateUserWebPort {

    void updateUser(String username, UserWeb user);
}
