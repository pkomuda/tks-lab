package pl.lodz.p.it.tks.appservices.controllers.rents;

import lombok.Data;
import pl.lodz.p.it.tks.appservices.controllers.ViewAccessController;
import pl.lodz.p.it.tks.appservices.services.RentService;
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
    private RentService rentService;
    @Inject
    private ViewAccessController viewAccessController;
    private List<Rent> unfinishedRents;
    private List<Rent> finishedRents;
    private int id;
    private String title;
    private String rentFilter;

    public void filterRents() {
        unfinishedRents = rentService.filterUnfinishedRentsForCatalog(id, rentFilter);
        finishedRents = rentService.filterFinishedRentsForCatalog(id, rentFilter);
    }

    public void removeRent(String rentId) {
        rentService.removeRent(rentId);
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
        unfinishedRents = rentService.getUnfinishedRentsForCatalog(id);
        finishedRents = rentService.getFinishedRentsForCatalog(id);
    }
}
