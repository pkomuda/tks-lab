package pl.lodz.p.it.tks.rest.ports.infrastructure.catalogports;

import pl.lodz.p.it.tks.rest.domainmodel.catalogs.Catalog;

public interface UpdateCatalogPort {

    void updateCatalog(int id, Catalog catalog);
}
