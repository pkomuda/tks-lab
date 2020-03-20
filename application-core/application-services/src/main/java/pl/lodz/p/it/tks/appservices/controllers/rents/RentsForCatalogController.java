package pl.lodz.p.it.tks.appservices.controllers.rents;

import lombok.Data;
import pl.lodz.p.it.tks.appservices.controllers.ViewAccessController;
import pl.lodz.p.it.tks.appservices.services.rent.interfaces.RentCrudServiceInterface;
import pl.lodz.p.it.tks.appservices.services.rent.interfaces.RentFilterServiceInterface;
import pl.lodz.p.it.tks.appservices.services.rent.interfaces.RentGetServiceInterface;
import pl.lodz.p.it.tks.domainmodel.Rent;
import pl.lodz.p.it.tks.domainmodel.catalogs.Catalog;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public @Data class RentsForCatalogController implements Serializable {

    @Inject
    private RentCrudServiceInterface rentCrudService;
    @Inject
    private RentGetServiceInterface rentGetService;
    @Inject
    private RentFilterServiceInterface rentFilterService;
    @Inject
    private ViewAccessController viewAccessController;
    private List<Rent> unfinishedRents;
    private List<Rent> finishedRents;
    private int id;
    private String title;
    private String rentFilter;

    public void filterRents() {
        unfinishedRents = rentFilterService.filterUnfinishedRentsForCatalog(id, rentFilter);
        finishedRents = rentFilterService.filterFinishedRentsForCatalog(id, rentFilter);
    }

    public void removeRent(String rentId) {
        rentCrudService.removeRent(rentId);
        loadData();
    }

    public String goBack() {
        return "back";
    }

    @PostConstruct
    public void loadData() {
        Catalog temp = viewAccessController.getSelectedCatalog();
        id = temp.getId();
        title = temp.getTitle();
        unfinishedRents = rentGetService.getUnfinishedRentsForCatalog(id);
        finishedRents = rentGetService.getFinishedRentsForCatalog(id);
    }
}