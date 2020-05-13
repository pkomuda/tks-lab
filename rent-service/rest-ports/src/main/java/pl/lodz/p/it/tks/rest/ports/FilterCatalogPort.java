package pl.lodz.p.it.tks.rest.ports;

import pl.lodz.p.it.tks.rest.model.BookRestModel;
import pl.lodz.p.it.tks.rest.model.CatalogRestModel;
import pl.lodz.p.it.tks.rest.model.MovieRestModel;

import java.util.List;

public interface FilterCatalogPort {

    List<CatalogRestModel> filterCatalogs(String catalogFilter);
    List<BookRestModel> filterBooks(String bookFilter);
    List<MovieRestModel> filterMovies(String movieFilter);
}
