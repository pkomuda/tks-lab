/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pas.zad2mvc;

import javax.inject.Named;
import javax.enterprise.context.Dependent;

/**
 *
 * @author Szymi
 */
@Named(value = "login")
@Dependent
public class Login {
      private int no;
      private String page;
    /**
     * Creates a new instance of Login
     */
    public Login() {
  
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
        if(no==1)
        this.page="managerlogin.xhtml";
        if(no==2)
        this.page="adminlogin.xhtml";
        if(no==3)
        this.page="clientlogin.xhtml";
        else 
        this.page="index.html";
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }
    
    
}
