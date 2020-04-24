package pl.lodz.p.it.tks.ports.aggregates.user.impl;

import pl.lodz.p.it.tks.adapters.repositories.UserRepository;
import pl.lodz.p.it.tks.domainmodel.users.Admin;
import pl.lodz.p.it.tks.domainmodel.users.Client;
import pl.lodz.p.it.tks.domainmodel.users.Manager;
import pl.lodz.p.it.tks.domainmodel.users.User;
import pl.lodz.p.it.tks.ports.aggregates.user.UserRepoGetAdapter;
import pl.lodz.p.it.tks.ports.userinterface.UserInput;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.stream.Collectors;

@Named
@ApplicationScoped
public class UserRepoGetAdapterImpl implements UserRepoGetAdapter {

    @Inject
    private UserRepository userRepository;
    @Inject
    private UserInput userInput;

    @Override
    public User getUser(String username) {
        return userInput.convert(userRepository.getUser(username));
    }

    @Override
    public List<Admin> getAdmins() {
        return userRepository.getAdmins()
                .stream()
                .map(userEntity -> (Admin) userInput.convert(userEntity))
                .collect(Collectors.toList());
    }

    @Override
    public List<Manager> getManagers() {
        return userRepository.getManagers()
                .stream()
                .map(userEntity -> (Manager) userInput.convert(userEntity))
                .collect(Collectors.toList());
    }

    @Override
    public List<Client> getClients() {
        return userRepository.getClients()
                .stream()
                .map(userEntity -> (Client) userInput.convert(userEntity))
                .collect(Collectors.toList());
    }
}
