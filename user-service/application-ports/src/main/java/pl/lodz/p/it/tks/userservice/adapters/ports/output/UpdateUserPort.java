package pl.lodz.p.it.tks.userservice.adapters.ports.output;

import pl.lodz.p.it.tks.userservice.domainmodel.User;

public interface UpdateUserPort {
    void updateUser(String username, User user);
}
