package com.pas.zad2mvc.controllers;

import com.pas.zad2mvc.model.Admin;
import com.pas.zad2mvc.model.Client;
import com.pas.zad2mvc.model.Manager;
import com.pas.zad2mvc.model.User;
import com.pas.zad2mvc.services.UserService;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
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
            externalContext.getSessionMap().put("user", user);
            System.out.println("title: " + request.getAttribute("title"));
            System.out.println("role: " + request.getAttribute("role"));
            if (user != null && user.isActive()) {
                if (user instanceof Admin) {
                    externalContext.redirect(externalContext.getRequestContextPath() + "/admin/adminPage.xhtml");
                } else if (user instanceof Manager) {
                    externalContext.redirect(externalContext.getRequestContextPath() + "/manager/managerPage.xhtml");
                } else if (user instanceof Client) {
                    externalContext.redirect(externalContext.getRequestContextPath() + "/client/clientPage.xhtml");
                } else {
                    externalContext.redirect(externalContext.getRequestContextPath() + "/index.xhtml");
                }
            }
        } catch (ServletException e) {
            context.addMessage(null, new FacesMessage("Unknown login"));
        }
    }

    public void logout() throws IOException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        externalContext.invalidateSession();
        externalContext.redirect(externalContext.getRequestContextPath() + "/index.xhtml");
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Getters/setters for username and password.
}

//package com.pas.zad2mvc.controllers;
//
//import com.pas.zad2mvc.model.Admin;
//import com.pas.zad2mvc.model.Client;
//import com.pas.zad2mvc.model.Manager;
//import com.pas.zad2mvc.model.User;
//import com.pas.zad2mvc.services.UserService;
//
//import javax.enterprise.context.SessionScoped;
//import javax.faces.context.FacesContext;
//import javax.inject.Inject;
//import javax.inject.Named;
//import java.io.Serializable;
//
//@Named
//@SessionScoped
//public class LoginController implements Serializable {
//    @Inject
//    private UserService userService;
//    private String username;
//    private boolean userExists;
//    private User user;
//
//    public void checkIfUserExists(String username) {
//        User temp = userService.getUser(username);
//        if (temp != null
//                && temp.isActive()) {
//            userExists = true;
//            if (temp instanceof Admin) {
//                user = new Admin(temp);
//            } else if (temp instanceof Manager) {
//                user = new Manager(temp);
//            } else if (temp instanceof Client) {
//                user = new Client(temp);
//            } else {
//                userExists = false;
//            }
//        }
//    }
//
//    public String redirect() {
//        if (userExists) {
//            return user.getType();
//        } else {
//            return "success.xhtml";
//        }
//    }
//
//    public String goBack() {
//        return "back";
//    }
//
//    public String logout() {
//        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
//        return "index";
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String name) {
//        this.username = name;
//    }
//}
//