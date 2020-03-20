package pl.lodz.p.it.tks.appservices.services;

import pl.lodz.p.it.tks.domainmodel.Rent;
import pl.lodz.p.it.tks.domainmodel.users.Client;
import pl.lodz.p.it.tks.ports.aggregates.CatalogAdapter;
import pl.lodz.p.it.tks.ports.aggregates.UserAdapter;
import pl.lodz.p.it.tks.ports.aggregates.RentRepoFilterAdapter;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Named
@Dependent
public class RentService implements Serializable {



    @Inject
    private RentRepoFilterAdapter rentRepoAdapter;
    @Inject
    private UserAdapter userAdapter;
    @Inject
    private CatalogAdapter catalogAdapter;

//    public void addRent(String username, int catalogId, int year, int month, int day, int hour, int minute) {
//        if (userAdapter.getUser(username) instanceof Client
//                && userAdapter.getUser(username).isActive()
//                && getUnfinishedRentsForCatalog(catalogId).isEmpty()) {
//            Rent rent = new Rent((Client) userAdapter.getUser(username), catalogAdapter.getCatalog(catalogId));
//            rent.setRentDateTime(year, month, day, hour, minute);
//            rentRepoAdapter.addRent(rent);
//        }
//    }
//
//    public void removeRent(String id) {
//        rentRepoAdapter.removeRent(UUID.fromString(id));
//    }
//
//
//
//
//    public void finishRent(String id) {
//        Rent temp = new Rent(UUID.fromString(id));
//        if (getUnfinishedRents().contains(temp)) {
//            temp = getUnfinishedRents().get(getUnfinishedRents().indexOf(temp));
//            if (temp.getRentDateTime().isAfter(LocalDateTime.now())) {
//                return;
//            }
//            temp.setReturnDateTime(LocalDateTime.now().getYear(),
//                    LocalDateTime.now().getMonthValue(),
//                    LocalDateTime.now().getDayOfMonth(),
//                    LocalDateTime.now().getHour(),
//                    LocalDateTime.now().getMinute());
//            rentRepoAdapter.updateRent(UUID.fromString(id), temp);
//        }
//    }
}
