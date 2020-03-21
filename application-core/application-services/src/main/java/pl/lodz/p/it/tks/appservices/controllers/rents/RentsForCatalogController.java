package pl.lodz.p.it.tks.appservices.controllers.rents;

import lombok.Data;
import pl.lodz.p.it.tks.appservices.controllers.ViewAccessController;
import pl.lodz.p.it.tks.appservices.services.rent.RentCrudService;
import pl.lodz.p.it.tks.appservices.services.rent.RentFilterService;
import pl.lodz.p.it.tks.appservices.services.rent.RentGetService;
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
    private RentCrudService rentCrudService;
    @Inject
    private RentGetService rentGetService;
    @Inject
    private RentFilterService rentFilterService;
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
