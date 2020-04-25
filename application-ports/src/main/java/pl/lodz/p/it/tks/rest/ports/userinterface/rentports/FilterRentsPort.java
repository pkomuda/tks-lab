package pl.lodz.p.it.tks.rest.ports.userinterface.rentports;

import pl.lodz.p.it.tks.rest.domainmodel.Rent;

import java.util.List;

public interface FilterRentsPort {

    List<Rent> filterUnfinishedRents(String rentFilter);
    List<Rent> filterFinishedRents(String rentFilter);
    List<Rent> filterUnfinishedRentsForClient(String username, String rentFilter);
    List<Rent> filterFinishedRentsForClient(String username, String rentFilter);
    List<Rent> filterUnfinishedRentsForCatalog(int id, String rentFilter);
    List<Rent> filterFinishedRentsForCatalog(int id, String rentFilter);
}
