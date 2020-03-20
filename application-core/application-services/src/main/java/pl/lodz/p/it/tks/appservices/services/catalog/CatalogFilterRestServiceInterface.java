package pl.lodz.p.it.tks.appservices.services.catalog;

import pl.lodz.p.it.tks.domainmodel.catalogs.Movie;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public interface CatalogFilterRestServiceInterface {

    @GET
    @Path("/catalogs/{filter}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response filterCatalogs( String catalogFilter);

    @GET
    @Path("/books/{filter}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response filterBooks(String catalogFilter);

    @GET
    @Path("/movies/{filter}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response filterMovies(String catalogFilter);
}
