package pl.lodz.p.it.tks.appservices.services.catalog;

import javax.ws.rs.core.Response;

public interface CatalogFilterRestService {

    Response filterCatalogs( String catalogFilter);
    Response filterBooks(String catalogFilter);
    Response filterMovies(String catalogFilter);
}
