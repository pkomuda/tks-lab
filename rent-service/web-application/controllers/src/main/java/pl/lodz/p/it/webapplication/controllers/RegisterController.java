package pl.lodz.p.it.webapplication.controllers;

import lombok.Data;
import pl.lodz.p.it.model.users.ClientWeb;
import uiports.aggregates.userweb.UserWebCrudAdapter;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public @Data class RegisterController {

    @Inject
    private UserWebCrudAdapter userCrudAdapter;
    private String username;
    private String password;
    private String firstName;
    private String lastName;

    public String register() {
        userCrudAdapter.addUser(new ClientWeb(username, password, firstName, lastName, false));
        return "registered";
    }
}
