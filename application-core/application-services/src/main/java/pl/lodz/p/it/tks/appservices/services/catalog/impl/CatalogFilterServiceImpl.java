package pl.lodz.p.it.tks.appservices.services.catalog.impl;

import pl.lodz.p.it.tks.appservices.services.catalog.CatalogFilterService;
import pl.lodz.p.it.tks.domainmodel.catalogs.Book;
import pl.lodz.p.it.tks.domainmodel.catalogs.Catalog;
import pl.lodz.p.it.tks.domainmodel.catalogs.Movie;
import pl.lodz.p.it.tks.ports.aggregates.catalog.CatalogRepoFilterAdapter;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.List;

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
