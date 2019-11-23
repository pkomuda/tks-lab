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
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
public class ListCatalogsController implements Serializable
{
    @Inject
    private CatalogService catalogService;
    private List<Catalog> catalogs;

    public List<Book> getBooks()
    {
        List<Book> books = new ArrayList<>();
        for (Catalog catalog : catalogs)
        {
            if (catalog.getClass().getName().equals("com.pas.zad2mvc.data.Book"))
                books.add((Book) catalog);
        }
        return books;
    }

    public List<Movie> getMovies()
    {
        List<Movie> movies = new ArrayList<>();
        for (Catalog catalog : catalogs)
        {
            if (catalog.getClass().getName().equals("com.pas.zad2mvc.data.Movie"))
                movies.add((Movie) catalog);
        }
        return movies;
    }

    public void removeCatalog(int id)
    {
        catalogService.removeCatalog(id);
        loadCatalogs();
    }

    @PostConstruct
    public void loadCatalogs() { catalogs = catalogService.getCatalogs(); }
}
