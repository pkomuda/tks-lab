package com.pas.zad2mvc.services;

import com.pas.zad2mvc.model.Book;
import com.pas.zad2mvc.model.Catalog;
import com.pas.zad2mvc.model.Movie;
import com.pas.zad2mvc.repositories.CatalogRepository;

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

    public void addBook(int id, String title, String author, int releaseYear) {
        catalogRepository.addBook(id, title, author, releaseYear);
    }

    public void addMovie(int id, String title, String director, int releaseYear, String format) {
        catalogRepository.addMovie(id, title, director, releaseYear, format);
    }

    public Catalog getCatalog(int id) {
        return catalogRepository.getCatalog(id);
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

    public void updateBook(int id, String title, String author, int releaseYear) {
        catalogRepository.updateBook(id, title, author, releaseYear);
    }

    public void updateMovie(int id, String title, String director, int releaseYear, String format) {
        catalogRepository.updateMovie(id, title, director, releaseYear, format);
    }

    public void removeCatalog(int id) {
        catalogRepository.removeCatalog(id);
    }

    @Override
    public String toString() {
        return catalogRepository.toString();
    }
}
