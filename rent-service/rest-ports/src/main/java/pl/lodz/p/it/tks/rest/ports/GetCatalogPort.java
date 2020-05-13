package pl.lodz.p.it.tks.rest.ports;

import pl.lodz.p.it.tks.rest.model.BookRestModel;
import pl.lodz.p.it.tks.rest.model.CatalogRestModel;
import pl.lodz.p.it.tks.rest.model.MovieRestModel;

import java.util.List;

public interface GetCatalogPort {

    List<CatalogRestModel> getCatalogs();
    List<BookRestModel> getBooks();
    List<MovieRestModel> getMovies();
}
