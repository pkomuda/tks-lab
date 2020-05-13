package pl.lodz.p.it.tks.rest.rest;


import pl.lodz.p.it.tks.rest.agregates.CatalogRestGetAdapter;
import pl.lodz.p.it.tks.rest.agregates.impl.CatalogRestGetAdapterImpl;
import pl.lodz.p.it.tks.rest.ports.aggregates.catalog.CatalogRepoGetAdapter;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Named
@RequestScoped
@Path("/api")
public class CatalogGetRestService {

    @Inject
    private CatalogRestGetAdapter catalogRestGetAdapter;

    @GET
    @Path("/catalogs")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCatalogs() {
        return Response.ok(catalogRestGetAdapter.getCatalogs()).build();
    }

    @GET
    @Path("/books")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBooks() {
        return Response.ok(catalogRestGetAdapter.getBooks()).build();
    }

    @GET
    @Path("/movies")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMovies() {
        return Response.ok(catalogRestGetAdapter.getMovies()).build();
    }
}
