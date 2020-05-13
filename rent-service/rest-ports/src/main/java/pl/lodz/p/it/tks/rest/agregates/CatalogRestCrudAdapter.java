package pl.lodz.p.it.tks.rest.agregates;


import pl.lodz.p.it.tks.rest.ports.AddCatalogPort;
import pl.lodz.p.it.tks.rest.ports.RemoveCatalogPort;
import pl.lodz.p.it.tks.rest.ports.UpdateCatalogPort;

public interface CatalogRestCrudAdapter extends AddCatalogPort, UpdateCatalogPort, RemoveCatalogPort {

}
