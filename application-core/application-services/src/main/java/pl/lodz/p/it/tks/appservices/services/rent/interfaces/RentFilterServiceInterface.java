package pl.lodz.p.it.tks.appservices.services.rent;

import pl.lodz.p.it.tks.domainmodel.Rent;

import java.util.List;

public interface RentFilterServiceInterface {

    public List<Rent> filterUnfinishedRents(String rentFilter) ;

    public List<Rent> filterFinishedRents(String rentFilter);

    public List<Rent> filterUnfinishedRentsForClient(String username, String rentFilter);

    public List<Rent> filterFinishedRentsForClient(String username, String rentFilter);

    public List<Rent> filterUnfinishedRentsForCatalog(int id, String rentFilter);

    public List<Rent> filterFinishedRentsForCatalog(int id, String rentFilter) ;
}
