package pl.lodz.p.it.tks.ports;

import pl.lodz.p.it.tks.domainmodel.catalogs.Book;
import pl.lodz.p.it.tks.domainmodel.catalogs.Catalog;
import pl.lodz.p.it.tks.domainmodel.catalogs.Movie;
import pl.lodz.p.it.tks.model.BookRestModel;
import pl.lodz.p.it.tks.model.CatalogRestModel;
import pl.lodz.p.it.tks.model.MovieRestModel;

import java.util.List;

public interface FilterCatalogPort {

    List<CatalogRestModel> filterCatalogs(String catalogFilter);
    List<BookRestModel> filterBooks(String bookFilter);
    List<MovieRestModel> filterMovies(String movieFilter);
}
