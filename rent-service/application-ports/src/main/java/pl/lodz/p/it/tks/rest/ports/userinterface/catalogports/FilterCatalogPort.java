package pl.lodz.p.it.tks.rest.ports.userinterface.catalogports;

import pl.lodz.p.it.tks.rest.domainmodel.catalogs.Book;
import pl.lodz.p.it.tks.rest.domainmodel.catalogs.Catalog;
import pl.lodz.p.it.tks.rest.domainmodel.catalogs.Movie;

import java.util.List;

public interface FilterCatalogPort {

    List<Catalog> filterCatalogs(String catalogFilter);
    List<Book> filterBooks(String bookFilter);
    List<Movie> filterMovies(String movieFilter);
}
