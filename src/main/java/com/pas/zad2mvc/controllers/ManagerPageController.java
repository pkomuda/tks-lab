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
        this.selectedCatalog = selectedCatalog;
        if (selectedCatalog instanceof Book) {
            return "editBook";
        } else if (selectedCatalog instanceof Movie) {
            return "editMovie";
        } else {
            return "";
        }
    }

    public String prepareRentsInfo(Catalog selectedCatalog) {
        beginConversation();
        this.selectedCatalog = selectedCatalog;
        return "rentsForCatalog";
    }

    public void filterCatalogs() {
        catalogs = catalogService.filterCatalogs(catalogFilter);
    }

    public void filterRents() {
        rents= rentService.filterRents(rentFilter);
    }

    public void removeCatalog(int id) {
        catalogService.removeCatalog(id);
        loadData();
    }

    public void removeRent(String rentId) {
        rentService.removeRent(rentId);
        loadData();
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
    CatalogService getCatalogService() {
        return catalogService;
    }

    String getSelectedAuthor() {
        if (selectedCatalog instanceof Book) {
            return ((Book) selectedCatalog).getAuthor();
        } else {
            return "";
        }
    }

    int getSelectedReleaseYear() {
        return selectedCatalog.getReleaseYear();
    }

    String getSelectedDirector() {
        if (selectedCatalog instanceof Movie) {
            return ((Movie) selectedCatalog).getDirector();
        } else {
            return "";
        }
    }

    String getSelectedFormat() {
        if (selectedCatalog instanceof Movie) {
            return ((Movie) selectedCatalog).getFormat();
        } else {
            return "";
        }
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

    public int getSelectedId() {
        return selectedCatalog.getId();
    }

    public String getSelectedTitle() {
        return selectedCatalog.getTitle();
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
        catalogs = catalogService.getCatalogs();
        rents = rentService.getRents();
    }
}
