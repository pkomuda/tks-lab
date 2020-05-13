package pl.lodz.p.it.tks.rest.ports.userinterface.rentports;

import pl.lodz.p.it.tks.rest.domainmodel.Rent;

import java.util.List;

public interface GetRentsPort {

    List<Rent> getUnfinishedRents();
    List<Rent> getFinishedRents();
    List<Rent> getUnfinishedRentsForClient(String username);
    List<Rent> getFinishedRentsForClient(String username);
    List<Rent> getRentsForCatalog(int id);
    List<Rent> getUnfinishedRentsForCatalog(int id);
    List<Rent> getFinishedRentsForCatalog(int id);
}
