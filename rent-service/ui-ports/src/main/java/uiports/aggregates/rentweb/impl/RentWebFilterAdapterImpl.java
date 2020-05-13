package uiports.aggregates.rentweb.impl;

import pl.lodz.p.it.model.RentWeb;
import pl.lodz.p.it.tks.appservices.services.rent.RentFilterService;
import uiports.aggregates.rentweb.RentWebFilterAdapter;
import uiports.converters.RentWebConverter;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@RequestScoped
public class RentWebFilterAdapterImpl implements RentWebFilterAdapter {

    @Inject
    private RentFilterService rentFilterService;

    @Override
    public List<RentWeb> filterUnfinishedRents(String rentFilter) {
        return RentWebConverter.domainToWebRents(rentFilterService.filterUnfinishedRents(rentFilter));
    }

    @Override
    public List<RentWeb> filterFinishedRents(String rentFilter) {
        return RentWebConverter.domainToWebRents(rentFilterService.filterFinishedRents(rentFilter));
    }

    @Override
    public List<RentWeb> filterUnfinishedRentsForClient(String username, String rentFilter) {
        return RentWebConverter.domainToWebRents(rentFilterService.filterUnfinishedRentsForClient(username, rentFilter));
    }

    @Override
    public List<RentWeb> filterFinishedRentsForClient(String username, String rentFilter) {
        return RentWebConverter.domainToWebRents(rentFilterService.filterFinishedRentsForClient(username, rentFilter));
    }

    @Override
    public List<RentWeb> filterUnfinishedRentsForCatalog(int id, String rentFilter) {
        return RentWebConverter.domainToWebRents(rentFilterService.filterUnfinishedRentsForCatalog(id, rentFilter));
    }

    @Override
    public List<RentWeb> filterFinishedRentsForCatalog(int id, String rentFilter) {
        return RentWebConverter.domainToWebRents(rentFilterService.filterFinishedRentsForCatalog(id, rentFilter));
    }
}
