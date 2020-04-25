package pl.lodz.p.it.tks.rest.appservices.services.catalog;

import pl.lodz.p.it.tks.rest.domainmodel.catalogs.Book;
import pl.lodz.p.it.tks.rest.domainmodel.catalogs.Movie;

public interface CatalogCrudService {

    void addBook(Book book);
    void addMovie(Movie movie);
    void updateBook(int id, Book book);
    void updateMovie( int id, Movie movie);
    void removeCatalog( int id);
}
