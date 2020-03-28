package pl.lodz.p.it.webmodel.catalogs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString(includeFieldNames = false)
public abstract @Data class CatalogWeb {

    private int id;
    private String title;
    private int releaseYear;
}