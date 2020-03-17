package pl.lodz.p.it.tks.ports.userinterface.catalogports;

import pl.lodz.p.it.tks.domainmodel.catalogs.Book;
import pl.lodz.p.it.tks.domainmodel.catalogs.Catalog;
import pl.lodz.p.it.tks.domainmodel.catalogs.Movie;

import java.util.List;

public interface GetCatalogsPort {

    List<Catalog> getCatalogs();
    List<Book> getBooks();
    List<Movie> getMovies();
    List<Catalog> filterCatalogs(String catalogFilter);
    List<Book> filterBooks(String bookFilter);
    List<Movie> filterMovies(String movieFilter);
}
