package pl.lodz.p.it.tks.appservices.services.catalog.impl;

import pl.lodz.p.it.tks.appservices.services.catalog.CatalogGetService;
import pl.lodz.p.it.tks.rest.domainmodel.catalogs.Book;
import pl.lodz.p.it.tks.rest.domainmodel.catalogs.Catalog;
import pl.lodz.p.it.tks.rest.domainmodel.catalogs.Movie;
import pl.lodz.p.it.tks.rest.ports.aggregates.catalog.CatalogRepoGetAdapter;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@Dependent
public class CatalogGetServiceImpl implements CatalogGetService {

    @Inject
    private CatalogRepoGetAdapter catalogAdapter;

    @Override
    public List<Catalog> getCatalogs() {
        return catalogAdapter.getCatalogs();
    }

    @Override
    public List<Book> getBooks() {
        return catalogAdapter.getBooks();
    }

    @Override
    public List<Movie> getMovies() {
        return catalogAdapter.getMovies();
    }
}
