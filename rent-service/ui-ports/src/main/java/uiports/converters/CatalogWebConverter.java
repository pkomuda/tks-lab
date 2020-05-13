
package uiports.converters;


import pl.lodz.p.it.model.catalogs.BookWeb;
import pl.lodz.p.it.model.catalogs.CatalogWeb;
import pl.lodz.p.it.model.catalogs.MovieWeb;
import pl.lodz.p.it.model.catalogs.NoCatalogWeb;
import pl.lodz.p.it.tks.rest.domainmodel.catalogs.Book;
import pl.lodz.p.it.tks.rest.domainmodel.catalogs.Catalog;
import pl.lodz.p.it.tks.rest.domainmodel.catalogs.Movie;
import pl.lodz.p.it.tks.rest.domainmodel.catalogs.NoCatalog;

public class CatalogWebConverter {

    public static CatalogWeb domainToWeb(Catalog catalog) {
        if (catalog instanceof Book) {
            return new BookWeb(
                    catalog.getId(),
                    catalog.getTitle(),
                    ((Book) catalog).getAuthor(),
                    catalog.getReleaseYear()
            );
        } else if (catalog instanceof Movie) {
            return new MovieWeb(
                    catalog.getId(),
                    catalog.getTitle(),
                    ((Movie) catalog).getDirector(),
                    catalog.getReleaseYear(),
                    ((Movie) catalog).getFormat()
            );
        } else if (catalog instanceof NoCatalog) {
            return new NoCatalogWeb();
        } else {
            return null;
        }
    }
}
