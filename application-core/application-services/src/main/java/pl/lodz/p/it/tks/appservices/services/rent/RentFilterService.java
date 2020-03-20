package pl.lodz.p.it.tks.appservices.services.rent;


import pl.lodz.p.it.tks.appservices.services.rent.interfaces.RentFilterServiceInterface;
import pl.lodz.p.it.tks.domainmodel.Rent;
import pl.lodz.p.it.tks.ports.aggregates.rent.RentRepoFilterAdapter;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@Dependent
public class RentFilterService implements Serializable, RentFilterServiceInterface {

    @Inject
    RentRepoFilterAdapter rentRepoFilterAdapter;

    public List<Rent> filterUnfinishedRents(String rentFilter) {
        return rentRepoFilterAdapter.filterUnfinishedRents(rentFilter);
    }

    public List<Rent> filterFinishedRents(String rentFilter) {
        return rentRepoFilterAdapter.filterFinishedRents(rentFilter);
    }

    public List<Rent> filterUnfinishedRentsForClient(String username, String rentFilter) {
        return rentRepoFilterAdapter.filterUnfinishedRentsForClient(username, rentFilter);
    }

    public List<Rent> filterFinishedRentsForClient(String username, String rentFilter) {
        return rentRepoFilterAdapter.filterFinishedRentsForClient(username, rentFilter);
    }

    public List<Rent> filterUnfinishedRentsForCatalog(int id, String rentFilter) {
        return rentRepoFilterAdapter.filterUnfinishedRentsForCatalog(id, rentFilter);
    }

    public List<Rent> filterFinishedRentsForCatalog(int id, String rentFilter) {
        return rentRepoFilterAdapter.filterFinishedRentsForCatalog(id, rentFilter);
    }

}
