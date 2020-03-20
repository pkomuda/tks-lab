package pl.lodz.p.it.tks.appservices.controllers;

import lombok.Data;
import pl.lodz.p.it.tks.appservices.services.RentService;
import pl.lodz.p.it.tks.appservices.services.rent.*;
import pl.lodz.p.it.tks.domainmodel.Rent;
import pl.lodz.p.it.tks.domainmodel.catalogs.Book;
import pl.lodz.p.it.tks.domainmodel.catalogs.Catalog;
import pl.lodz.p.it.tks.domainmodel.catalogs.Movie;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Named
@ViewScoped
public @Data class ManagerPageRestController implements Serializable {

    @Inject
    private RentCrudServiceInterface rentCrudService;
    @Inject
    private RentGetServiceInterface rentGetService;
    @Inject
    private RentFilterServiceInterface rentFilterService;
    @Inject
    private ViewAccessController viewAccessController;
    private List<Book> books;
    private List<Movie> movies;
    private List<Rent> unfinishedRents;
    private List<Rent> finishedRents;
    private String catalogFilter;
    private String rentFilter;
    private Client client = ClientBuilder.newClient();
    private WebTarget base = client.target("https://localhost:8181/tkslab/resources/api");

    public String addBook() {
        return "addBook";
    }

    public String addMovie() {
        return "addMovie";
    }

    public String prepareCatalogInfo(Catalog selectedCatalog) {
        viewAccessController.setSelectedCatalog(selectedCatalog);
        if (selectedCatalog instanceof Book) {
            return "editBook";
        } else if (selectedCatalog instanceof Movie) {
            return "editMovie";
        } else {
            return "";
        }
    }

    public String prepareRentsInfo(Catalog selectedCatalog) {
        viewAccessController.setSelectedCatalog(selectedCatalog);
        return "rentsForCatalog";
    }

    public void filterCatalogs() {
        books = base.path("catalogs/{filter}")
                .resolveTemplate("filter",catalogFilter)
                .request(MediaType.APPLICATION_JSON)
                .get(new GenericType<List<Book>>() {})
                .stream()
                .filter(book -> book.getAuthor() != null)
                .collect(Collectors.toList());
        movies = base.path("catalogs/{filter}")
                .resolveTemplate("filter",catalogFilter)
                .request(MediaType.APPLICATION_JSON)
                .get(new GenericType<List<Movie>>() {})
                .stream()
                .filter(movie -> movie.getDirector() != null && movie.getFormat() != null)
                .collect(Collectors.toList());
    }

    public void filterRents() {
        unfinishedRents = rentFilterService.filterUnfinishedRents(rentFilter);
        finishedRents = rentFilterService.filterFinishedRents(rentFilter);
    }

    public void removeCatalog(int id) {
        base.path("/catalog/{id}")
                .resolveTemplate("id", id)
                .request().delete();
        loadData();
    }

    public void removeRent(String rentId) {
        rentCrudService.removeRent(rentId);
        loadData();
    }

    @PostConstruct
    public void loadData() {
        books = base.path("books").request(MediaType.APPLICATION_JSON).get(new GenericType<>() {});
        movies = base.path("movies").request(MediaType.APPLICATION_JSON).get(new GenericType<>() {});
        unfinishedRents = rentGetService.getUnfinishedRents();
        finishedRents = rentGetService.getFinishedRents();
    }
}
