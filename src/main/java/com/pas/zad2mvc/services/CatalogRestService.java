package com.pas.zad2mvc.services;

import com.pas.zad2mvc.model.*;
import com.pas.zad2mvc.repositories.CatalogRepository;
import com.pas.zad2mvc.repositories.RentRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@RequestScoped
@Path("model.catalog")
public class CatalogRestService {
    @Inject
    private CatalogRepository catalogRepository;
    @Inject
    private RentRepository rentRepository;

    @POST
    @Path("/book")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void addBook(Book book) {
        if (getCatalog(book.getId()) == null && book.getId() != 0) {
            catalogRepository.addCatalog(book);
        }
    }

    @POST
    @Path("/movie")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void addMovie( Movie movie) {
        if (getCatalog(movie.getId()) == null && movie.getId() != 0) {
            catalogRepository.addCatalog(movie);
        }
    }

    @GET
    @Path("/catalog/{catalogid}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Catalog getCatalog(@PathParam("catalogid") int id) {
        return catalogRepository.getCatalog(id);
    }

    @PUT
    @Path("/book/{bookid}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void updateBook(@PathParam("bookid") int id, Book book) {
        if (getCatalog(id) != null && getCatalog(id) instanceof Book) {
            catalogRepository.updateCatalog(id, book);
            for (Rent rent : rentRepository.getRentsForCatalog(id)) {
                rent.setCatalog(book);
                rentRepository.updateRent(rent.getId(), rent);
            }
        }
    }

    @PUT
    @Path("/movie/{movieid}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void updateMovie(@PathParam("movieid") int id, Movie movie) {
        if (getCatalog(id) != null && getCatalog(id) instanceof Movie) {
            catalogRepository.updateCatalog(id, movie);
            for (Rent rent : rentRepository.getRentsForCatalog(id)) {
                rent.setCatalog(movie);
                rentRepository.updateRent(rent.getId(), rent);
            }
        }
    }

    @DELETE
    @Path("/catalog/{catalogid}")
    public void removeCatalog(@PathParam("catalogid") int id) {
        for (Rent rent : rentRepository.getRentsForCatalog(id)) {
            rent.setCatalog(new NoCatalog());
            rentRepository.updateRent(rent.getId(), rent);
        }
        catalogRepository.removeCatalog(id);
    }

    @GET
    @Path("/catalogs")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Catalog> getCatalogs() {
        return catalogRepository.getCatalogs();
    }

    @GET
    @Path("/books")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Book> getBooks() {
        return catalogRepository.getBooks();
    }

    @GET
    @Path("/movies")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Movie> getMovies() {
        return catalogRepository.getMovies();
    }

    @GET
    @Path("{catalogfilter}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Catalog> filterCatalogs(@PathParam("catalogfilter") String catalogFilter) {
        return catalogRepository.filterCatalogs(catalogFilter);
    }

    @GET
    @Path("/books/{bookfilter}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Book> filterBooks(@PathParam("bookfilter") String catalogFilter) {
        return catalogRepository.filterBooks(catalogFilter);
    }

    @GET
    @Path("/movies/{moviefilter}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Movie> filterMovies(@PathParam("moviefilter") String catalogFilter) {
        return catalogRepository.filterMovies(catalogFilter);
    }

    @Override
    public String toString() {
        return catalogRepository.toString();
    }
}

