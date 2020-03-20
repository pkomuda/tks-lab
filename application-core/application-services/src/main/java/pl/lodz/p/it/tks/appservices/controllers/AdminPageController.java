package pl.lodz.p.it.tks.appservices.controllers;

import lombok.Data;
import pl.lodz.p.it.tks.appservices.services.UserService;
import pl.lodz.p.it.tks.appservices.services.user.UserFilterServiceInterface;
import pl.lodz.p.it.tks.appservices.services.user.UserGetServiceInterface;
import pl.lodz.p.it.tks.domainmodel.users.Admin;
import pl.lodz.p.it.tks.domainmodel.users.Client;
import pl.lodz.p.it.tks.domainmodel.users.Manager;
import pl.lodz.p.it.tks.domainmodel.users.User;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public @Data class AdminPageController implements Serializable {

    @Inject
    private UserGetServiceInterface userGetService;
    @Inject
    private UserFilterServiceInterface userFilterService;
    @Inject
    private ViewAccessController viewAccessController;
    private List<Admin> admins;
    private List<Manager> managers;
    private List<Client> clients;
    private String userFilter;

    public String addUser() {
        return "add";
    }

    public String prepareUserInfo(User selectedUser) {
        viewAccessController.setSelectedUsername(selectedUser.getUsername());
        return "edit";
    }

    public String prepareRentsInfo(Client selectedClient) {
        viewAccessController.setSelectedUsername(selectedClient.getUsername());
        return "rentsForClient";
    }

    public void filterUsers() {
        admins = userFilterService.filterAdmins(userFilter);
        managers = userFilterService.filterManagers(userFilter);
        clients = userFilterService.filterClients(userFilter);
    }

    @PostConstruct
    public void loadUsers() {
        admins = userGetService.getAdmins();
        managers = userGetService.getManagers();
        clients = userGetService.getClients();
    }
}
