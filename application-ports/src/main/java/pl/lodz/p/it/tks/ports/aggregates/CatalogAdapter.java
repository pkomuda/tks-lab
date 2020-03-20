package pl.lodz.p.it.tks.ports.aggregates;

import pl.lodz.p.it.tks.adapters.repositories.CatalogRepository;
import pl.lodz.p.it.tks.domainmodel.catalogs.Book;
import pl.lodz.p.it.tks.domainmodel.catalogs.Catalog;
import pl.lodz.p.it.tks.domainmodel.catalogs.Movie;
import pl.lodz.p.it.tks.ports.infrastructure.CatalogOutput;
import pl.lodz.p.it.tks.ports.infrastructure.catalogports.AddCatalogPort;
import pl.lodz.p.it.tks.ports.infrastructure.catalogports.UpdateCatalogPort;
import pl.lodz.p.it.tks.ports.userinterface.CatalogInput;
import pl.lodz.p.it.tks.ports.userinterface.catalogports.GetCatalogPort;
import pl.lodz.p.it.tks.ports.userinterface.catalogports.FilterCatalogPort;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.stream.Collectors;

@Named
@ApplicationScoped
public class CatalogAdapter implements AddCatalogPort, UpdateCatalogPort, GetCatalogPort, FilterCatalogPort {

    @Inject
    private CatalogRepository catalogRepository;
    @Inject
    private CatalogOutput catalogOutput;
    @Inject
    private CatalogInput catalogInput;

    @Override
    public void addCatalog(Catalog catalog) {
        catalogRepository.addCatalog(catalogOutput.convert(catalog));
    }

    @Override
    public Catalog getCatalog(int id) {
        return catalogInput.convert(catalogRepository.getCatalog(id));
    }

    @Override
    public void updateCatalog(int id, Catalog catalog) {
        catalogRepository.updateCatalog(id, catalogOutput.convert(catalog));
    }

    public void removeCatalog(int id) {
        catalogRepository.removeCatalog(id);
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
