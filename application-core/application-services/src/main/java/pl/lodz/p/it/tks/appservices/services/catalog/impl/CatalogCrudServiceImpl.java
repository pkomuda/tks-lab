package pl.lodz.p.it.tks.appservices.services.catalog.impl;

import pl.lodz.p.it.tks.appservices.services.catalog.CatalogCrudService;
import pl.lodz.p.it.tks.rest.domainmodel.Rent;
import pl.lodz.p.it.tks.rest.domainmodel.catalogs.Book;
import pl.lodz.p.it.tks.rest.domainmodel.catalogs.Movie;
import pl.lodz.p.it.tks.rest.domainmodel.catalogs.NoCatalog;
import pl.lodz.p.it.tks.rest.ports.aggregates.catalog.CatalogRepoCrudAdapter;
import pl.lodz.p.it.tks.rest.ports.aggregates.catalog.CatalogRepoGetAdapter;
import pl.lodz.p.it.tks.rest.ports.aggregates.rent.RentRepoCrudAdapter;
import pl.lodz.p.it.tks.rest.ports.aggregates.rent.RentRepoGetAdapter;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@Dependent
public class CatalogCrudServiceImpl implements CatalogCrudService {

    @Inject
    private CatalogRepoGetAdapter catalogRepoGetAdapter;
    @Inject
    private CatalogRepoCrudAdapter catalogRepoCrudAdapter;
    @Inject
    private RentRepoGetAdapter rentRepoGetAdapter;
    @Inject
    private RentRepoCrudAdapter rentRepoCrudAdapter;

    @Override
    public void addBook(Book book) {
        if (catalogRepoGetAdapter.getCatalog(book.getId()) == null && book.getId() != 0) {
            catalogRepoCrudAdapter.addCatalog(book);
        } else {
            throw new IllegalArgumentException("Catalog with id: " + book.getId() + " already exists");
        }
    }

    @Override
    public void addMovie(Movie movie) {
        if (catalogRepoGetAdapter.getCatalog(movie.getId()) == null && movie.getId() != 0) {
            catalogRepoCrudAdapter.addCatalog(movie);
        } else {
            throw new IllegalArgumentException("Catalog with id: " + movie.getId() + " already exists");
        }
    }

    @Override
    public void updateBook(int id, Book book) {
        if (catalogRepoGetAdapter.getCatalog(id) != null) {
            catalogRepoCrudAdapter.updateCatalog(id, book);
            for (Rent rent : rentRepoGetAdapter.getRentsForCatalog(id)) {
                rent.setCatalog(book);
                rentRepoCrudAdapter.updateRent(rent.getId(), rent);
            }
        } else {
            throw new IllegalArgumentException("Catalog with id: " + id + " not found");
        }
    }

    @Override
    public void updateMovie(int id, Movie movie) {
        if (catalogRepoGetAdapter.getCatalog(id) != null) {
            catalogRepoCrudAdapter.updateCatalog(id, movie);
            for (Rent rent : rentRepoGetAdapter.getRentsForCatalog(id)) {
                rent.setCatalog(movie);
                rentRepoCrudAdapter.updateRent(rent.getId(), rent);
            }
        } else {
            throw new IllegalArgumentException("Catalog with id: " + id + " not found");
        }
    }

    @Override
    public void removeCatalog(int id) {
        if (catalogRepoGetAdapter.getCatalog(id) != null) {
            for (Rent rent : rentRepoGetAdapter.getRentsForCatalog(id)) {
                rent.setCatalog(new NoCatalog());
                rentRepoCrudAdapter.updateRent(rent.getId(), rent);
            }
            catalogRepoCrudAdapter.removeCatalog(id);
        } else {
            throw new IllegalArgumentException("Catalog with id: " + id + " not found");
        }
    }
}
