package pl.lodz.p.it.tks.rest.ports.aggregates.catalog.impl;

import pl.lodz.p.it.tks.rest.adapters.repositories.CatalogRepository;
import pl.lodz.p.it.tks.rest.domainmodel.catalogs.Book;
import pl.lodz.p.it.tks.rest.domainmodel.catalogs.Catalog;
import pl.lodz.p.it.tks.rest.domainmodel.catalogs.Movie;
import pl.lodz.p.it.tks.rest.ports.aggregates.catalog.CatalogRepoGetAdapter;
import pl.lodz.p.it.tks.rest.ports.userinterface.CatalogInput;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.stream.Collectors;

@Named
@Dependent
public class CatalogRepoGetAdapterImpl implements CatalogRepoGetAdapter {

    @Inject
    private CatalogRepository catalogRepository;
    @Inject
    private CatalogInput catalogInput;

    @Override
    public Catalog getCatalog(int id) {
        return catalogInput.convert(catalogRepository.getCatalog(id));
    }

    @Override
    public List<Catalog> getCatalogs() {
        return catalogRepository.getCatalogs()
                .stream()
                .map(catalogEntity -> catalogInput.convert(catalogEntity))
                .collect(Collectors.toList());
    }

    @Override
    public List<Book> getBooks() {
        return catalogRepository.getBooks()
                .stream()
                .map(catalogEntity -> (Book) catalogInput.convert(catalogEntity))
                .collect(Collectors.toList());
    }

    @Override
    public List<Movie> getMovies() {
        return catalogRepository.getMovies()
                .stream()
                .map(catalogEntity -> (Movie) catalogInput.convert(catalogEntity))
                .collect(Collectors.toList());
    }
}
