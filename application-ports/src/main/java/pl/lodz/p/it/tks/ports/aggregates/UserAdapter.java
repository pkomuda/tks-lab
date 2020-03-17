package pl.lodz.p.it.tks.ports.aggregates;

import pl.lodz.p.it.tks.adapters.repositories.UserRepository;
import pl.lodz.p.it.tks.domainmodel.users.Admin;
import pl.lodz.p.it.tks.domainmodel.users.Client;
import pl.lodz.p.it.tks.domainmodel.users.Manager;
import pl.lodz.p.it.tks.domainmodel.users.User;
import pl.lodz.p.it.tks.ports.infrastructure.UserOutput;
import pl.lodz.p.it.tks.ports.infrastructure.userports.AddUserPort;
import pl.lodz.p.it.tks.ports.infrastructure.userports.UpdateUserPort;
import pl.lodz.p.it.tks.ports.userinterface.UserInput;
import pl.lodz.p.it.tks.ports.userinterface.userports.GetUserPort;
import pl.lodz.p.it.tks.ports.userinterface.userports.GetUsersPort;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.stream.Collectors;

@Named
@ApplicationScoped
public class UserAdapter implements AddUserPort, UpdateUserPort, GetUserPort, GetUsersPort {

    @Inject
    private UserRepository userRepository;
    @Inject
    private UserInput userInput;
    @Inject
    private UserOutput userOutput;

    @Override
    public void addUser(User user) {
        userRepository.addUser(userOutput.convert(user));
    }

    @Override
    public User getUser(String username) {
        return userInput.convert(userRepository.getUser(username));
    }

    @Override
    public void updateUser(String username, User user) {
        userRepository.updateUser(username, userOutput.convert(user));
    }

    @Override
    public List<User> getUsers() {
        return userRepository.getUsers()
                .stream()
                .map(userEntity -> userInput.convert(userEntity))
                .collect(Collectors.toList());
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
