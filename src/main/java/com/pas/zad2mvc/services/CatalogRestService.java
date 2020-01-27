package com.pas.zad2mvc.services;

import com.pas.zad2mvc.model.*;
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
    public void addBook(Book book) {
        if (getCatalog(book.getId()) == null && book.getId() != 0) {
            catalogRepository.addCatalog(book);
        }
    }

    @POST
    @Path("/movie")
    @Consumes(MediaType.APPLICATION_JSON)
    public void addMovie(Movie movie) {
        if (getCatalog(movie.getId()) == null && movie.getId() != 0) {
            catalogRepository.addCatalog(movie);
        }
    }

    @GET
    @Path("/catalog/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Catalog getCatalog(@PathParam("id") int id) {
        return catalogRepository.getCatalog(id);
    }

    @PUT
    @Path("/catalog/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateCatalog(@PathParam("id") int id, Catalog catalog) {
        if (getCatalog(id) != null) {
            catalogRepository.updateCatalog(id, catalog);
            for (Rent rent : rentRepository.getRentsForCatalog(id)) {
                rent.setCatalog(catalog);
                rentRepository.updateRent(rent.getId(), rent);
            }
        }
    }

    @DELETE
    @Path("/catalog/{id}")
    public void removeCatalog(@PathParam("id") int id) {
        for (Rent rent : rentRepository.getRentsForCatalog(id)) {
            rent.setCatalog(new NoCatalog());
            rentRepository.updateRent(rent.getId(), rent);
        }
        catalogRepository.removeCatalog(id);
    }

    @GET
    @Path("/catalogs")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Catalog> getCatalogs() {
        return catalogRepository.getCatalogs();
    }

    @GET
    @Path("/books")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Book> getBooks() {
        return catalogRepository.getBooks();
    }

    @GET
    @Path("/movies")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Movie> getMovies() {
        return catalogRepository.getMovies();
    }

    @GET
    @Path("/catalogs/{filter}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Catalog> filterCatalogs(@PathParam("filter") String catalogFilter) {
        return catalogRepository.filterCatalogs(catalogFilter);
    }

    @GET
    @Path("/books/{filter}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Book> filterBooks(@PathParam("filter") String catalogFilter) {
        return catalogRepository.filterBooks(catalogFilter);
    }

    @GET
    @Path("/movies/{filter}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Movie> filterMovies(@PathParam("filter") String catalogFilter) {
        return catalogRepository.filterMovies(catalogFilter);
    }

    @Override
    public String toString() {
        return catalogRepository.toString();
    }
}
