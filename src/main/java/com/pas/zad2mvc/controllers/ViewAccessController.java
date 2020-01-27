package com.pas.zad2mvc.controllers;

import com.pas.zad2mvc.model.catalogs.Catalog;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class ViewAccessController implements Serializable {
    private Catalog selectedCatalog;
    private String selectedUsername;

    //region getters
    public Catalog getSelectedCatalog() {
        return selectedCatalog;
    }

    public String getSelectedUsername() {
        return selectedUsername;
    }
    //endregion

    //region setters
    public void setSelectedCatalog(Catalog selectedCatalog) {
        this.selectedCatalog = selectedCatalog;
    }

    public void setSelectedUsername(String selectedUsername) {
        this.selectedUsername = selectedUsername;
    }
    //endregion
}
