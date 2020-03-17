package pl.lodz.p.it.tks.ports.userinterface.catalogports;

import pl.lodz.p.it.tks.domainmodel.catalogs.Catalog;

public interface GetCatalogPort {

    Catalog getCatalog(int id);
}
