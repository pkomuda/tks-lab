package pl.lodz.p.it.tks.converters;
import pl.lodz.p.it.tks.domainmodel.catalogs.Book;
import pl.lodz.p.it.tks.domainmodel.catalogs.Catalog;
import pl.lodz.p.it.tks.domainmodel.catalogs.Movie;
import pl.lodz.p.it.tks.domainmodel.catalogs.NoCatalog;
import pl.lodz.p.it.tks.model.BookRestModel;
import pl.lodz.p.it.tks.model.CatalogRestModel;
import pl.lodz.p.it.tks.model.MovieRestModel;
import pl.lodz.p.it.tks.model.NoCatalogRestModel;

import java.util.List;
import java.util.stream.Collectors;

public class RestConverter {
    public static CatalogRestModel domainToRest(Catalog catalog) {
        if(catalog instanceof Book){
            BookRestModel catalogRestModel = new BookRestModel();
            catalogRestModel.setReleaseYear(catalog.getReleaseYear());
            catalogRestModel.setId(catalog.getId());
            catalogRestModel.setTitle(catalog.getTitle());
            catalogRestModel.setAuthor(catalogRestModel.getAuthor());
            return catalogRestModel;
        }
     else if (catalog instanceof Movie) {
        return new MovieRestModel(
                catalog.getId(),
                catalog.getTitle(),
                ((Movie) catalog).getDirector(),
                catalog.getReleaseYear(),
                ((Movie) catalog).getFormat()
        );
    } else if (catalog instanceof NoCatalog) {
        return new NoCatalogRestModel();
    } else {
        return null;
    }
    }


    public static Catalog restToDomain(CatalogRestModel catalogRestModel){
        if(catalogRestModel instanceof BookRestModel){
            Book book = new Book();
            book.setReleaseYear(catalogRestModel.getReleaseYear());
            book.setId(catalogRestModel.getId());
            book.setTitle(catalogRestModel.getTitle());
            book.setAuthor(book.getAuthor());
            return book;
        }
        else if (catalogRestModel instanceof MovieRestModel) {
            return new Movie(
                    catalogRestModel.getId(),
                    catalogRestModel.getTitle(),
                    ((MovieRestModel) catalogRestModel).getDirector(),
                    catalogRestModel.getReleaseYear(),
                    ((MovieRestModel) catalogRestModel).getFormat()
            );
        } else if (catalogRestModel instanceof NoCatalogRestModel) {
            return new NoCatalog();
        } else {
            return null;
        }
    }
    public static List<BookRestModel> domainToRestBooks(List<Book> books) {
        return books
                .stream()
                .map(book -> (BookRestModel) RestConverter.domainToRest(book))
                .collect(Collectors.toList());
    }

    public static List<MovieRestModel> domainToRestMovies(List<Movie> movies) {
        return movies
                .stream()
                .map(movie -> (MovieRestModel) RestConverter.domainToRest(movie))
                .collect(Collectors.toList());
    }

    public static List<CatalogRestModel> domainToRestCatalogs(List<Catalog> catalogs) {
        return catalogs
                .stream()
                .map(catalog -> (CatalogRestModel) RestConverter.domainToRest(catalog))
                .collect(Collectors.toList());
    }
    public static Book restToDomainBook(BookRestModel book){
        return new Book(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getReleaseYear()
        );

    }

    public static Movie restToDomainMovie(MovieRestModel movieRestModel){
        return new Movie(
                movieRestModel.getId(),
                movieRestModel.getTitle(),
                movieRestModel.getDirector(),
                movieRestModel.getReleaseYear(),
                movieRestModel.getFormat()
        );
    }
}
