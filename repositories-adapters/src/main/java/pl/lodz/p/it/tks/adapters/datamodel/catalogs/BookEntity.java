package pl.lodz.p.it.tks.adapters.datamodel.catalogs;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;

@Entity(name = "Book")
@EqualsAndHashCode(callSuper = true)
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
