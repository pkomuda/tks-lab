package pl.lodz.p.it.tks.rest.appservices.services.catalog;

import pl.lodz.p.it.tks.rest.domainmodel.catalogs.Book;
import pl.lodz.p.it.tks.rest.domainmodel.catalogs.Catalog;
import pl.lodz.p.it.tks.rest.domainmodel.catalogs.Movie;

import java.util.List;

public interface CatalogFilterService {

    List<Catalog> filterCatalogs(String catalogFilter);
    List<Book> filterBooks(String catalogFilter);
    List<Movie> filterMovies(String catalogFilter);
}
