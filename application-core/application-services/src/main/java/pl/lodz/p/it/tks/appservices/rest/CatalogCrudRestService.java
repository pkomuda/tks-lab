package pl.lodz.p.it.tks.appservices.rest;

import pl.lodz.p.it.tks.appservices.services.catalog.CatalogCrudService;
import pl.lodz.p.it.tks.domainmodel.catalogs.Book;
import pl.lodz.p.it.tks.domainmodel.catalogs.Movie;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@RequestScoped
@Path("api")
public class CatalogCrudRestService {

    @Inject
    private CatalogCrudService catalogService;

    @POST
    @Path("/book")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addBook(@Valid Book book) {
        try {
            catalogService.addBook(book);
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
        return Response.ok("Book with id: " + book.getId() + " added").build();
    }

    @POST
    @Path("/movie")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addMovie(@Valid Movie movie) {
        try {
            catalogService.addMovie(movie);
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
        return Response.ok("Movie with id: " + movie.getId() + " added").build();
    }

    @PUT
    @Path("/book/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateBook(@PathParam("id") int id, @Valid Book book) {
        try {
            catalogService.updateBook(id, book);
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
        return Response.ok("Book with id: " + book.getId() + " updated").build();
    }

    @PUT
    @Path("/movie/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateMovie(@PathParam("id") int id, @Valid Movie movie) {
        try {
            catalogService.updateMovie(id, movie);
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
        return Response.ok("Movie with id: " + movie.getId() + " updated").build();
    }

    @DELETE
    @Path("/catalog/{id}")
    public Response removeCatalog(@PathParam("id") int id) {
        try {
            catalogService.removeCatalog(id);
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
        return Response.ok("Catalog with id: " + id + " removed").build();
    }
}
