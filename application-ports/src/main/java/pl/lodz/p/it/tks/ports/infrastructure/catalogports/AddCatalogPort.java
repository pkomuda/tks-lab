package pl.lodz.p.it.tks.ports.infrastructure.catalogports;

import pl.lodz.p.it.tks.domainmodel.catalogs.Catalog;

public interface AddCatalogPort {

    void addCatalog(Catalog catalog);
}
