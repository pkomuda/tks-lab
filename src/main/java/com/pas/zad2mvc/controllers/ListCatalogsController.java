package com.pas.zad2mvc.controllers;

import com.pas.zad2mvc.data.Book;
import com.pas.zad2mvc.data.Catalog;
import com.pas.zad2mvc.data.Movie;
import com.pas.zad2mvc.services.CatalogService;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.stream.Collectors;

@Named
@ViewScoped
public class ListCatalogsController implements Serializable {
    @Inject
    private CatalogService catalogService;
    private List<Catalog> catalogs;

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

    @PostConstruct
    public void loadCatalogs() {
        catalogs = catalogService.getCatalogs();
    }
}
