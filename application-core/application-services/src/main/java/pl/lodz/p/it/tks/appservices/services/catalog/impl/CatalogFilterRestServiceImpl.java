package pl.lodz.p.it.tks.appservices.services.catalog.impl;

import pl.lodz.p.it.tks.appservices.services.catalog.CatalogFilterRestService;
import pl.lodz.p.it.tks.domainmodel.catalogs.Book;
import pl.lodz.p.it.tks.domainmodel.catalogs.Catalog;
import pl.lodz.p.it.tks.domainmodel.catalogs.Movie;
import pl.lodz.p.it.tks.ports.aggregates.catalog.CatalogRepoFilterAdapter;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@RequestScoped
@Path("api")
public class CatalogFilterRestServiceImpl implements CatalogFilterRestService {

    @Inject
    private CatalogRepoFilterAdapter catalogAdapter;

    @GET
    @Path("/catalogs/{filter}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response filterCatalogs(@PathParam("filter") String catalogFilter) {
        List<Catalog> catalogs = catalogAdapter.filterCatalogs(catalogFilter);
        return Response.ok(catalogs).build();
    }

    @GET
    @Path("/books/{filter}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response filterBooks(@PathParam("filter") String catalogFilter) {
        List<Book> books = catalogAdapter.filterBooks(catalogFilter);
        return Response.ok(books).build();
    }

    @GET
    @Path("/movies/{filter}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response filterMovies(@PathParam("filter") String catalogFilter) {
        List<Movie> movies = catalogAdapter.filterMovies(catalogFilter);
        return Response.ok(movies).build();
    }
}
