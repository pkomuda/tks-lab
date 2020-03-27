package pl.lodz.p.it.webapplication.controllers.catalogs;

import lombok.Data;
import pl.lodz.p.it.webapplication.controllers.ViewAccessController;
import pl.lodz.p.it.webapplication.webmodel.catalogs.BookWeb;
import pl.lodz.p.it.webapplication.webmodel.catalogs.CatalogWeb;
import pl.lodz.p.it.webapplication.webmodel.catalogs.MovieWeb;

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
                .put(Entity.json(new BookWeb(id, title, author, releaseYear)), Response.class);
        return "manager";
    }

    public String confirmEditMovie() {
        base.path("movie/{id}")
                .resolveTemplate("id", id)
                .request(MediaType.APPLICATION_JSON)
                .put(Entity.json(new MovieWeb(id, title, director, releaseYear, format)), Response.class);
        return "manager";
    }

    public String cancel() {
        return "cancel";
    }

    @PostConstruct
    public void loadCatalogInfo() {
        CatalogWeb temp = viewAccessController.getSelectedCatalog();
        id = temp.getId();
        title = temp.getTitle();
        releaseYear = temp.getReleaseYear();
        if (temp instanceof BookWeb) {
            author = ((BookWeb) temp).getAuthor();
        } else if (temp instanceof MovieWeb) {
            director = ((MovieWeb) temp).getDirector();
            format = ((MovieWeb) temp).getFormat();
        }
    }
}
