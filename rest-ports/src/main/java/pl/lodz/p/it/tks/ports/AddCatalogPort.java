package pl.lodz.p.it.tks.ports;

import pl.lodz.p.it.tks.model.BookRestModel;
import pl.lodz.p.it.tks.model.MovieRestModel;

public interface AddCatalogPort {

    void addBook(BookRestModel book);
    void addMovie(MovieRestModel movie);
}
