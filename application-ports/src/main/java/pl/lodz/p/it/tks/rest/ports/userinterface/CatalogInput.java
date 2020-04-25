package pl.lodz.p.it.tks.rest.ports.userinterface;

import pl.lodz.p.it.tks.rest.adapters.datamodel.catalogs.BookEntity;
import pl.lodz.p.it.tks.rest.adapters.datamodel.catalogs.CatalogEntity;
import pl.lodz.p.it.tks.rest.adapters.datamodel.catalogs.MovieEntity;
import pl.lodz.p.it.tks.rest.adapters.datamodel.catalogs.NoCatalogEntity;
import pl.lodz.p.it.tks.rest.domainmodel.catalogs.Book;
import pl.lodz.p.it.tks.rest.domainmodel.catalogs.Catalog;
import pl.lodz.p.it.tks.rest.domainmodel.catalogs.Movie;
import pl.lodz.p.it.tks.rest.domainmodel.catalogs.NoCatalog;

import javax.enterprise.context.Dependent;
import javax.inject.Named;

@Named
@Dependent
public class CatalogInput {

    public Catalog convert(CatalogEntity catalogEnt) {
        if (catalogEnt instanceof BookEntity) {
            return new Book(
                    catalogEnt.getId(),
                    catalogEnt.getTitle(),
                    ((BookEntity) catalogEnt).getAuthor(),
                    catalogEnt.getReleaseYear()
            );
        } else if (catalogEnt instanceof MovieEntity) {
            return new Movie(
                    catalogEnt.getId(),
                    catalogEnt.getTitle(),
                    ((MovieEntity) catalogEnt).getDirector(),
                    catalogEnt.getReleaseYear(),
                    ((MovieEntity) catalogEnt).getFormat()
            );
        } else if (catalogEnt instanceof NoCatalogEntity) {
            return new NoCatalog();
        } else {
            return null;
        }
    }
}
