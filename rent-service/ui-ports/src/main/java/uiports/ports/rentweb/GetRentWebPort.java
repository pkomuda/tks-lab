package uiports.ports.rentweb;

import pl.lodz.p.it.model.RentWeb;

import java.util.List;

public interface GetRentWebPort {

    List<RentWeb> getUnfinishedRents();
    List<RentWeb> getFinishedRents();
    List<RentWeb> getUnfinishedRentsForClient(String username);
    List<RentWeb> getFinishedRentsForClient(String username);
    List<RentWeb> getRentsForCatalog(int id);
    List<RentWeb> getUnfinishedRentsForCatalog(int id);
    List<RentWeb> getFinishedRentsForCatalog(int id);
}
