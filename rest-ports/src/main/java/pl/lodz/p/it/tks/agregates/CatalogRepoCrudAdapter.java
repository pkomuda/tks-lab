package pl.lodz.p.it.tks.agregates;


import pl.lodz.p.it.tks.ports.AddCatalogPort;
import pl.lodz.p.it.tks.ports.RemoveCatalogPort;
import pl.lodz.p.it.tks.ports.UpdateCatalogPort;

public interface CatalogRepoCrudAdapter extends AddCatalogPort, UpdateCatalogPort, RemoveCatalogPort {

}
