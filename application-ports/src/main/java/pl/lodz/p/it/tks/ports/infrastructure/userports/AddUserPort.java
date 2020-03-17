package pl.lodz.p.it.tks.ports.infrastructure.userports;

import pl.lodz.p.it.tks.domainmodel.users.User;

public interface AddUserPort {

    void addUser(User user);
}
