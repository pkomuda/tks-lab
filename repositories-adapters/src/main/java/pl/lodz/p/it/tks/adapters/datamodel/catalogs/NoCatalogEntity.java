package pl.lodz.p.it.tks.adapters.datamodel.catalogs;

import javax.persistence.Entity;

@Entity(name = "NoCatalog")
public class NoCatalogEntity extends CatalogEntity {

    public NoCatalogEntity() {
        super(0, "", 0);
    }
}
