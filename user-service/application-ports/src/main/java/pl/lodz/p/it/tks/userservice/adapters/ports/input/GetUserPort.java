package pl.lodz.p.it.tks.userservice.adapters.ports.input;

import pl.lodz.p.it.tks.userservice.domainmodel.User;

import java.util.List;

public interface GetUserPort {
    User getUser(String username);
    List<User> getUsers();
}
