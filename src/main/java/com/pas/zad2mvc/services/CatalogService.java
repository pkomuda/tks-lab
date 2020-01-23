package com.pas.zad2mvc.services;

import com.pas.zad2mvc.model.*;
import com.pas.zad2mvc.repositories.CatalogRepository;
import com.pas.zad2mvc.repositories.RentRepository;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@Dependent
public class CatalogService implements Serializable {
    @Inject
    private CatalogRepository catalogRepository;
    @Inject
    private RentRepository rentRepository;

    public void addBook(int id, String title, String author, int releaseYear) {
        if (getCatalog(id) == null && id != 0) {
            catalogRepository.addCatalog(new Book(id, title, author, releaseYear));
        }
    }

    public void addMovie(int id, String title, String director, int releaseYear, String format) {
        if (getCatalog(id) == null && id != 0) {
            catalogRepository.addCatalog(new Movie(id, title, director, releaseYear, format));
        }
    }

    public Catalog getCatalog(int id) {
        return catalogRepository.getCatalog(id);
    }

    public void updateBook(int id, String title, String author, int releaseYear) {
        if (getCatalog(id) != null && getCatalog(id) instanceof Book) {
            Catalog temp = new Book(id, title, author, releaseYear);
            catalogRepository.updateCatalog(id, temp);
            for (Rent rent : rentRepository.getRentsForCatalog(id)) {
                rent.setCatalog(temp);
                rentRepository.updateRent(rent.getId(), rent);
            }
        }
    }

    public void updateMovie(int id, String title, String director, int releaseYear, String format) {
        if (getCatalog(id) != null && getCatalog(id) instanceof Movie) {
            Catalog temp = new Movie(id, title, director, releaseYear, format);
            catalogRepository.updateCatalog(id, temp);
            for (Rent rent : rentRepository.getRentsForCatalog(id)) {
                rent.setCatalog(temp);
                rentRepository.updateRent(rent.getId(), rent);
            }
        }
    }

    public void removeCatalog(int id) {
        for (Rent rent : rentRepository.getRentsForCatalog(id)) {
            rent.setCatalog(new NoCatalog());
            rentRepository.updateRent(rent.getId(), rent);
        }
        catalogRepository.removeCatalog(id);
    }

    public List<Catalog> getCatalogs() {
        return catalogRepository.getCatalogs();
    }
    
    public List<Book> getBooks() {
        return catalogRepository.getBooks();
    }

    public List<Movie> getMovies() {
        return catalogRepository.getMovies();
    }

    public List<Catalog> filterCatalogs(String catalogFilter) {
        return catalogRepository.filterCatalogs(catalogFilter);
    }
    
    public List<Book> filterBooks(String catalogFilter) {
        return catalogRepository.filterBooks(catalogFilter);
    }
    
    public List<Movie> filterMovies(String catalogFilter) {
        return catalogRepository.filterMovies(catalogFilter);
    }

    @Override
    public String toString() {
        return catalogRepository.toString();
    }
}
