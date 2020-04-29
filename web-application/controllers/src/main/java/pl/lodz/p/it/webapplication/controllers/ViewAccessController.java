package pl.lodz.p.it.webapplication.controllers;

import lombok.Data;
import pl.lodz.p.it.model.catalogs.CatalogWeb;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public @Data class ViewAccessController implements Serializable {

    private CatalogWeb selectedCatalog;
    private String selectedUsername;
}
