package pl.lodz.p.it.model.catalogs;

import lombok.ToString;

@ToString(callSuper = true, includeFieldNames = false)
public class NoCatalogWeb extends CatalogWeb {

    public NoCatalogWeb() {
        super(0, "", 0);
    }
}