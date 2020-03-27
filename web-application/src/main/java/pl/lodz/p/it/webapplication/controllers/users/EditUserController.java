package pl.lodz.p.it.webapplication.controllers.users;

import lombok.Data;
import pl.lodz.p.it.webapplication.controllers.ViewAccessController;
import pl.lodz.p.it.tks.appservices.services.user.UserCrudService;
import pl.lodz.p.it.tks.appservices.services.user.UserGetService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public @Data class EditUserController {

    @Inject
    private UserCrudService userService;
    @Inject
    private UserGetService userGetService;
    @Inject
    private ViewAccessController viewAccessController;
    private String username;
    private boolean active;
    private String firstName;
    private String lastName;

    public String confirmEditUser() {
        userService.updateUser(username, firstName, lastName, active);
        return "admin";
    }

    public String cancel() {
        return "cancel";
    }

    @PostConstruct
    public void loadUserInfo() {
        username = viewAccessController.getSelectedUsername();
        active = userGetService.getUser(username).isActive();
    }
}
