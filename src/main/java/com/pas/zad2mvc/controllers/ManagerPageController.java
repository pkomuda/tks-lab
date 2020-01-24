package com.pas.zad2mvc.controllers;

import com.pas.zad2mvc.model.catalogs.Book;
import com.pas.zad2mvc.model.catalogs.Catalog;
import com.pas.zad2mvc.model.catalogs.Movie;
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
public class ManagerPageController implements Serializable {
    @Inject
    private CatalogService catalogService;
    @Inject
    private RentService rentService;
    @Inject
    private ViewAccessController viewAccessController;
    private List<Book> books;
    private List<Movie> movies;
    private List<Rent> unfinishedRents;
    private List<Rent> finishedRents;
    private String catalogFilter;
    private String rentFilter;

    public String addBook() {
        return "addBook";
    }

    public String addMovie() {
        return "addMovie";
    }

    public String prepareCatalogInfo(Catalog selectedCatalog) {
        viewAccessController.setSelectedCatalogId(selectedCatalog.getId());
        if (selectedCatalog instanceof Book) {
            return "editBook";
        } else if (selectedCatalog instanceof Movie) {
            return "editMovie";
        } else {
            return "";
        }
    }

    public String prepareRentsInfo(Catalog selectedCatalog) {
        viewAccessController.setSelectedCatalogId(selectedCatalog.getId());
        return "rentsForCatalog";
    }

    public void filterCatalogs() {
        books = catalogService.filterBooks(catalogFilter);
        movies = catalogService.filterMovies(catalogFilter);
    }

    public void filterRents() {
        unfinishedRents = rentService.filterUnfinishedRents(rentFilter);
        finishedRents = rentService.filterFinishedRents(rentFilter);
    }

    public void removeCatalog(int id) {
        catalogService.removeCatalog(id);
        loadData();
    }

    public void removeRent(String rentId) {
        rentService.removeRent(rentId);
        loadData();
    }

    //region getters
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
        unfinishedRents = rentService.getUnfinishedRents();
        finishedRents = rentService.getFinishedRents();
    }
}
