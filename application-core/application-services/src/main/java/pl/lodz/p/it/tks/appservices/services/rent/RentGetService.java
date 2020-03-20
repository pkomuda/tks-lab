package pl.lodz.p.it.tks.appservices.services.rent;

import pl.lodz.p.it.tks.domainmodel.Rent;
import pl.lodz.p.it.tks.ports.aggregates.RentRepoGetAdapter;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;


@Named
@Dependent
public class RentGetService implements Serializable {

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
