package pl.lodz.p.it.tks.rest;


import pl.lodz.p.it.tks.agregates.CatalogRepoCrudAdapter;
import pl.lodz.p.it.tks.converters.RestConverter;
import pl.lodz.p.it.tks.domainmodel.catalogs.Book;
import pl.lodz.p.it.tks.domainmodel.catalogs.Movie;
import pl.lodz.p.it.tks.model.BookRestModel;
import pl.lodz.p.it.tks.model.MovieRestModel;

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
    private CatalogRepoCrudAdapter catalogRepoCrudAdapter;

    @POST
    @Path("/book")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addBook(@Valid BookRestModel book) {
        try {
            catalogRepoCrudAdapter.addBook(book);
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
            catalogRepoCrudAdapter.addMovie(movie);
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
            catalogRepoCrudAdapter.updateBook(id, book);
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
            catalogRepoCrudAdapter.updateMovie(id, movie);
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
        return Response.ok("Movie with id: " + movie.getId() + " updated").build();
    }

    @DELETE
    @Path("/catalog/{id}")
    public Response removeCatalog(@PathParam("id") int id) {
        try {
            catalogRepoCrudAdapter.removeCatalog(id);
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
        return Response.ok("Catalog with id: " + id + " removed").build();
    }
}
