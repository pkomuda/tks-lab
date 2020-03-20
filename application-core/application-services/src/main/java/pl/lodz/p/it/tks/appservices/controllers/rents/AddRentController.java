package pl.lodz.p.it.tks.appservices.controllers.rents;

import lombok.Data;
import pl.lodz.p.it.tks.appservices.controllers.LoginController;
import pl.lodz.p.it.tks.appservices.controllers.ViewAccessController;
import pl.lodz.p.it.tks.appservices.services.RentService;
import pl.lodz.p.it.tks.appservices.services.rent.RentCrudService;
import pl.lodz.p.it.tks.appservices.services.rent.RentCrudServiceInterface;
import pl.lodz.p.it.tks.appservices.services.rent.RentFilterServiceInterface;
import pl.lodz.p.it.tks.appservices.services.rent.RentGetServiceInterface;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public @Data class AddRentController {

    @Inject
    private RentCrudServiceInterface rentCrudService;
    @Inject
    private ViewAccessController viewAccessController;
    @Inject
    private LoginController loginController;
    private int id;
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;

    public String confirmRent() {
        rentCrudService.addRent(loginController.getUsername(), id, year, month, day, hour, minute);
        return "client";
    }

    public String cancel() {
        return "cancel";
    }

    @PostConstruct
    public void loadCatalogInfo() {
        id = viewAccessController.getSelectedCatalog().getId();
    }
}
