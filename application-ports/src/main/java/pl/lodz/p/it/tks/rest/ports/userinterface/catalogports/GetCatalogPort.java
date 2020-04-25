package pl.lodz.p.it.tks.rest.ports.userinterface.catalogports;

import pl.lodz.p.it.tks.rest.domainmodel.catalogs.Book;
import pl.lodz.p.it.tks.rest.domainmodel.catalogs.Catalog;
import pl.lodz.p.it.tks.rest.domainmodel.catalogs.Movie;

import java.util.List;

public interface GetCatalogPort {

    Catalog getCatalog(int id);
    List<Catalog> getCatalogs();
    List<Book> getBooks();
    List<Movie> getMovies();
}
