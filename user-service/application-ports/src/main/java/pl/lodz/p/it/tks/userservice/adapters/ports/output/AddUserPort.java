package pl.lodz.p.it.tks.userservice.adapters.ports.output;

import pl.lodz.p.it.tks.userservice.domainmodel.User;

public interface AddUserPort {
    void addUser(User user);
}
