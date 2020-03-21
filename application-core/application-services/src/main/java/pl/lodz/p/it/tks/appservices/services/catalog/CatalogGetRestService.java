package pl.lodz.p.it.tks.appservices.services.catalog;

import javax.ws.rs.core.Response;

public interface CatalogGetRestService {

    Response getCatalogs();
    Response getBooks();
    Response getMovies();
}
