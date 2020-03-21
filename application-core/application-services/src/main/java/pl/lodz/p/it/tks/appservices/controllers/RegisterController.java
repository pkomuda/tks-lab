package pl.lodz.p.it.tks.appservices.controllers;

import lombok.Data;
import pl.lodz.p.it.tks.appservices.services.user.UserCrudService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public @Data class RegisterController {

    @Inject
    private UserCrudService userService;
    private String username;
    private String password;
    private String firstName;
    private String lastName;

    public String register() {
        userService.addClient(username, false, firstName, lastName, password);
        return "registered";
    }
}
