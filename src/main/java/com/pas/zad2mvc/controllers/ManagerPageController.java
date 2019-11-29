package com.pas.zad2mvc.controllers;

import com.pas.zad2mvc.data.Book;
import com.pas.zad2mvc.data.Catalog;
import com.pas.zad2mvc.data.Movie;
import com.pas.zad2mvc.data.Rent;
import com.pas.zad2mvc.services.CatalogService;
import com.pas.zad2mvc.services.RentService;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.stream.Collectors;

@Named
@ConversationScoped
public class ManagerPageController implements Serializable {
    @Inject
    private CatalogService catalogService;
    @Inject
    private RentService rentService;
    @Inject
    private Conversation conversation;
    private List<Catalog> catalogs;
    private List<Rent> rents;
    private Catalog selectedCatalog;
    private String catalogFilter;
    private String rentFilter;

    public String prepareCatalogInfo(Catalog selectedCatalog) {
        beginConversation();
        setSelectedCatalog(selectedCatalog);
        if (selectedCatalog instanceof Book) {
            return "editBook";
        } else if (selectedCatalog instanceof Movie) {
            return "editMovie";
        } else {
            return "";
        }
    }

    public void setCatalogService(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    public RentService getRentService() {
        return rentService;
    }

    public void setRentService(RentService rentService) {
        this.rentService = rentService;
    }

    public void setCatalogs(List<Catalog> catalogs) {
        this.catalogs = catalogs;
    }

    public List<Rent> getRents() {
        return rents;
    }

    public void setRents(List<Rent> rents) {
        this.rents = rents;
    }

    public String getRentFilter() {
        return rentFilter;
    }

    public void setRentFilter(String rentFilter) {
        this.rentFilter = rentFilter;
    }

    public void filterCatalogs() {
        catalogs = catalogService.filterCatalogs(catalogFilter);
    }

    public void fiterRents(){
        rents= rentService.filterRents(rentFilter);
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

    public void removeCatalog(int id) {
        catalogService.removeCatalog(id);
        loadCatalogs();
    }

    //region conversation
    public void beginConversation() {
        if (!conversation.isTransient()) {
            conversation.end();
        }
        conversation.begin();
    }

    public void endConversation() {
        conversation.end();
    }
    //endregion

    //region getters
    public CatalogService getCatalogService() {
        return catalogService;
    }

    public List<Catalog> getCatalogs() {
        return catalogs;
    }

    public Catalog getSelectedCatalog() {
        return selectedCatalog;
    }

    public int getSelectedId() {
        return selectedCatalog.getId();
    }

    public String getSelectedTitle() {
        return selectedCatalog.getTitle();
    }

    public String getSelectedAuthor() {
        if (selectedCatalog instanceof Book) {
            return ((Book) selectedCatalog).getAuthor();
        } else {
            return "";
        }
    }

    public List<Rent> getFinishedRents(){
        return rents
                .stream()
                .filter(rent -> rent.getReturnDateTime()!=null)
                .collect(Collectors.toList());
    }

    public List<Rent> getUnfinishedRents(){
        return rents
                .stream()
                .filter(rent -> rent.getReturnDateTime()==null)
                .collect(Collectors.toList());
    }

    public void RemoveRent(String rentId){
        rentService.removeRent(rentId);
    }

    public int getSelectedReleaseYear() {
        return selectedCatalog.getReleaseYear();
    }

    public String getSelectedDirector() {
        if (selectedCatalog instanceof Movie) {
            return ((Movie) selectedCatalog).getDirector();
        } else {
            return "";
        }
    }

    public String getSelectedFormat() {
        if (selectedCatalog instanceof Movie) {
            return ((Movie) selectedCatalog).getFormat();
        } else {
            return "";
        }
    }

    public String getCatalogFilter() {
        return catalogFilter;
    }
    //endregion

    //region setters
    public void setSelectedCatalog(Catalog selectedCatalog) {
        this.selectedCatalog = selectedCatalog;
    }

    public void setCatalogFilter(String catalogFilter) {
        this.catalogFilter = catalogFilter;
    }
    //endregion


    @PostConstruct
    public void loadCatalogs() {
        catalogs = catalogService.getCatalogs();
        rents = rentService.getRents();
    }
}
