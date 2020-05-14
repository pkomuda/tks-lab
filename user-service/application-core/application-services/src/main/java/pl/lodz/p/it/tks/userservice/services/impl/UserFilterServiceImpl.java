package pl.lodz.p.it.tks.userservice.services.impl;

import pl.lodz.p.it.tks.userservice.adapters.aggregates.UserFilterAdapter;
import pl.lodz.p.it.tks.userservice.domainmodel.User;
import pl.lodz.p.it.tks.userservice.services.UserFilterService;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@Dependent
public class UserFilterServiceImpl implements UserFilterService {

    @Inject
    private UserFilterAdapter userFilterAdapter;

    @Override
    public List<User> filterUsers(String userFilter) {
        return userFilterAdapter.filterUsers(userFilter);
    }
}
