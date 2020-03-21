package pl.lodz.p.it.tks.appservices.services.rent;

import pl.lodz.p.it.tks.domainmodel.Rent;

import java.util.List;

public interface RentGetService {

    List<Rent> getUnfinishedRents();
    List<Rent> getFinishedRents();
    List<Rent> getUnfinishedRentsForClient(String username);
    List<Rent> getFinishedRentsForClient(String username);
    List<Rent> getUnfinishedRentsForCatalog(int id);
    List<Rent> getFinishedRentsForCatalog(int id);
}
