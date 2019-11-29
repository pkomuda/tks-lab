package com.pas.zad2mvc.controllers;

import com.pas.zad2mvc.model.Book;
import com.pas.zad2mvc.model.Catalog;
import com.pas.zad2mvc.model.Movie;
import com.pas.zad2mvc.model.Rent;
import com.pas.zad2mvc.services.CatalogService;
import com.pas.zad2mvc.services.RentService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Named
@ConversationScoped
public class ClientPageController implements Serializable {
    @Inject
    private CatalogService catalogService;
    @Inject
    private RentService rentService;
    @Inject
    private LoginController loginController;
    @Inject
    private Conversation conversation;
    private List<Catalog> catalogs;
    private List<Rent> rents;
    private Catalog selectedCatalog;
    private String catalogFilter;
    private String rentFilter;

    public String prepareRentInfo(Catalog catalog) {
        if (rentService.getUnfinishedRentsForCatalog(catalog.getId()).isEmpty()) {
            beginConversation();
            selectedCatalog = catalog;
            return "addRent";
        } else {
            return "clientPage.xhtml";
        }
    }

    public String getCatalogStatus(Catalog catalog) {
        if (rentService.getUnfinishedRentsForCatalog(catalog.getId()).isEmpty()) {
            return "Free";
        } else {
            return "Rented";
        }
    }

    public void filterCatalogs() {
        catalogs = catalogService.filterCatalogs(catalogFilter);
    }

    public void filterRentsForClient() {
        rents = rentService.filterRentsForClient(loginController.getUsername(), rentFilter);
    }

    public void finishRent(String rentId) {
        rentService.finishRent(rentId);
    }

    public List<Book> getBooks() {
        return catalogs
                .stream()
                .filter(catalog -> catalog instanceof Book)
                .map(catalog -> (Book) catalog)
                .collect(Collectors.toList());
    }

    public List<Movie> getMovies() {
        return catalogs
                .stream()
                .filter(catalog -> catalog instanceof Movie)
                .map(catalog -> (Movie) catalog)
                .collect(Collectors.toList());
    }

    public List<Rent> getUnfinishedRents() {
        return rents
                .stream()
                .filter(rent -> rent.getReturnDateTime() == null)
                .collect(Collectors.toList());
    }

    public List<Rent> getFinishedRents() {
        return rents
                .stream()
                .filter(rent -> rent.getReturnDateTime() != null)
                .collect(Collectors.toList());
    }

    //region conversation
    private void beginConversation() {
        if (!conversation.isTransient()) {
            conversation.end();
        }
        conversation.begin();
    }

    void endConversation() {
        conversation.end();
    }
    //endregion

    //region getters
    RentService getRentService() {
        return rentService;
    }

    public String getCatalogFilter() {
        return catalogFilter;
    }

    public String getRentFilter() {
        return rentFilter;
    }

    public int getSelectedCatalogId() {
        return selectedCatalog.getId();
    }
    //endregion

    //region setters
    public void setCatalogFilter(String catalogFilter) {
        this.catalogFilter = catalogFilter;
    }

    public void setRentFilter(String rentFilter) {
        this.rentFilter = rentFilter;
    }
    //endregion

    @PostConstruct
    public void loadData() {
        System.out.println(rentService);
        catalogs = catalogService.getCatalogs();
        rents = rentService.getRentsForClient(loginController.getUsername());
    }
}
