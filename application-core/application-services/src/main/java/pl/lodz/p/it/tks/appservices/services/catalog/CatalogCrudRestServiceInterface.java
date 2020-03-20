package pl.lodz.p.it.tks.appservices.services.catalog;

import pl.lodz.p.it.tks.domainmodel.catalogs.Book;
import pl.lodz.p.it.tks.domainmodel.catalogs.Movie;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

public interface CatalogCrudRestServiceInterface {

    @POST
    @Path("/book")
    public Response addBook(Book book);

    @POST
    @Path("/movie")
    public Response addMovie(Movie movie);

    @PUT
    @Path("/book/{id}")
    public Response updateBook(int id, Book book);

    @PUT
    @Path("/movie/{id}")
    public Response updateMovie( int id, Movie movie);

    @DELETE
    @Path("/catalog/{id}")
    public Response removeCatalog( int id);
}
