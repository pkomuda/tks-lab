package com.pas.zad2mvc.controllers;

import com.pas.zad2mvc.model.Rent;
import com.pas.zad2mvc.services.CatalogService;
import com.pas.zad2mvc.services.RentService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@RequestScoped
public class RentsForCatalogController {
    @Inject
    private RentService rentService;
    @Inject
    private CatalogService catalogService;
    @Inject
    private ViewAccessController viewAccessController;
    private List<Rent> unfinishedRents;
    private List<Rent> finishedRents;
    private int id;
    private String title;
    private String rentFilter;

    public void filterRents() {
        unfinishedRents = rentService.getUnfinishedRentsForCatalog(id);
        finishedRents = rentService.getFinishedRentsForCatalog(id);
    }

    public String goBack() {
        return "back";
    }

    //region getters
    public List<Rent> getUnfinishedRents() {
        return unfinishedRents;
    }

    public List<Rent> getFinishedRents() {
        return finishedRents;
    }

    public String getTitle() {
        return title;
    }

    public String getRentFilter() {
        return rentFilter;
    }
    //endregion

    public void setRentFilter(String rentFilter) {
        this.rentFilter = rentFilter;
    }

    @PostConstruct
    public void loadData() {
        id = viewAccessController.getSelectedCatalogId();
        title = catalogService.getCatalog(id).getTitle();
        unfinishedRents = rentService.getUnfinishedRentsForCatalog(viewAccessController.getSelectedCatalogId());
        finishedRents = rentService.getFinishedRentsForCatalog(viewAccessController.getSelectedCatalogId());
    }
}
