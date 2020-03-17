package pl.lodz.p.it.tks.appservices.controllers.rents;

import lombok.Data;
import pl.lodz.p.it.tks.appservices.controllers.ViewAccessController;
import pl.lodz.p.it.tks.appservices.services.RentService;
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
    private RentService rentService;
    @Inject
    private ViewAccessController viewAccessController;
    private List<Rent> unfinishedRents;
    private List<Rent> finishedRents;
    private String username;
    private String rentFilter;

    public void filterRents() {
        unfinishedRents = rentService.filterUnfinishedRentsForClient(username, rentFilter);
        finishedRents = rentService.filterFinishedRentsForClient(username, rentFilter);
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
        username = viewAccessController.getSelectedUsername();
        unfinishedRents = rentService.getUnfinishedRentsForClient(viewAccessController.getSelectedUsername());
        finishedRents = rentService.getFinishedRentsForClient(viewAccessController.getSelectedUsername());
    }
}
