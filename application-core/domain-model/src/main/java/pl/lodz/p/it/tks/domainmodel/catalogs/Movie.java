package pl.lodz.p.it.tks.domainmodel.catalogs;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true, includeFieldNames = false)
public @Data class Movie extends Catalog {

    @NotBlank
    private String director;
    @NotBlank
    private String format;

    public Movie() {
        super();
    }

    public Movie(int id, String title, String director, int releaseYear, String format) {
        super(id, title, releaseYear);
        this.director = director;
        this.format = format;
    }
}
