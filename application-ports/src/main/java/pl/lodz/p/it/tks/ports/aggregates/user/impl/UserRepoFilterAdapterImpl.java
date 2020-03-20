package pl.lodz.p.it.tks.ports.aggregates.user.impl;

import pl.lodz.p.it.tks.adapters.repositories.UserRepository;
import pl.lodz.p.it.tks.domainmodel.users.Admin;
import pl.lodz.p.it.tks.domainmodel.users.Client;
import pl.lodz.p.it.tks.domainmodel.users.Manager;
import pl.lodz.p.it.tks.ports.aggregates.user.UserRepoFilterAdapter;
import pl.lodz.p.it.tks.ports.userinterface.UserInput;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.stream.Collectors;

@Named
@ApplicationScoped
public class UserRepoFilterAdapterImpl implements UserRepoFilterAdapter {

    @Inject
    private UserRepository userRepository;
    @Inject
    private UserInput userInput;

    @Override
    public List<Admin> filterAdmins(String adminFilter) {
        return userRepository.filterAdmins(adminFilter)
                .stream()
                .map(userEntity -> (Admin) userInput.convert(userEntity))
                .collect(Collectors.toList());
    }

    @Override
    public List<Manager> filterManagers(String managerFilter) {
        return userRepository.filterManagers(managerFilter)
                .stream()
                .map(userEntity -> (Manager) userInput.convert(userEntity))
                .collect(Collectors.toList());
    }

    @Override
    public List<Client> filterClients(String clientFilter) {
        return userRepository.filterClients(clientFilter)
                .stream()
                .map(userEntity -> (Client) userInput.convert(userEntity))
                .collect(Collectors.toList());
    }
}
