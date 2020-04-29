package pl.lodz.p.it.webapplication.controllers.rents;

import lombok.Data;
import pl.lodz.p.it.model.RentWeb;
import pl.lodz.p.it.model.catalogs.CatalogWeb;
import pl.lodz.p.it.webapplication.controllers.ViewAccessController;
import uiports.aggregates.rentweb.RentWebCrudAdapter;
import uiports.aggregates.rentweb.RentWebFilterAdapter;
import uiports.aggregates.rentweb.RentWebGetAdapter;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Named
@ViewScoped
public @Data class RentsForCatalogController implements Serializable {

    @Inject
    private RentWebCrudAdapter rentCrudAdapter;
    @Inject
    private RentWebGetAdapter rentGetAdapter;
    @Inject
    private RentWebFilterAdapter rentFilterAdapter;
    @Inject
    private ViewAccessController viewAccessController;
    private List<RentWeb> unfinishedRents;
    private List<RentWeb> finishedRents;
    private int id;
    private String title;
    private String rentFilter;

    public void filterRents() {
        unfinishedRents = rentFilterAdapter.filterUnfinishedRentsForCatalog(id, rentFilter);
        finishedRents = rentFilterAdapter.filterFinishedRentsForCatalog(id, rentFilter);
    }

    public void removeRent(String rentId) {
        rentCrudAdapter.removeRent(UUID.fromString(rentId));
        loadData();
    }

    public String goBack() {
        return "back";
    }

    @PostConstruct
    public void loadData() {
        CatalogWeb temp = viewAccessController.getSelectedCatalog();
        id = temp.getId();
        title = temp.getTitle();
        unfinishedRents = rentGetAdapter.getUnfinishedRentsForCatalog(id);
        finishedRents = rentGetAdapter.getFinishedRentsForCatalog(id);
    }
}
