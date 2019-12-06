package com.pas.zad2mvc.controllers;

import com.pas.zad2mvc.model.Rent;
import com.pas.zad2mvc.services.RentService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class RentsForCatalogController {
    @Inject
    private ManagerPageController managerPageController;
    @Inject
    private RentService rentService;
    private List<Rent> rents;
    private String rentFilter;

    //KWESTIA
    public void filterRents() {
        rentService.filterRentsForCatalog(1, rentFilter);
    }

    public String goBack() {
//        managerPageController.endConversation();
        return "back";
    }

    public List<Rent> getFinishedRents() {
        return rents
                .stream()
                .filter(rent -> rent.getReturnDateTime() != null)
                .collect(Collectors.toList());
    }

    public List<Rent> getUnfinishedRents() {
        return rents
                .stream()
                .filter(rent -> rent.getReturnDateTime() == null)
                .collect(Collectors.toList());
    }

    public String getRentFilter() {
        return rentFilter;
    }

    //region setters
    public void setRentFilter(String rentFilter) {
        this.rentFilter = rentFilter;
    }
    //endregion

    @PostConstruct
    public void loadData() {
        rents = rentService.getRentsForCatalog(1);
    }
}
