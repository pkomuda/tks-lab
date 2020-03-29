package pl.lodz.p.it.model.catalogs;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true, includeFieldNames = false)
public @Data class MovieWeb extends CatalogWeb {

    private String director;
    private String format;

    public MovieWeb() {
        super();
    }

    public MovieWeb(int id, String title, String director, int releaseYear, String format) {
        super(id, title, releaseYear);
        this.director = director;
        this.format = format;
    }
}