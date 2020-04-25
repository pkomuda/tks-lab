package pl.lodz.p.it.tks.rest.appservices.services.rent.impl;

import pl.lodz.p.it.tks.rest.appservices.services.rent.RentGetService;
import pl.lodz.p.it.tks.rest.domainmodel.Rent;
import pl.lodz.p.it.tks.rest.ports.aggregates.rent.RentRepoGetAdapter;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@Dependent
public class RentGetServiceImpl implements Serializable, RentGetService {

    @Inject
    private RentRepoGetAdapter rentRepoGetAdapter;

    public List<Rent> getUnfinishedRents() {
        return rentRepoGetAdapter.getUnfinishedRents();
    }

    public List<Rent> getFinishedRents() {
        return rentRepoGetAdapter.getFinishedRents();
    }

    public List<Rent> getUnfinishedRentsForClient(String username) {
        return rentRepoGetAdapter.getUnfinishedRentsForClient(username);
    }

    public List<Rent> getFinishedRentsForClient(String username) {
        return rentRepoGetAdapter.getFinishedRentsForClient(username);
    }

    public List<Rent> getUnfinishedRentsForCatalog(int id) {
        return rentRepoGetAdapter.getUnfinishedRentsForCatalog(id);
    }

    public List<Rent> getFinishedRentsForCatalog(int id) {
        return rentRepoGetAdapter.getFinishedRentsForCatalog(id);
    }
}
