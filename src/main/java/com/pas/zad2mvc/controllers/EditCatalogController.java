package com.pas.zad2mvc.controllers;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class EditCatalogController {
    @Inject
    private ListCatalogsController listCatalogsController;
    private String title;
    private String author;
    private int releaseYear;
    private String director;
    private String format;

    public String confirmEditBook(int id) {
        listCatalogsController.getCatalogService().updateBook(id, title, author, releaseYear);
        listCatalogsController.endConversation();
        return "manager";
    }

    public String confirmEditMovie(int id) {
        listCatalogsController.getCatalogService().updateMovie(id, title, director, releaseYear, format);
        listCatalogsController.endConversation();
        return "manager";
    }

    //region getters
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
    //endregion

    //region setters
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
    //endregion

    @PostConstruct
    public void loadCatalogInfo() {
        setTitle(listCatalogsController.getSelectedTitle());
        setAuthor(listCatalogsController.getSelectedAuthor());
        setReleaseYear(listCatalogsController.getSelectedReleaseYear());
        setDirector(listCatalogsController.getSelectedDirector());
        setFormat(listCatalogsController.getSelectedFormat());
    }
}
