package pl.lodz.p.it.tks.userservice.services.impl;

import pl.lodz.p.it.tks.userservice.adapters.aggregates.UserGetAdapter;
import pl.lodz.p.it.tks.userservice.domainmodel.User;
import pl.lodz.p.it.tks.userservice.services.UserGetService;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@Dependent
public class UserGetServiceImpl implements UserGetService {

    @Inject
    private UserGetAdapter userGetAdapter;


    @Override
    public User getUser(String username) {
        return userGetAdapter.getUser(username);
    }

    @Override
    public List<User> getUsers() {
        return userGetAdapter.getUsers();
    }
}
