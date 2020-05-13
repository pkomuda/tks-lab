package pl.lodz.p.it.tks.rest.ports.aggregates.catalog;

import pl.lodz.p.it.tks.rest.ports.infrastructure.catalogports.AddCatalogPort;
import pl.lodz.p.it.tks.rest.ports.infrastructure.catalogports.RemoveCatalogPort;
import pl.lodz.p.it.tks.rest.ports.infrastructure.catalogports.UpdateCatalogPort;

public interface CatalogRepoCrudAdapter extends AddCatalogPort, UpdateCatalogPort, RemoveCatalogPort {

}
