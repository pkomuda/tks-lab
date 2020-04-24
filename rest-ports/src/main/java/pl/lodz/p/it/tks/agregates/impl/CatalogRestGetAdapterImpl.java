package pl.lodz.p.it.tks.agregates.impl;

import pl.lodz.p.it.tks.agregates.CatalogRestGetAdapter;
import pl.lodz.p.it.tks.appservices.services.catalog.CatalogGetService;
import pl.lodz.p.it.tks.converters.RestConverter;
import pl.lodz.p.it.tks.model.BookRestModel;
import pl.lodz.p.it.tks.model.CatalogRestModel;
import pl.lodz.p.it.tks.model.MovieRestModel;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@Dependent
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
