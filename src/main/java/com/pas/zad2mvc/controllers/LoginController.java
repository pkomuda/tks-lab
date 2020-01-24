package com.pas.zad2mvc.controllers;

import com.pas.zad2mvc.model.User;
import com.pas.zad2mvc.services.UserService;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.Serializable;

@Named
@SessionScoped
public class LoginController implements Serializable {
    @Inject
    private UserService userService;
    private String username;
    private String password;

    public void login() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        try {
            request.login(username, password);
            User user = userService.getUser(username);
            if (user != null && user.isActive()) {
                if (request.isUserInRole("ADMIN")) {
                    externalContext.getSessionMap().put("role", "ADMIN");
                    externalContext.redirect(externalContext.getRequestContextPath() + "/admin/adminPage.xhtml");
                } else if (request.isUserInRole("MANAGER")) {
                    externalContext.getSessionMap().put("role", "MANAGER");
                    externalContext.redirect(externalContext.getRequestContextPath() + "/manager/managerPage.xhtml");
                } else if (request.isUserInRole("CLIENT")) {
                    externalContext.getSessionMap().put("role", "CLIENT");
                    externalContext.redirect(externalContext.getRequestContextPath() + "/client/clientPageRest.xhtml");
                } else {
                    externalContext.redirect(externalContext.getRequestContextPath() + "/login.xhtml?error=true");
                }
            }
        } catch (ServletException e) {
            externalContext.redirect(externalContext.getRequestContextPath() + "/login.xhtml?error=true");
        }
    }

    public void logout() throws IOException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        externalContext.invalidateSession();
        externalContext.redirect(externalContext.getRequestContextPath() + "/index.xhtml");
    }

    //region getters
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
    //endregion

    //region setters
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    //endregion
}
