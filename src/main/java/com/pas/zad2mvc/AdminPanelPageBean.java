package com.pas.zad2mvc;

import javax.inject.Inject;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;

@Named
@ApplicationScoped
public class AdminPanelPageBean
{
    private LoginAdmin loginAdmin;

    @Inject
    public AdminPanelPageBean(LoginAdmin loginAdmin)
    {
        this.loginAdmin = loginAdmin;
    }

    public LoginAdmin getLoginAdmin()
    {
        return loginAdmin;
    }
}
