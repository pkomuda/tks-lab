package pl.lodz.p.it.tks.appservices.services.catalog;


import pl.lodz.p.it.tks.appservices.services.catalog.interfaces.CatalogGetRestServiceInterface;
import pl.lodz.p.it.tks.domainmodel.catalogs.Book;
import pl.lodz.p.it.tks.domainmodel.catalogs.Catalog;
import pl.lodz.p.it.tks.domainmodel.catalogs.Movie;
import pl.lodz.p.it.tks.ports.aggregates.catalog.CatalogRepoGetAdapter;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@RequestScoped
@Path("api")
public class CatalogGetRestService implements CatalogGetRestServiceInterface {

    @Inject
    private CatalogRepoGetAdapter catalogAdapter;
    @GET
    @Path("/catalogs")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCatalogs() {
        List<Catalog> catalogs = catalogAdapter.getCatalogs();
        return Response.ok(catalogs).build();
    }

    @GET
    @Path("/books")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBooks() {
        List<Book> books = catalogAdapter.getBooks();
        return Response.ok(books).build();
    }

    @GET
    @Path("/movies")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMovies() {
        List<Movie> movies = catalogAdapter.getMovies();
        return Response.ok(movies).build();
    }
}
