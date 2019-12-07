package com.pas.zad2mvc.repositories;

import com.pas.zad2mvc.model.Book;
import com.pas.zad2mvc.model.Catalog;
import com.pas.zad2mvc.model.Movie;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

@Named
@ApplicationScoped
public class CatalogRepository {
    private LinkedHashMap<Integer, Catalog> catalogs = new LinkedHashMap<>();

    public synchronized void addCatalog(Catalog catalog) {
        catalogs.put(catalog.getId(), catalog);
    }

    public synchronized Catalog getCatalog(int id) {
        return catalogs.get(id);
    }

    public synchronized void updateCatalog(int id, Catalog catalog) {
        catalogs.replace(id, catalog);
    }

    public synchronized void removeCatalog(int id) {
        catalogs.remove(id);
    }

    public synchronized List<Catalog> getCatalogs() {
        return new ArrayList<>(catalogs.values());
    }

    public List<Book> getBooks() {
        return getCatalogs()
                .stream()
                .filter(catalog -> catalog instanceof Book)
                .map(catalog -> (Book) catalog)
                .collect(Collectors.toList());
    }

    public List<Movie> getMovies() {
        return getCatalogs()
                .stream()
                .filter(catalog -> catalog instanceof Movie)
                .map(catalog -> (Movie) catalog)
                .collect(Collectors.toList());
    }

    public List<Catalog> filterCatalogs(String catalogFilter) {
        return getCatalogs()
                .stream()
                .filter(catalog -> StringUtils.containsIgnoreCase(catalog.toString(), catalogFilter))
                .collect(Collectors.toList());
    }
    
    public List<Book> filterBooks(String bookFilter) {
        return getBooks()
                .stream()
                .filter(book -> StringUtils.containsIgnoreCase(book.toString(), bookFilter))
                .collect(Collectors.toList());
    }
    
    public List<Movie> filterMovies(String movieFilter) {
        return getMovies()
                .stream()
                .filter(movie -> StringUtils.containsIgnoreCase(movie.toString(), movieFilter))
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < catalogs.size(); i++) {
            if (i == 0) {
                str = str.concat(getCatalogs().get(i).toString() + "\n");
            } else {
                str = str.concat("\t      " + getCatalogs().get(i).toString());
                if (i != catalogs.size() - 1) {
                    str = str.concat("\n");
                }
            }
        }
        return "CatalogRepo[" + str + "]";
    }

    @PostConstruct
    private void addCatalogPool() {
        addCatalog(new Book(1, "The Shining", "Stephen King", 1997));
        addCatalog(new Book(2, "The Lord of the Rings", "J.R.R. Tolkien", 2015));
        addCatalog(new Book(3, "What Could Possibly Go Wrong", "Jeremy Clarkson", 2015));
        addCatalog(new Movie(4, "Trainspotting", "Danny Boyle", 1996, "DVD"));
        addCatalog(new Movie(5, "Pulp Fiction", "Quentin Tarantino", 1994, "Blu-ray"));
        addCatalog(new Movie(6, "The Graduate", "Mike Nichols", 1967, "VHS"));
    }
}
