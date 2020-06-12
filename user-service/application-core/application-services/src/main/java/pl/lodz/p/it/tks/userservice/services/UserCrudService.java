package pl.lodz.p.it.tks.userservice.services;

import pl.lodz.p.it.tks.userservice.domainmodel.User;

public interface UserCrudService {
    void addUser(User user);
    void updateUser(User user);
}
