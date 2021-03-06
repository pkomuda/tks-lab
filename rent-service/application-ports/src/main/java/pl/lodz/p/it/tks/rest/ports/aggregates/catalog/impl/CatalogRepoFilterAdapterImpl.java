package pl.lodz.p.it.tks.rest.ports.aggregates.catalog.impl;

import pl.lodz.p.it.tks.rest.adapters.repositories.CatalogRepository;
import pl.lodz.p.it.tks.rest.domainmodel.catalogs.Book;
import pl.lodz.p.it.tks.rest.domainmodel.catalogs.Catalog;
import pl.lodz.p.it.tks.rest.domainmodel.catalogs.Movie;
import pl.lodz.p.it.tks.rest.ports.aggregates.catalog.CatalogRepoFilterAdapter;
import pl.lodz.p.it.tks.rest.ports.userinterface.CatalogInput;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.stream.Collectors;

@Named
@Dependent
public class CatalogRepoFilterAdapterImpl implements CatalogRepoFilterAdapter {

    @Inject
    private CatalogRepository catalogRepository;
    @Inject
    private CatalogInput catalogInput;

    @Override
    public List<Catalog> filterCatalogs(String catalogFilter) {
        return catalogRepository.filterCatalogs(catalogFilter)
                .stream()
                .map(catalogEntity -> catalogInput.convert(catalogEntity))
                .collect(Collectors.toList());
    }

    @Override
    public List<Book> filterBooks(String bookFilter) {
        return catalogRepository.filterBooks(bookFilter)
                .stream()
                .map(catalogEntity -> (Book) catalogInput.convert(catalogEntity))
                .collect(Collectors.toList());
    }

    @Override
    public List<Movie> filterMovies(String movieFilter) {
        return catalogRepository.filterMovies(movieFilter)
                .stream()
                .map(catalogEntity -> (Movie) catalogInput.convert(catalogEntity))
                .collect(Collectors.toList());
    }
}
