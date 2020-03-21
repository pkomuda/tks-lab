package pl.lodz.p.it.tks.appservices.services.catalog;

import pl.lodz.p.it.tks.domainmodel.catalogs.Book;
import pl.lodz.p.it.tks.domainmodel.catalogs.Catalog;
import pl.lodz.p.it.tks.domainmodel.catalogs.Movie;

import java.util.List;

public interface CatalogGetService {

    List<Catalog> getCatalogs();
    List<Book> getBooks();
    List<Movie> getMovies();
}
