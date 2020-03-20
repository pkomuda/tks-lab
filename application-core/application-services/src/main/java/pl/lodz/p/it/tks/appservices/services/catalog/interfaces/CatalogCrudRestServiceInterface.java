package pl.lodz.p.it.tks.appservices.services.catalog.interfaces;

import pl.lodz.p.it.tks.domainmodel.catalogs.Book;
import pl.lodz.p.it.tks.domainmodel.catalogs.Movie;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

public interface CatalogCrudRestServiceInterface {

    public Response addBook(Book book);

    public Response addMovie(Movie movie);

    public Response updateBook(int id, Book book);

    public Response updateMovie( int id, Movie movie);

    public Response removeCatalog( int id);
}
