package pl.lodz.p.it.tks.ports.userinterface.controller.converters;

import pl.lodz.p.it.model.catalogs.BookWeb;
import pl.lodz.p.it.model.catalogs.CatalogWeb;
import pl.lodz.p.it.model.catalogs.MovieWeb;
import pl.lodz.p.it.model.catalogs.NoCatalogWeb;
import pl.lodz.p.it.tks.domainmodel.catalogs.Book;
import pl.lodz.p.it.tks.domainmodel.catalogs.Catalog;
import pl.lodz.p.it.tks.domainmodel.catalogs.Movie;
import pl.lodz.p.it.tks.domainmodel.catalogs.NoCatalog;


public class CatalogConverter {

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

//    public static Catalog webToDomain(CatalogWeb catalogWeb) {
//        if (catalogWeb instanceof BookWeb) {
//            return new Book(
//                    catalogWeb.getId(),
//                    catalogWeb.getTitle(),
//                    ((BookWeb) catalogWeb).getAuthor(),
//                    catalogWeb.getReleaseYear()
//            );
//        } else if (catalogWeb instanceof MovieWeb) {
//            return new Movie(
//                    catalogWeb.getId(),
//                    catalogWeb.getTitle(),
//                    ((MovieWeb) catalogWeb).getDirector(),
//                    catalogWeb.getReleaseYear(),
//                    ((MovieWeb) catalogWeb).getFormat()
//            );
//        } else if (catalogWeb instanceof NoCatalogWeb) {
//            return new NoCatalog();
//        } else {
//            return null;
//        }
//    }
}
