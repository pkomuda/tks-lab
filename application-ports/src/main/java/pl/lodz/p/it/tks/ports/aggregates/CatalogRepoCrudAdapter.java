package pl.lodz.p.it.tks.ports.aggregates;

import pl.lodz.p.it.tks.ports.infrastructure.catalogports.AddCatalogPort;
import pl.lodz.p.it.tks.ports.infrastructure.catalogports.RemoveCatalogPort;
import pl.lodz.p.it.tks.ports.infrastructure.catalogports.UpdateCatalogPort;
import pl.lodz.p.it.tks.ports.infrastructure.rentports.AddRentPort;

public interface CatalogRepoCrudAdapter extends AddCatalogPort, UpdateCatalogPort, RemoveCatalogPort {
}
