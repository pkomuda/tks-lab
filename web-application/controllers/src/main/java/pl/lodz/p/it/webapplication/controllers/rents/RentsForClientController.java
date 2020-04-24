package pl.lodz.p.it.webapplication.controllers.rents;

import lombok.Data;
import pl.lodz.p.it.tks.appservices.services.rent.RentCrudService;
import pl.lodz.p.it.tks.appservices.services.rent.RentFilterService;
import pl.lodz.p.it.tks.appservices.services.rent.RentGetService;
import pl.lodz.p.it.webapplication.controllers.ViewAccessController;
import pl.lodz.p.it.tks.ports.userinterface.controller.converters.RentConverter;
import pl.lodz.p.it.model.RentWeb;


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
    private RentCrudService rentCrudService;
    @Inject
    private RentGetService rentGetService;
    @Inject
    private RentFilterService rentFilterService;
    @Inject
    private ViewAccessController viewAccessController;
    private List<RentWeb> unfinishedRents;
    private List<RentWeb> finishedRents;
    private String username;
    private String rentFilter;

    public void filterRents() {
        unfinishedRents = RentConverter.domainToWebRents(rentFilterService.filterUnfinishedRentsForClient(username, rentFilter));
        finishedRents = RentConverter.domainToWebRents(rentFilterService.filterFinishedRentsForClient(username, rentFilter));
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
        unfinishedRents = RentConverter.domainToWebRents(rentGetService.getUnfinishedRentsForClient(viewAccessController.getSelectedUsername()));
        finishedRents = RentConverter.domainToWebRents(rentGetService.getFinishedRentsForClient(viewAccessController.getSelectedUsername()));
    }
}
