package pl.lodz.p.it.tks.domainmodel.catalogs;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true, includeFieldNames = false)
public @Data class Book extends Catalog {

    private String author;

    public Book() {
        super();
    }

    public Book(int id, String title, String author, int releaseYear) {
        super(id, title, releaseYear);
        this.author = author;
    }
}