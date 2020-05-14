package pl.lodz.p.it.tks.userservice.services;

import pl.lodz.p.it.tks.userservice.domainmodel.Type;
import pl.lodz.p.it.tks.userservice.domainmodel.User;

public interface UserCrudService {
    void addUser(String username, boolean active, String firstName, String lastName, String password, Type type);
    void updateUser(String username, String firstName, String lastName, boolean active, Type type);
}
