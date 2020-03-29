package pl.lodz.p.it.model.catalogs;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true, includeFieldNames = false)
public @Data
class BookWeb extends CatalogWeb {

    private String author;

    public BookWeb() {
        super();
    }

    public BookWeb(int id, String title, String author, int releaseYear) {
        super(id, title, releaseYear);
        this.author = author;
    }
}