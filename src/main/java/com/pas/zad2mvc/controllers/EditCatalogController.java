package com.pas.zad2mvc.controllers;

import com.pas.zad2mvc.model.Book;
import com.pas.zad2mvc.model.Movie;
import com.pas.zad2mvc.services.CatalogService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class EditCatalogController {
    @Inject
    private ManagerPageController managerPageController;
    @Inject
    private CatalogService catalogService;
    private int id;
    private String title;
    private String author;
    private int releaseYear;
    private String director;
    private String format;

    public String confirmEditBook() {
        managerPageController.getCatalogService().updateBook(id, title, author, releaseYear);
//        managerPageController.endConversation();
        return "manager";
    }

    public String confirmEditMovie() {
        managerPageController.getCatalogService().updateMovie(id, title, director, releaseYear, format);
//        managerPageController.endConversation();
        return "manager";
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
        id = (int) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("selectedId");
        setTitle(catalogService.getCatalog(id).getTitle());
        setReleaseYear(catalogService.getCatalog(id).getReleaseYear());
        if (catalogService.getCatalog(id) instanceof Book) {
            setAuthor(((Book) catalogService.getCatalog(id)).getAuthor());
        } else if (catalogService.getCatalog(id) instanceof Movie) {
            setDirector(((Movie) catalogService.getCatalog(id)).getDirector());
            setFormat(((Movie) catalogService.getCatalog(id)).getFormat());
        }
    }
}
