package pl.lodz.p.it.webapplication.controllers;

import pl.lodz.p.it.webapplication.controllers.mq.RabbitTemplate;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;

@Named
@RequestScoped
public class IndexController {

    @Inject
    private RabbitTemplate rabbitTemplate;

    public String login() {
        return "login";
    }

    public String register() {
        return "register";
    }

    public void publish() {
        rabbitTemplate.send("wiadomosc", "user.create");
    }
}
