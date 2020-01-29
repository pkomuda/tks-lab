package com.pas.zad2mvc.services;

import com.pas.zad2mvc.model.Rent;
import com.pas.zad2mvc.model.catalogs.Book;
import com.pas.zad2mvc.model.catalogs.Catalog;
import com.pas.zad2mvc.model.catalogs.Movie;
import com.pas.zad2mvc.model.catalogs.NoCatalog;
import com.pas.zad2mvc.repositories.CatalogRepository;
import com.pas.zad2mvc.repositories.RentRepository;

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
    private CatalogRepository catalogRepository;
    @Inject
    private RentRepository rentRepository;

    @POST
    @Path("/book")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addBook(Book book) {
        if (catalogRepository.getCatalog(book.getId()) == null && book.getId() != 0) {
            catalogRepository.addCatalog(book);
            return Response.ok("Book with id: " + book.getId() + " added").build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity("Catalog with id: " + book.getId() + " already exists").build();
        }
    }

    @POST
    @Path("/movie")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addMovie(Movie movie) {
        if (catalogRepository.getCatalog(movie.getId()) == null && movie.getId() != 0) {
            catalogRepository.addCatalog(movie);
            return Response.ok("Movie with id: " + movie.getId() + " added").build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity("Catalog with id: " + movie.getId() + " already exists").build();
        }
    }

    @GET
    @Path("/catalog/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCatalog(@PathParam("id") int id) {
        Catalog catalog = catalogRepository.getCatalog(id);
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
        if (catalogRepository.getCatalog(id) != null) {
            catalogRepository.updateCatalog(id, book);
            for (Rent rent : rentRepository.getRentsForCatalog(id)) {
                rent.setCatalog(book);
                rentRepository.updateRent(rent.getId(), rent);
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
        if (catalogRepository.getCatalog(id) != null) {
            catalogRepository.updateCatalog(id, movie);
            for (Rent rent : rentRepository.getRentsForCatalog(id)) {
                rent.setCatalog(movie);
                rentRepository.updateRent(rent.getId(), rent);
            }
            return Response.ok("Book with id: " + movie.getId() + " updated").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Catalog with id: " + id + " not found").build();
        }
    }

    @DELETE
    @Path("/catalog/{id}")
    public Response removeCatalog(@PathParam("id") int id) {
        if (catalogRepository.getCatalog(id) != null) {
            for (Rent rent : rentRepository.getRentsForCatalog(id)) {
                rent.setCatalog(new NoCatalog());
                rentRepository.updateRent(rent.getId(), rent);
            }
            catalogRepository.removeCatalog(id);
            return Response.ok("Catalog with id: " + id + " removed").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Catalog with id: " + id + " not found").build();
        }
    }

    @GET
    @Path("/catalogs")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCatalogs() {
        List<Catalog> catalogs = catalogRepository.getCatalogs();
        return Response.ok(catalogs).build();
    }

    @GET
    @Path("/books")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBooks() {
        List<Book> books = catalogRepository.getBooks();
        return Response.ok(books).build();
    }

    @GET
    @Path("/movies")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMovies() {
        List<Movie> movies = catalogRepository.getMovies();
        return Response.ok(movies).build();
    }

    @GET
    @Path("/catalogs/{filter}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response filterCatalogs(@PathParam("filter") String catalogFilter) {
        List<Catalog> catalogs = catalogRepository.filterCatalogs(catalogFilter);
        return Response.ok(catalogs).build();
    }

    @GET
    @Path("/books/{filter}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response filterBooks(@PathParam("filter") String catalogFilter) {
        List<Book> books = catalogRepository.filterBooks(catalogFilter);
        return Response.ok(books).build();
    }

    @GET
    @Path("/movies/{filter}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response filterMovies(@PathParam("filter") String catalogFilter) {
        List<Movie> movies = catalogRepository.filterMovies(catalogFilter);
        return Response.ok(movies).build();
    }

    @GET
    @Path("/count")
    @Produces(MediaType.TEXT_PLAIN)
    public String count() {
        String str = "Number of Catalogs:";
        str += String.valueOf(catalogRepository.countCatalogs());
        return str;
    }
}
