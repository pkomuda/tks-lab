package pl.lodz.p.it.tks.appservices.controllers;

import lombok.Data;
import pl.lodz.p.it.tks.domainmodel.catalogs.Catalog;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public @Data class ViewAccessController implements Serializable {

    private Catalog selectedCatalog;
    private String selectedUsername;
}
