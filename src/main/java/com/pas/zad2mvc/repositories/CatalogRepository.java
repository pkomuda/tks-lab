package com.pas.zad2mvc.repositories;

import com.pas.zad2mvc.data.Book;
import com.pas.zad2mvc.data.Catalog;
import com.pas.zad2mvc.data.Movie;

import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Named
@ApplicationScoped
public class CatalogRepository {
    private LinkedHashMap<Integer, Catalog> catalogs = new LinkedHashMap<>();

    public void addBook(int id, String title, String author, int releaseYear) {
        if (getCatalog(id) == null) {
            catalogs.put(id, new Book(id, title, author, releaseYear));
        }
    }

    public void addMovie(int id, String title, String director, int releaseYear, String format) {
        if (getCatalog(id) == null) {
            catalogs.put(id, new Movie(id, title, director, releaseYear, format));
        }
    }

    public Catalog getCatalog(int id) {
        return catalogs.get(id);
    }

    public List<Catalog> getCatalogs() {
        return new ArrayList<>(catalogs.values());
    }

    public void updateBook(int id, String title, String author, int releaseYear) {
        if (getCatalog(id) != null && getCatalog(id) instanceof Book) {
            catalogs.replace(id, new Book(id, title, author, releaseYear));
        }
    }

    public void updateMovie(int id, String title, String director, int releaseYear, String format) {
        if (getCatalog(id) != null && getCatalog(id) instanceof Movie) {
            catalogs.replace(id, new Movie(id, title, director, releaseYear, format));
        }
    }

    public boolean removeCatalog(int id) {
        return catalogs.remove(id) != null;
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
        addBook(1, "The Shining", "Stephen King", 1997);
        addBook(2, "The Lord of the Rings", "J.R.R. Tolkien", 2015);
        addBook(3, "What Could Possibly Go Wrong", "Jeremy Clarkson", 2015);
        addMovie(4, "Trainspotting", "Danny Boyle", 1996, "DVD");
        addMovie(5, "Pulp Fiction", "Quentin Tarantino", 1994, "Blu-ray");
        addMovie(6, "The Graduate", "Mike Nichols", 1967, "VHS");
    }
}
