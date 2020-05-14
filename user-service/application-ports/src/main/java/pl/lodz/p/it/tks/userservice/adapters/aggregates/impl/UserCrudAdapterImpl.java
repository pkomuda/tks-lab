package pl.lodz.p.it.tks.userservice.adapters.aggregates.impl;

import pl.lodz.p.it.tks.userservice.adapters.aggregates.UserCrudAdapter;
import pl.lodz.p.it.tks.userservice.adapters.converters.UserConverter;
import pl.lodz.p.it.tks.userservice.domainmodel.User;
import pl.lodz.p.it.tks.userservice.repositories.UserRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ApplicationScoped
public class UserCrudAdapterImpl implements UserCrudAdapter {

    @Inject
    private UserRepository userRepository;

    @Override
    public void addUser(User user) {
        userRepository.addUser(UserConverter.userToEntity(user));
    }

    @Override
    public void updateUser(String username, User user) {
        userRepository.updateUser(username, UserConverter.userToEntity(user));
    }
}
