package pl.lodz.p.it.webapplication.controllers;

import lombok.Data;
import pl.lodz.p.it.model.users.AdminWeb;
import pl.lodz.p.it.model.users.ClientWeb;
import pl.lodz.p.it.model.users.ManagerWeb;
import pl.lodz.p.it.model.users.UserWeb;
import pl.lodz.p.it.webapplication.controllers.mq.RabbitRpcClient;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Named
@ViewScoped
public @Data class AdminPageController implements Serializable {

    @Inject
    private RabbitRpcClient rabbitRpcClient;

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
        List<UserWeb> users = rabbitRpcClient.filter(userFilter);
        admins = users.stream().filter(userWeb -> userWeb instanceof AdminWeb).map(userWeb -> (AdminWeb) userWeb).collect(Collectors.toList());
        managers = users.stream().filter(userWeb -> userWeb instanceof ManagerWeb).map(userWeb -> (ManagerWeb) userWeb).collect(Collectors.toList());
        clients = users.stream().filter(userWeb -> userWeb instanceof ClientWeb).map(userWeb -> (ClientWeb) userWeb).collect(Collectors.toList());
    }

    @PostConstruct
    public void loadUsers() {
        List<UserWeb> users = rabbitRpcClient.getAll();
        admins = users.stream().filter(userWeb -> userWeb instanceof AdminWeb).map(userWeb -> (AdminWeb) userWeb).collect(Collectors.toList());
        managers = users.stream().filter(userWeb -> userWeb instanceof ManagerWeb).map(userWeb -> (ManagerWeb) userWeb).collect(Collectors.toList());
        clients = users.stream().filter(userWeb -> userWeb instanceof ClientWeb).map(userWeb -> (ClientWeb) userWeb).collect(Collectors.toList());
    }
}
