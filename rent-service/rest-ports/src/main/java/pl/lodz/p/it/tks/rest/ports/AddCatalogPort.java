package pl.lodz.p.it.tks.rest.ports;

import pl.lodz.p.it.tks.rest.model.BookRestModel;
import pl.lodz.p.it.tks.rest.model.MovieRestModel;

public interface AddCatalogPort {

    void addBook(BookRestModel book);
    void addMovie(MovieRestModel movie);
}
