package pl.lodz.p.it.tks.ports;

import pl.lodz.p.it.tks.domainmodel.catalogs.Catalog;
import pl.lodz.p.it.tks.model.BookRestModel;
import pl.lodz.p.it.tks.model.CatalogRestModel;
import pl.lodz.p.it.tks.model.MovieRestModel;

public interface UpdateCatalogPort {

    void updateBook(int id, BookRestModel book);
    void updateMovie(int id, MovieRestModel movieRestModel);
}
