package pl.lodz.p.it.tks.appservices.services.catalog;


import pl.lodz.p.it.tks.domainmodel.Rent;
import pl.lodz.p.it.tks.domainmodel.catalogs.Book;
import pl.lodz.p.it.tks.domainmodel.catalogs.Movie;
import pl.lodz.p.it.tks.domainmodel.catalogs.NoCatalog;
import pl.lodz.p.it.tks.ports.aggregates.*;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@RequestScoped
@Path("api")
public class CatalogCrudRestService implements CatalogCrudRestServiceInterface {

    @Inject
    private CatalogRepoGetAdapter catalogRepoGetAdapter;
    @Inject
    private CatalogRepoCrudAdapter catalogRepoCrudAdapter;
    @Inject
    private RentRepoGetAdapter rentRepoGetAdapter;
    @Inject
    private RentRepoCrudAdapter rentRepoCrudAdapter;

    @POST
    @Path("/book")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addBook(Book book) {
        if (catalogRepoGetAdapter.getCatalog(book.getId()) == null && book.getId() != 0) {
            catalogRepoCrudAdapter.addCatalog(book);
            return Response.ok("Book with id: " + book.getId() + " added").build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity("Catalog with id: " + book.getId() + " already exists").build();
        }
    }

    @POST
    @Path("/movie")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addMovie(Movie movie) {
        if (catalogRepoGetAdapter.getCatalog(movie.getId()) == null && movie.getId() != 0) {
            catalogRepoCrudAdapter.addCatalog(movie);
            return Response.ok("Movie with id: " + movie.getId() + " added").build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity("Catalog with id: " + movie.getId() + " already exists").build();
        }
    }

    @PUT
    @Path("/book/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateBook(@PathParam("id") int id, Book book) {
        if (catalogRepoGetAdapter.getCatalog(id) != null) {
            catalogRepoCrudAdapter.updateCatalog(id, book);
            for (Rent rent : rentRepoGetAdapter.getRentsForCatalog(id)) {
                rent.setCatalog(book);
                rentRepoCrudAdapter.updateRent(rent.getId(), rent);
            }
            return Response.ok("Book with id: " + book.getId() + " updated").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Catalog with id: " + id + " not found").build();
        }
    }

    @PUT
    @Path("/movie/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateMovie(@PathParam("id") int id, Movie movie) {
        if (catalogRepoGetAdapter.getCatalog(id) != null) {
            catalogRepoCrudAdapter.updateCatalog(id, movie);
            for (Rent rent : rentRepoGetAdapter.getRentsForCatalog(id)) {
                rent.setCatalog(movie);
                rentRepoCrudAdapter.updateRent(rent.getId(), rent);
            }
            return Response.ok("Movie with id: " + movie.getId() + " updated").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Catalog with id: " + id + " not found").build();
        }
    }

    @DELETE
    @Path("/catalog/{id}")
    public Response removeCatalog(@PathParam("id") int id) {
        if (catalogRepoGetAdapter.getCatalog(id) != null) {
            for (Rent rent : rentRepoGetAdapter.getRentsForCatalog(id)) {
                rent.setCatalog(new NoCatalog());
                rentRepoCrudAdapter.updateRent(rent.getId(), rent);
            }
            catalogRepoCrudAdapter.removeCatalog(id);
            return Response.ok("Catalog with id: " + id + " removed").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Catalog with id: " + id + " not found").build();
        }
    }
}
