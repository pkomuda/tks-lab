package pl.lodz.p.it.tks.userservice.adapters.aggregates.impl;

import pl.lodz.p.it.tks.userservice.adapters.aggregates.UserGetAdapter;
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
public class UserGetAdapterImpl implements UserGetAdapter {

    @Inject
    private UserRepository userRepository;

    @Override
    public User getUser(String username) {
        return UserConverter.entityToUser(userRepository.getUser(username));
    }

    @Override
    public List<User> getUsers() {
        return userRepository.getUsers()
                .stream()
                .map(userEntity -> (User) UserConverter.entityToUser(userEntity))
                .collect(Collectors.toList());
    }
}
