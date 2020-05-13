package pl.lodz.p.it.tks.rest.domainmodel.catalogs;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true, includeFieldNames = false)
public @Data class Book extends Catalog {

    @NotBlank
    private String author;

    public Book() {
        super();
    }

    public Book(int id, String title, String author, int releaseYear) {
        super(id, title, releaseYear);
        this.author = author;
    }
}
