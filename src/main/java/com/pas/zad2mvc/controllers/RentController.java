/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pas.zad2mvc.controllers;

import com.pas.zad2mvc.services.CatalogService;
import com.pas.zad2mvc.services.UserService;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author Szymi
 */
@Named
@RequestScoped
public class RentController {

    @Inject 
    private CatalogService catalogService;
    @Inject
    private UserService userService;
    
    public void rent(int catalogId, String username){
        userService.
        catalogService.removeCatalog(catalogId);
        
    }
    
    
    
    /**
     * Creates a new instance of RentController
     */
    
    
}
