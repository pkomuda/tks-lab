package pl.lodz.p.it.tks.adapters.datamodel.catalogs;

import lombok.ToString;

import javax.persistence.Entity;

@Entity(name = "NoCatalog")
@ToString(callSuper = true, includeFieldNames = false)
public class NoCatalogEntity extends CatalogEntity {

    public NoCatalogEntity() {
        super(0, "", 0);
    }
}
