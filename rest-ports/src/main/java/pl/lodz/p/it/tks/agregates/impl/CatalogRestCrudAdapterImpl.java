package pl.lodz.p.it.tks.agregates.impl;


import pl.lodz.p.it.tks.agregates.CatalogRestCrudAdapter;
import pl.lodz.p.it.tks.appservices.services.catalog.CatalogCrudService;
import pl.lodz.p.it.tks.converters.RestConverter;
import pl.lodz.p.it.tks.model.BookRestModel;
import pl.lodz.p.it.tks.model.MovieRestModel;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@Dependent
public class CatalogRestCrudAdapterImpl implements CatalogRestCrudAdapter {

    @Inject
    private CatalogCrudService catalogCrudService;



    public void removeCatalog(int id) {
        catalogCrudService.removeCatalog(id);
    }


    @Override
    public void addBook(BookRestModel book) {
        catalogCrudService.addBook(RestConverter.restToDomainBook(book));
    }

    @Override
    public void addMovie(MovieRestModel movie) {
        catalogCrudService.addMovie(RestConverter.restToDomainMovie(movie));
    }

    @Override
    public void updateBook(int id, BookRestModel book) {
        catalogCrudService.updateBook(id,RestConverter.restToDomainBook(book));
    }

    @Override
    public void updateMovie(int id, MovieRestModel movieRestModel) {
        catalogCrudService.updateMovie(id,RestConverter.restToDomainMovie(movieRestModel));
    }
}
