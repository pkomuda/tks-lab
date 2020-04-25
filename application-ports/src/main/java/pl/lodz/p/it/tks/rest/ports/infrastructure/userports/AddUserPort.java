package pl.lodz.p.it.tks.rest.ports.infrastructure.userports;

import pl.lodz.p.it.tks.rest.domainmodel.users.User;

public interface AddUserPort {

    void addUser(User user);
}
