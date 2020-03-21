package pl.lodz.p.it.tks.ports.aggregates.catalog;

import pl.lodz.p.it.tks.ports.infrastructure.catalogports.AddCatalogPort;
import pl.lodz.p.it.tks.ports.infrastructure.catalogports.RemoveCatalogPort;
import pl.lodz.p.it.tks.ports.infrastructure.catalogports.UpdateCatalogPort;

public interface CatalogRepoCrudAdapter extends AddCatalogPort, UpdateCatalogPort, RemoveCatalogPort {

}
