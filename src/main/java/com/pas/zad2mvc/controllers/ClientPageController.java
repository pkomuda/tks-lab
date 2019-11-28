package com.pas.zad2mvc.controllers;

import com.pas.zad2mvc.data.Book;
import com.pas.zad2mvc.data.Catalog;
import com.pas.zad2mvc.data.Movie;
import com.pas.zad2mvc.services.CatalogService;
import org.apache.commons.lang3.StringUtils;

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
    private Conversation conversation;
    private List<Catalog> catalogs;
    private Catalog selectedCatalog;
    private String catalogFilter;

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

    public void filterCatalogs() {
        catalogs = catalogService.getCatalogs()
                .stream()
                .filter(catalog -> StringUtils.containsIgnoreCase(catalog.toString(), catalogFilter))
                .collect(Collectors.toList());
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
    }
}
