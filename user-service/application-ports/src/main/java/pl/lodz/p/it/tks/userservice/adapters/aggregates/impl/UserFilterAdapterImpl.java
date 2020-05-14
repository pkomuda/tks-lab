package pl.lodz.p.it.tks.userservice.adapters.aggregates.impl;

import pl.lodz.p.it.tks.userservice.adapters.aggregates.UserFilterAdapter;
import pl.lodz.p.it.tks.userservice.adapters.converters.UserConverter;
import pl.lodz.p.it.tks.userservice.domainmodel.User;
import pl.lodz.p.it.tks.userservice.repositories.UserRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.stream.Collectors;

@Named
@ApplicationScoped
public class UserFilterAdapterImpl implements UserFilterAdapter {

    @Inject
    private UserRepository userRepository;

    @Override
    public List<User> filterUsers(String userFilter) {
        return userRepository.filterUsers(userFilter)
                .stream()
                .map(userEntity -> (User) UserConverter.entityToUser(userEntity))
                .collect(Collectors.toList());
    }

}
