package com.pas.zad2mvc.controllers.catalogs;

import com.pas.zad2mvc.controllers.ViewAccessController;
import com.pas.zad2mvc.model.catalogs.Book;
import com.pas.zad2mvc.model.catalogs.Catalog;
import com.pas.zad2mvc.model.catalogs.Movie;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

@Named
@RequestScoped
public class EditCatalogRestController {
    @Inject
    private ViewAccessController viewAccessController;
    private int id;
    private String title;
    private String author;
    private int releaseYear;
    private String director;
    private String format;
    private Client client = ClientBuilder.newClient();
    private WebTarget base = client.target("https://localhost:8181/zad2mvc/resources/api");

    public String confirmEditBook() {
        base.path("catalog/{id}")
                .resolveTemplate("id", id)
                .request(MediaType.APPLICATION_JSON)
                .put(Entity.json(new Book(id, title, author, releaseYear)), Book.class);
        return "manager";
    }

    public String confirmEditMovie() {
        base.path("catalog/{id}")
                .resolveTemplate("id", id)
                .request(MediaType.APPLICATION_JSON)
                .put(Entity.json(new Movie(id, title, director, releaseYear, format)), Movie.class);
        return "manager";
    }

    public String cancel() {
        return "cancel";
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
    //endregion

    @PostConstruct
    public void loadCatalogInfo() {
        id = viewAccessController.getSelectedCatalogId();
//        Catalog temp =  base.path("catalog/{id}")
//                .resolveTemplate("id", id)
//                .request(MediaType.APPLICATION_JSON)
//                .get(new GenericType<Catalog>() {});
        System.out.println(base.path("catalog/{id}")
                .resolveTemplate("id", id)
                .request(MediaType.APPLICATION_JSON)
                .get(Catalog.class));
//        title = temp.getTitle();
//        releaseYear = temp.getReleaseYear();
//        if (temp instanceof Book) {
//            author = ((Book) temp).getAuthor();
//        } else if (temp instanceof Movie) {
//            director = ((Movie) temp).getDirector();
//            format = ((Movie) temp).getFormat();
//        }
    }
}
