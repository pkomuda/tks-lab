package pl.lodz.p.it.tks.appservices.services.rent;

import pl.lodz.p.it.tks.domainmodel.Rent;

import java.util.List;

public interface RentGetServiceInterface {

    public List<Rent> getUnfinishedRents();

    public List<Rent> getFinishedRents();

    public List<Rent> getUnfinishedRentsForClient(String username);

    public List<Rent> getFinishedRentsForClient(String username);

    public List<Rent> getUnfinishedRentsForCatalog(int id);

    public List<Rent> getFinishedRentsForCatalog(int id);

}
