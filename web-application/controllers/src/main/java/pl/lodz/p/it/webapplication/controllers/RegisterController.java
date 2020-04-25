package pl.lodz.p.it.webapplication.controllers;

import lombok.Data;
import uiports.aggregates.UserAdapter;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public @Data class RegisterController {

    @Inject
    private UserAdapter userAdapter;
    private String username;
    private String password;
    private String firstName;
    private String lastName;

    public String register() {
        userAdapter.addClient(username, false, firstName, lastName, password);
        return "registered";
    }
}
