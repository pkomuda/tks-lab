package pl.lodz.p.it.tks.rest.agregates.impl;

import pl.lodz.p.it.tks.rest.agregates.CatalogRestGetAdapter;
import pl.lodz.p.it.tks.rest.appservices.services.catalog.CatalogGetService;
import pl.lodz.p.it.tks.rest.converters.RestConverter;
import pl.lodz.p.it.tks.rest.model.BookRestModel;
import pl.lodz.p.it.tks.rest.model.CatalogRestModel;
import pl.lodz.p.it.tks.rest.model.MovieRestModel;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@ApplicationScoped
public class CatalogRestGetAdapterImpl implements CatalogRestGetAdapter {

    @Inject
    private CatalogGetService catalogGetService;


    @Override
    public List<CatalogRestModel> getCatalogs() {
        return RestConverter.domainToRestCatalogs(catalogGetService.getCatalogs());
}

    @Override
    public List<BookRestModel> getBooks() {
        return RestConverter.domainToRestBooks(catalogGetService.getBooks());
    }

    @Override
    public List<MovieRestModel> getMovies() {
        return RestConverter.domainToRestMovies(catalogGetService.getMovies());
    }
}
