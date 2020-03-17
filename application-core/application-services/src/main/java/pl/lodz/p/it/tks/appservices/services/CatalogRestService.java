package pl.lodz.p.it.tks.appservices.services;

import pl.lodz.p.it.tks.domainmodel.Rent;
import pl.lodz.p.it.tks.domainmodel.catalogs.Book;
import pl.lodz.p.it.tks.domainmodel.catalogs.Catalog;
import pl.lodz.p.it.tks.domainmodel.catalogs.Movie;
import pl.lodz.p.it.tks.domainmodel.catalogs.NoCatalog;
import pl.lodz.p.it.tks.ports.aggregates.CatalogAdapter;
import pl.lodz.p.it.tks.ports.aggregates.RentAdapter;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@RequestScoped
@Path("api")
public class CatalogRestService {

    @Inject
    private CatalogAdapter catalogAdapter;
    @Inject
    private RentAdapter rentAdapter;

    @POST
    @Path("/book")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addBook(Book book) {
        if (catalogAdapter.getCatalog(book.getId()) == null && book.getId() != 0) {
            catalogAdapter.addCatalog(book);
            return Response.ok("Book with id: " + book.getId() + " added").build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity("Catalog with id: " + book.getId() + " already exists").build();
        }
    }

    @POST
    @Path("/movie")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addMovie(Movie movie) {
        if (catalogAdapter.getCatalog(movie.getId()) == null && movie.getId() != 0) {
            catalogAdapter.addCatalog(movie);
            return Response.ok("Movie with id: " + movie.getId() + " added").build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity("Catalog with id: " + movie.getId() + " already exists").build();
        }
    }

    @GET
    @Path("/catalog/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCatalog(@PathParam("id") int id) {
        Catalog catalog = catalogAdapter.getCatalog(id);
        if (catalog != null) {
            return Response.ok(catalog).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Catalog with id: " + id + " not found").build();
        }
    }

    @PUT
    @Path("/book/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateBook(@PathParam("id") int id, Book book) {
        if (catalogAdapter.getCatalog(id) != null) {
            catalogAdapter.updateCatalog(id, book);
            for (Rent rent : rentAdapter.getRentsForCatalog(id)) {
                rent.setCatalog(book);
                rentAdapter.updateRent(rent.getId(), rent);
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
        if (catalogAdapter.getCatalog(id) != null) {
            catalogAdapter.updateCatalog(id, movie);
            for (Rent rent : rentAdapter.getRentsForCatalog(id)) {
                rent.setCatalog(movie);
                rentAdapter.updateRent(rent.getId(), rent);
            }
            return Response.ok("Movie with id: " + movie.getId() + " updated").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Catalog with id: " + id + " not found").build();
        }
    }

    @DELETE
    @Path("/catalog/{id}")
    public Response removeCatalog(@PathParam("id") int id) {
        if (catalogAdapter.getCatalog(id) != null) {
            for (Rent rent : rentAdapter.getRentsForCatalog(id)) {
                rent.setCatalog(new NoCatalog());
                rentAdapter.updateRent(rent.getId(), rent);
            }
            catalogAdapter.removeCatalog(id);
            return Response.ok("Catalog with id: " + id + " removed").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Catalog with id: " + id + " not found").build();
        }
    }

    @GET
    @Path("/catalogs")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCatalogs() {
        List<Catalog> catalogs = catalogAdapter.getCatalogs();
        return Response.ok(catalogs).build();
    }

    @GET
    @Path("/books")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBooks() {
        List<Book> books = catalogAdapter.getBooks();
        return Response.ok(books).build();
    }

    @GET
    @Path("/movies")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMovies() {
        List<Movie> movies = catalogAdapter.getMovies();
        return Response.ok(movies).build();
    }

    @GET
    @Path("/catalogs/{filter}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response filterCatalogs(@PathParam("filter") String catalogFilter) {
        List<Catalog> catalogs = catalogAdapter.filterCatalogs(catalogFilter);
        return Response.ok(catalogs).build();
    }

    @GET
    @Path("/books/{filter}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response filterBooks(@PathParam("filter") String catalogFilter) {
        List<Book> books = catalogAdapter.filterBooks(catalogFilter);
        return Response.ok(books).build();
    }

    @GET
    @Path("/movies/{filter}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response filterMovies(@PathParam("filter") String catalogFilter) {
        List<Movie> movies = catalogAdapter.filterMovies(catalogFilter);
        return Response.ok(movies).build();
    }
}
