package pl.lodz.p.it.tks.appservices.services.catalog.interfaces;

import pl.lodz.p.it.tks.domainmodel.catalogs.Book;
import pl.lodz.p.it.tks.domainmodel.catalogs.Catalog;
import pl.lodz.p.it.tks.domainmodel.catalogs.Movie;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

public interface CatalogGetRestServiceInterface {


    public Response getCatalogs();

    public Response getBooks();

    public Response getMovies();
}
