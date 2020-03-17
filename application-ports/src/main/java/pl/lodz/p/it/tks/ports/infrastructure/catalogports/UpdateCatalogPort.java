package pl.lodz.p.it.tks.ports.infrastructure.catalogports;

import pl.lodz.p.it.tks.domainmodel.catalogs.Catalog;

public interface UpdateCatalogPort {

    void updateCatalog(int id, Catalog catalog);
}
