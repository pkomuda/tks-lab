package pl.lodz.p.it.tks.userservice.services;

import pl.lodz.p.it.tks.userservice.domainmodel.User;

import java.util.List;

public interface UserFilterService {
    List<User> filterUsers(String userFilter);
}
