package com.pas.zad2mvc.controllers;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class ViewAccessController implements Serializable {
    private int selectedCatalogId;
    private String selectedUsername;

    //region getters
    int getSelectedCatalogId() {
        return selectedCatalogId;
    }

    String getSelectedUsername() {
        return selectedUsername;
    }
    //endregion

    //region setters
    void setSelectedCatalogId(int selectedCatalogId) {
        this.selectedCatalogId = selectedCatalogId;
    }

    void setSelectedUsername(String selectedUsername) {
        this.selectedUsername = selectedUsername;
    }
    //endregion
}
