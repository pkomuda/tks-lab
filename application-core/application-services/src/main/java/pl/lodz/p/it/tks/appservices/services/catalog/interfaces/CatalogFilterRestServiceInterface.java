package pl.lodz.p.it.tks.appservices.services.catalog.interfaces;

import pl.lodz.p.it.tks.domainmodel.catalogs.Movie;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public interface CatalogFilterRestServiceInterface {


    public Response filterCatalogs( String catalogFilter);


    public Response filterBooks(String catalogFilter);

    public Response filterMovies(String catalogFilter);
}
