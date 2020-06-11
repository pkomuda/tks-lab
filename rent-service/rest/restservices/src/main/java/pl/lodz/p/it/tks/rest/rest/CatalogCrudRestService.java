package pl.lodz.p.it.tks.rest.rest;

import pl.lodz.p.it.tks.rest.agregates.CatalogRestCrudAdapter;
import pl.lodz.p.it.tks.rest.model.BookRestModel;
import pl.lodz.p.it.tks.rest.model.MovieRestModel;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Named
@RequestScoped
@Path("api")
public class CatalogCrudRestService {

    @Inject
    private CatalogRestCrudAdapter catalogRestCrudAdapter;

    @POST
    @Path("/book")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addBook(@Valid BookRestModel book) {
        try {
            catalogRestCrudAdapter.addBook(book);
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
        return Response.ok("Book with id: " + book.getId() + " added").build();
    }

    @POST
    @Path("/movie")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addMovie(@Valid MovieRestModel movie) {
        try {
            catalogRestCrudAdapter.addMovie(movie);
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
        return Response.ok("Movie with id: " + movie.getId() + " added").build();
    }

    @PUT
    @Path("/book/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateBook(@PathParam("id") int id, @Valid BookRestModel book) {
        try {
            catalogRestCrudAdapter.updateBook(id, book);
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
        return Response.ok("Book with id: " + book.getId() + " updated").build();
    }

    @PUT
    @Path("/movie/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateMovie(@PathParam("id") int id, @Valid MovieRestModel movie) {
        try {
            catalogRestCrudAdapter.updateMovie(id, movie);
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
        return Response.ok("Movie with id: " + movie.getId() + " updated").build();
    }

    @DELETE
    @Path("/catalog/{id}")
    public Response removeCatalog(@PathParam("id") int id) {
        try {
            catalogRestCrudAdapter.removeCatalog(id);
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
        return Response.ok("Catalog with id: " + id + " removed").build();
    }
}
