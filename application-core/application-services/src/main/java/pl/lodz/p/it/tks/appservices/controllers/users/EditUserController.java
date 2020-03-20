package pl.lodz.p.it.tks.appservices.controllers.users;

import lombok.Data;
import pl.lodz.p.it.tks.appservices.controllers.ViewAccessController;
import pl.lodz.p.it.tks.appservices.services.UserService;
import pl.lodz.p.it.tks.appservices.services.user.UserCrudServiceInterface;
import pl.lodz.p.it.tks.appservices.services.user.UserGetServiceInterface;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public @Data class EditUserController {

    @Inject
    private UserCrudServiceInterface userService;
    @Inject
    private UserGetServiceInterface userGetService;
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
