package pl.lodz.p.it.tks.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true, includeFieldNames = false)
public @Data class BookRestModel extends CatalogRestModel {

    @NotBlank
    private String author;

    public BookRestModel() {
        super();
    }

    public BookRestModel(int id, String title, String author, int releaseYear) {
        super(id, title, releaseYear);
        this.author = author;
    }
}
