package pl.lodz.p.it.tks.rest.appservices.services.catalog;

import pl.lodz.p.it.tks.rest.domainmodel.catalogs.Book;
import pl.lodz.p.it.tks.rest.domainmodel.catalogs.Catalog;
import pl.lodz.p.it.tks.rest.domainmodel.catalogs.Movie;

import java.util.List;

public interface CatalogGetService {

    List<Catalog> getCatalogs();
    List<Book> getBooks();
    List<Movie> getMovies();
}
