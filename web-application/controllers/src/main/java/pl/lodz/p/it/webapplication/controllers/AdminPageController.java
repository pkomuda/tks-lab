package pl.lodz.p.it.webapplication.controllers;

import lombok.Data;
import pl.lodz.p.it.model.users.AdminWeb;
import pl.lodz.p.it.model.users.ClientWeb;
import pl.lodz.p.it.model.users.ManagerWeb;
import pl.lodz.p.it.model.users.UserWeb;
import uiports.aggregates.userweb.UserWebFilterAdapter;
import uiports.aggregates.userweb.UserWebGetAdapter;

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
    private UserWebFilterAdapter userFilterAdapter;
    @Inject
    private UserWebGetAdapter userGetAdapter;
    @Inject
    private ViewAccessController viewAccessController;

    private List<AdminWeb> admins;
    private List<ManagerWeb> managers;
    private List<ClientWeb> clients;
    private String userFilter;

    public String addUser() {
        return "add";
    }

    public String prepareUserInfo(UserWeb selectedUser) {
        viewAccessController.setSelectedUsername(selectedUser.getUsername());
        return "edit";
    }

    public String prepareRentsInfo(ClientWeb selectedClient) {
        viewAccessController.setSelectedUsername(selectedClient.getUsername());
        return "rentsForClient";
    }

    public void filterUsers() {
        admins = userFilterAdapter.filterAdmins(userFilter);
        managers = userFilterAdapter.filterManagers(userFilter);
        clients = userFilterAdapter.filterClients(userFilter);
    }

    @PostConstruct
    public void loadUsers() {
        admins = userGetAdapter.getAdmins();
        managers = userGetAdapter.getManagers();
        clients = userGetAdapter.getClients();
    }
}
