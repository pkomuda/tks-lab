package pl.lodz.p.it.tks.rest.model;

import lombok.ToString;

@ToString(callSuper = true, includeFieldNames = false)
public class NoCatalogRestModel extends CatalogRestModel {

    public NoCatalogRestModel() {
        super(0, "", 0);
    }
}
