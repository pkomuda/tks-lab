package pl.lodz.p.it.tks.rest.adapters.datamodel.catalogs;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Entity;

@Entity(name = "Book")
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true, includeFieldNames = false)
public @Data class BookEntity extends CatalogEntity {

    private String author;

    public BookEntity() {
        super();
    }

    public BookEntity(int id, String title, String author, int releaseYear) {
        super(id, title, releaseYear);
        this.author = author;
    }
}
