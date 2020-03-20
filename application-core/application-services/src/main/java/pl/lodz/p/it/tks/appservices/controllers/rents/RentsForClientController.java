package pl.lodz.p.it.tks.appservices.controllers.rents;

import lombok.Data;
import pl.lodz.p.it.tks.appservices.controllers.ViewAccessController;
import pl.lodz.p.it.tks.appservices.services.RentService;
import pl.lodz.p.it.tks.appservices.services.rent.*;
import pl.lodz.p.it.tks.domainmodel.Rent;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public @Data class RentsForClientController implements Serializable {

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
    private String username;
    private String rentFilter;

    public void filterRents() {
        unfinishedRents = rentFilterService.filterUnfinishedRentsForClient(username, rentFilter);
        finishedRents = rentFilterService.filterFinishedRentsForClient(username, rentFilter);
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
        username = viewAccessController.getSelectedUsername();
        unfinishedRents = rentGetService.getUnfinishedRentsForClient(viewAccessController.getSelectedUsername());
        finishedRents = rentGetService.getFinishedRentsForClient(viewAccessController.getSelectedUsername());
    }
}
