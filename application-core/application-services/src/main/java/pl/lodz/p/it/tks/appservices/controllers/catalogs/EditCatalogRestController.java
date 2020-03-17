package pl.lodz.p.it.tks.appservices.controllers.catalogs;

import lombok.Data;
import pl.lodz.p.it.tks.appservices.controllers.ViewAccessController;
import pl.lodz.p.it.tks.domainmodel.catalogs.Book;
import pl.lodz.p.it.tks.domainmodel.catalogs.Catalog;
import pl.lodz.p.it.tks.domainmodel.catalogs.Movie;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Named
@RequestScoped
public @Data class EditCatalogRestController {

    @Inject
    private ViewAccessController viewAccessController;
    private int id;
    private String title;
    private String author;
    private int releaseYear;
    private String director;
    private String format;
    private Client client = ClientBuilder.newClient();
    private WebTarget base = client.target("https://localhost:8181/tkslab/resources/api");

    public String confirmEditBook() {
        base.path("book/{id}")
                .resolveTemplate("id", id)
                .request(MediaType.APPLICATION_JSON)
                .put(Entity.json(new Book(id, title, author, releaseYear)), Response.class);
        return "manager";
    }

    public String confirmEditMovie() {
        base.path("movie/{id}")
                .resolveTemplate("id", id)
                .request(MediaType.APPLICATION_JSON)
                .put(Entity.json(new Movie(id, title, director, releaseYear, format)), Response.class);
        return "manager";
    }

    public String cancel() {
        return "cancel";
    }

    @PostConstruct
    public void loadCatalogInfo() {
        Catalog temp = viewAccessController.getSelectedCatalog();
        id = temp.getId();
        title = temp.getTitle();
        releaseYear = temp.getReleaseYear();
        if (temp instanceof Book) {
            author = ((Book) temp).getAuthor();
        } else if (temp instanceof Movie) {
            director = ((Movie) temp).getDirector();
            format = ((Movie) temp).getFormat();
        }
    }
}
