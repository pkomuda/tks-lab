package pl.lodz.p.it.tks.ports.aggregates.catalog.impl;

import pl.lodz.p.it.tks.adapters.repositories.CatalogRepository;
import pl.lodz.p.it.tks.domainmodel.catalogs.Catalog;
import pl.lodz.p.it.tks.ports.aggregates.catalog.CatalogRepoCrudAdapter;
import pl.lodz.p.it.tks.ports.infrastructure.CatalogOutput;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@Dependent
public class CatalogRepoCrudAdapterImpl implements CatalogRepoCrudAdapter {

    @Inject
    private CatalogRepository catalogRepository;
    @Inject
    private CatalogOutput catalogOutput;

    @Override
    public void addCatalog(Catalog catalog) {
        catalogRepository.addCatalog(catalogOutput.convert(catalog));
    }

    public void removeCatalog(int id) {
        catalogRepository.removeCatalog(id);
    }

    @Override
    public void updateCatalog(int id, Catalog catalog) {
        catalogRepository.updateCatalog(id, catalogOutput.convert(catalog));
    }

}
