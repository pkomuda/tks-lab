package pl.lodz.p.it.tks.appservices.services.catalog;

import pl.lodz.p.it.tks.domainmodel.catalogs.Book;
import pl.lodz.p.it.tks.domainmodel.catalogs.Movie;

public interface CatalogCrudService {

    void addBook(Book book);
    void addMovie(Movie movie);
    void updateBook(int id, Book book);
    void updateMovie( int id, Movie movie);
    void removeCatalog( int id);
}
