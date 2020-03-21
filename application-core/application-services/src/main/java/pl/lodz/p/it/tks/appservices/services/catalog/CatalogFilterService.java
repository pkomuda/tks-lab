package pl.lodz.p.it.tks.appservices.services.catalog;

import pl.lodz.p.it.tks.domainmodel.catalogs.Book;
import pl.lodz.p.it.tks.domainmodel.catalogs.Catalog;
import pl.lodz.p.it.tks.domainmodel.catalogs.Movie;

import java.util.List;

public interface CatalogFilterService {

    List<Catalog> filterCatalogs(String catalogFilter);
    List<Book> filterBooks(String catalogFilter);
    List<Movie> filterMovies(String catalogFilter);
}
