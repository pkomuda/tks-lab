package com.pas.zad2mvc.controllers;

import com.pas.zad2mvc.model.Book;
import com.pas.zad2mvc.model.Catalog;
import com.pas.zad2mvc.model.Movie;
import com.pas.zad2mvc.model.Rent;
import com.pas.zad2mvc.services.CatalogService;
import com.pas.zad2mvc.services.RentService;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class ClientPageController implements Serializable {
    @Inject
    private CatalogService catalogService;
    @Inject
    private RentService rentService;
    @Inject
    ViewAccessController viewAccessController;
    @Inject
    private LoginController loginController;
    private List<Book> books;
    private List<Movie> movies;
    private List<Rent> unfinishedRents;
    private List<Rent> finishedRents;
    private String catalogFilter;
    private String rentFilter;

    public String prepareRentInfo(Catalog catalog) {
        if (rentService.getUnfinishedRentsForCatalog(catalog.getId()).isEmpty()) {
            viewAccessController.setSelectedCatalogId(catalog.getId());
            return "addRent";
        } else {
            return "clientPage.xhtml";
        }
    }

    public void filterCatalogs() {
        books = catalogService.filterBooks(catalogFilter);
        movies = catalogService.filterMovies(catalogFilter);
    }

    public void filterRentsForClient() {
        unfinishedRents = rentService.filterUnfinishedRentsForClient(loginController.getUsername(), rentFilter);
        finishedRents = rentService.filterFinishedRentsForClient(loginController.getUsername(), rentFilter);
    }

    public void finishRent(String rentId) {
        rentService.finishRent(rentId);
    }

    //region getters
    public String getCatalogStatus(Catalog catalog) {
        if (rentService.getUnfinishedRentsForCatalog(catalog.getId()).isEmpty()) {
            return "Free";
        } else {
            return "Rented";
        }
    }

    public List<Book> getBooks() {
        return books;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public List<Rent> getUnfinishedRents() {
        return unfinishedRents;
    }

    public List<Rent> getFinishedRents() {
        return finishedRents;
    }

    public String getCatalogFilter() {
        return catalogFilter;
    }

    public String getRentFilter() {
        return rentFilter;
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
        books = catalogService.getBooks();
        movies = catalogService.getMovies();
        unfinishedRents = rentService.getUnfinishedRentsForClient(loginController.getUsername());
        finishedRents = rentService.getFinishedRentsForClient(loginController.getUsername());
    }
}
