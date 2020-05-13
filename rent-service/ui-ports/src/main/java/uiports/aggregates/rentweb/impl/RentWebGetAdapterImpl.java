package uiports.aggregates.rentweb.impl;

import pl.lodz.p.it.model.RentWeb;
import pl.lodz.p.it.tks.appservices.services.rent.RentGetService;
import uiports.aggregates.rentweb.RentWebGetAdapter;
import uiports.converters.RentWebConverter;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named
@RequestScoped
public class RentWebGetAdapterImpl implements RentWebGetAdapter {

    @Inject
    private RentGetService rentGetService;

    @Override
    public List<RentWeb> getUnfinishedRents() {
        return RentWebConverter.domainToWebRents(rentGetService.getUnfinishedRents());
    }

    @Override
    public List<RentWeb> getFinishedRents() {
        return RentWebConverter.domainToWebRents(rentGetService.getFinishedRents());
    }

    @Override
    public List<RentWeb> getUnfinishedRentsForClient(String username) {
        return RentWebConverter.domainToWebRents(rentGetService.getUnfinishedRentsForClient(username));
    }

    @Override
    public List<RentWeb> getFinishedRentsForClient(String username) {
        return RentWebConverter.domainToWebRents(rentGetService.getFinishedRentsForClient(username));
    }

    @Override
    public List<RentWeb> getRentsForCatalog(int id) {
        List<RentWeb> rents = new ArrayList<>();
        rents.addAll(RentWebConverter.domainToWebRents(rentGetService.getUnfinishedRentsForCatalog(id)));
        rents.addAll(RentWebConverter.domainToWebRents(rentGetService.getFinishedRentsForCatalog(id)));
        return rents;
    }

    @Override
    public List<RentWeb> getUnfinishedRentsForCatalog(int id) {
        return RentWebConverter.domainToWebRents(rentGetService.getUnfinishedRentsForCatalog(id));
    }

    @Override
    public List<RentWeb> getFinishedRentsForCatalog(int id) {
        return RentWebConverter.domainToWebRents(rentGetService.getFinishedRentsForCatalog(id));
    }
}
