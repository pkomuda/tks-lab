package pl.lodz.p.it.webapplication.controllers.rents;

import lombok.Data;
import pl.lodz.p.it.tks.rest.domainmodel.Rent;
import pl.lodz.p.it.webapplication.controllers.LoginController;
import pl.lodz.p.it.webapplication.controllers.ViewAccessController;
import pl.lodz.p.it.webapplication.controllers.mq.RabbitPublisher;
import uiports.aggregates.rentweb.RentWebCrudAdapter;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public @Data class AddRentController {

    @Inject
    private RabbitPublisher rabbitPublisher;
    @Inject
    private RentWebCrudAdapter rentCrudAdapter;
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
        rentCrudAdapter.addRent(loginController.getUsername(), id, year, month, day, hour, minute);
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
