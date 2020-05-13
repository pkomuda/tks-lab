package pl.lodz.p.it.tks.rest.ports;

import pl.lodz.p.it.tks.rest.model.BookRestModel;
import pl.lodz.p.it.tks.rest.model.MovieRestModel;

public interface UpdateCatalogPort {

    void updateBook(int id, BookRestModel book);
    void updateMovie(int id, MovieRestModel movieRestModel);
}
