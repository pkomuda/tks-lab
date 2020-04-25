package pl.lodz.p.it.tks.rest.appservices.services.catalog.impl;

import pl.lodz.p.it.tks.rest.appservices.services.catalog.CatalogFilterService;
import pl.lodz.p.it.tks.rest.domainmodel.catalogs.Book;
import pl.lodz.p.it.tks.rest.domainmodel.catalogs.Catalog;
import pl.lodz.p.it.tks.rest.domainmodel.catalogs.Movie;
import pl.lodz.p.it.tks.rest.ports.aggregates.catalog.CatalogRepoFilterAdapter;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@Dependent
public class CatalogFilterServiceImpl implements CatalogFilterService {

    @Inject
    private CatalogRepoFilterAdapter catalogAdapter;

    @Override
    public List<Catalog> filterCatalogs(String catalogFilter) {
        return catalogAdapter.filterCatalogs(catalogFilter);
    }

    @Override
    public List<Book> filterBooks(String catalogFilter) {
        return catalogAdapter.filterBooks(catalogFilter);
    }

    @Override
    public List<Movie> filterMovies(String catalogFilter) {
        return catalogAdapter.filterMovies(catalogFilter);
    }
}
