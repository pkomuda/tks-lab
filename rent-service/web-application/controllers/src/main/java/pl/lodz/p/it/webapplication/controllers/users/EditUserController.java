package pl.lodz.p.it.webapplication.controllers.users;

import lombok.Data;
import pl.lodz.p.it.model.users.AdminWeb;
import pl.lodz.p.it.model.users.ClientWeb;
import pl.lodz.p.it.model.users.ManagerWeb;
import pl.lodz.p.it.model.users.UserWeb;
import pl.lodz.p.it.webapplication.controllers.ViewAccessController;
import pl.lodz.p.it.webapplication.controllers.mq.RabbitPublisher;
import pl.lodz.p.it.webapplication.controllers.mq.RabbitRpcClient;
import uiports.aggregates.userweb.UserWebCrudAdapter;
import uiports.aggregates.userweb.UserWebGetAdapter;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public @Data class EditUserController {

    @Inject
    private RabbitPublisher rabbitPublisher;
    @Inject
    private RabbitRpcClient rabbitRpcClient;

    @Inject
    private UserWebCrudAdapter userCrudAdapter;
    @Inject
    private UserWebGetAdapter userGetAdapter;
    @Inject
    private ViewAccessController viewAccessController;
    private String username;
    private boolean active;
    private String firstName;
    private String lastName;
    private String password;
    private UserWeb user;

    public String confirmEditUser() {
        UserWeb temp = null;
        if (user instanceof AdminWeb) {
            temp = new AdminWeb(username, password, firstName, lastName, active);
        } else if (user instanceof ManagerWeb) {
            temp = new ManagerWeb(username, password, firstName, lastName, active);
        } else if (user instanceof ClientWeb) {
            temp = new ClientWeb(username, password, firstName, lastName, active);
        }
        userCrudAdapter.updateUser(username, temp);
        rabbitPublisher.publish(temp, "user.edit");
        return "admin";
    }

    public String cancel() {
        return "cancel";
    }

    @PostConstruct
    public void loadUserInfo() {
        username = viewAccessController.getSelectedUsername();
        user = rabbitRpcClient.get(username);
        active = user.isActive();
        password = user.getPassword();
    }
}
