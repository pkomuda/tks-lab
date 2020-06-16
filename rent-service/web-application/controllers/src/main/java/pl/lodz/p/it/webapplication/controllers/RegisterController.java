package pl.lodz.p.it.webapplication.controllers;

import lombok.Data;
import pl.lodz.p.it.model.users.ClientWeb;
import pl.lodz.p.it.model.users.UserWeb;
import pl.lodz.p.it.webapplication.controllers.mq.RabbitPublisher;
import uiports.aggregates.userweb.UserWebCrudAdapter;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public @Data class RegisterController {

    @Inject
    private RabbitPublisher rabbitPublisher;

    @Inject
    private UserWebCrudAdapter userCrudAdapter;
    private String username;
    private String password;
    private String firstName;
    private String lastName;

    public String register() {
        UserWeb client = new ClientWeb(username, password, firstName, lastName, false);
        rabbitPublisher.publish(client, "user.create");
//        userCrudAdapter.addUser(client);
        return "registered";
    }
}
