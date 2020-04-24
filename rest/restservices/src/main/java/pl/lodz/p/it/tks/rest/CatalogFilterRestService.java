package pl.lodz.p.it.tks.rest;

import pl.lodz.p.it.tks.appservices.services.catalog.CatalogFilterService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@RequestScoped
@Path("api")
public class CatalogFilterRestService {

    @Inject
    private CatalogFilterService catalogService;

    @GET
    @Path("/catalogs/{filter}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response filterCatalogs(@PathParam("filter") String catalogFilter) {
        return Response.ok(catalogService.filterCatalogs(catalogFilter)).build();
    }

    @GET
    @Path("/books/{filter}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response filterBooks(@PathParam("filter") String catalogFilter) {
        return Response.ok(catalogService.filterBooks(catalogFilter)).build();
    }

    @GET
    @Path("/movies/{filter}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response filterMovies(@PathParam("filter") String catalogFilter) {
        return Response.ok(catalogService.filterMovies(catalogFilter)).build();
    }
}
