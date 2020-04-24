package pl.lodz.p.it.tks.rest;


import pl.lodz.p.it.tks.ports.aggregates.catalog.CatalogRepoGetAdapter;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@RequestScoped
@Path("api")
public class CatalogGetRestService {

    @Inject
    private CatalogRepoGetAdapter catalogRepoGetAdapter;

    @GET
    @Path("/catalogs")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCatalogs() {
        return Response.ok(catalogRepoGetAdapter.getCatalogs()).build();
    }

    @GET
    @Path("/books")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBooks() {
        return Response.ok(catalogRepoGetAdapter.getBooks()).build();
    }

    @GET
    @Path("/movies")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMovies() {
        return Response.ok(catalogRepoGetAdapter.getMovies()).build();
    }
}
