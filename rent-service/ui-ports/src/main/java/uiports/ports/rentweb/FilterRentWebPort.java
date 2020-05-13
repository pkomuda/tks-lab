package uiports.ports.rentweb;

import pl.lodz.p.it.model.RentWeb;

import java.util.List;

public interface FilterRentWebPort {

    List<RentWeb> filterUnfinishedRents(String rentFilter);
    List<RentWeb> filterFinishedRents(String rentFilter);
    List<RentWeb> filterUnfinishedRentsForClient(String username, String rentFilter);
    List<RentWeb> filterFinishedRentsForClient(String username, String rentFilter);
    List<RentWeb> filterUnfinishedRentsForCatalog(int id, String rentFilter);
    List<RentWeb> filterFinishedRentsForCatalog(int id, String rentFilter);
}
