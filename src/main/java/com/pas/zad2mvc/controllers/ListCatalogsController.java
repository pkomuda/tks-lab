package com.pas.zad2mvc.controllers;

import com.pas.zad2mvc.data.Book;
import com.pas.zad2mvc.data.Catalog;
import com.pas.zad2mvc.data.Movie;
import com.pas.zad2mvc.services.CatalogService;
import org.apache.commons.lang3.StringUtils;

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
public class ListCatalogsController implements Serializable {
    @Inject
    private CatalogService catalogService;
    @Inject
    private Conversation conversation;
    private List<Catalog> catalogs;
    private int id;
    private String title;
    private String author;
    private int releaseYear;
    private String director;
    private String format;
    private String catalogFilter;

    public void prepareBookInfo(int id, String title, String author, int releaseYear) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.releaseYear = releaseYear;
    }

    public void prepareMovieInfo(int id, String title, String director, int releaseYear, String format) {
        this.id = id;
        this.title = title;
        this.director = director;
        this.releaseYear = releaseYear;
        this.format = format;
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

    public String editBook() {
        if (!conversation.isTransient()) {
            conversation.end();
        }
        conversation.begin();
        return "editBook";
    }

    public String editMovie() {
        if (!conversation.isTransient()) {
            conversation.end();
        }
        conversation.begin();
        return "editMovie";
    }

    public String confirmEditBook() {
        catalogService.updateBook(id, title, author, releaseYear);
        conversation.end();
        return "manager";
    }

    public String confirmEditMovie() {
        catalogService.updateMovie(id, title, author, releaseYear, format);
        conversation.end();
        return "manager";
    }

    public void removeCatalog(int id) {
        catalogService.removeCatalog(id);
        loadCatalogs();
    }

    //region getters
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public String getDirector() {
        return director;
    }

    public String getFormat() {
        return format;
    }

    public String getCatalogFilter() {
        return catalogFilter;
    }
    //endregion

    //region setters
    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setFormat(String format) {
        this.format = format;
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
