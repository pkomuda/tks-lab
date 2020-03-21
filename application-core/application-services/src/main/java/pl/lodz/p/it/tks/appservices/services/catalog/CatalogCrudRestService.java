package pl.lodz.p.it.tks.appservices.services.catalog;

import pl.lodz.p.it.tks.domainmodel.catalogs.Book;
import pl.lodz.p.it.tks.domainmodel.catalogs.Movie;

import javax.ws.rs.core.Response;

public interface CatalogCrudRestService {

    Response addBook(Book book);
    Response addMovie(Movie movie);
    Response updateBook(int id, Book book);
    Response updateMovie( int id, Movie movie);
    Response removeCatalog( int id);
}
