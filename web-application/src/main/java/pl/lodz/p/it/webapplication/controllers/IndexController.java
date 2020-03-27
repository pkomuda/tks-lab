package pl.lodz.p.it.webapplication.controllers;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class IndexController {

    public String login() {
        return "login";
    }

    public String register() {
        return "register";
    }
}
