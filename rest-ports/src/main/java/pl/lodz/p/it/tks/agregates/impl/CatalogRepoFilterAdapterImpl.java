package pl.lodz.p.it.tks.agregates.impl;


import pl.lodz.p.it.tks.agregates.CatalogRepoFilterAdapter;
import pl.lodz.p.it.tks.appservices.services.catalog.CatalogFilterService;
import pl.lodz.p.it.tks.converters.RestConverter;
import pl.lodz.p.it.tks.domainmodel.catalogs.Book;

import pl.lodz.p.it.tks.domainmodel.catalogs.Movie;
import pl.lodz.p.it.tks.model.BookRestModel;
import pl.lodz.p.it.tks.model.CatalogRestModel;
import pl.lodz.p.it.tks.model.MovieRestModel;


import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.stream.Collectors;

@Named
@Dependent
public class CatalogRepoFilterAdapterImpl implements CatalogRepoFilterAdapter {

    @Inject
    private CatalogFilterService catalogFilterService;

    @Override
    public List<CatalogRestModel> filterCatalogs(String catalogFilter) {
        return RestConverter.domainToRestCatalogs(catalogFilterService.filterCatalogs(catalogFilter));
    }

    @Override
    public List<BookRestModel> filterBooks(String bookFilter) {
        return RestConverter.domainToRestBooks(catalogFilterService.filterBooks(bookFilter));
    }

    @Override
    public List<MovieRestModel> filterMovies(String movieFilter) {
        return RestConverter.domainToRestMovies(catalogFilterService.filterMovies(movieFilter));
    }
}
