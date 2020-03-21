package pl.lodz.p.it.tks.appservices.services.rent.impl;

import pl.lodz.p.it.tks.appservices.services.rent.RentCrudService;
import pl.lodz.p.it.tks.domainmodel.Rent;
import pl.lodz.p.it.tks.domainmodel.users.Client;
import pl.lodz.p.it.tks.ports.aggregates.catalog.CatalogRepoGetAdapter;
import pl.lodz.p.it.tks.ports.aggregates.rent.RentRepoCrudAdapter;
import pl.lodz.p.it.tks.ports.aggregates.rent.RentRepoGetAdapter;
import pl.lodz.p.it.tks.ports.aggregates.user.UserRepoGetAdapter;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Named
@Dependent
public class RentCrudServiceImpl implements Serializable, RentCrudService {

    @Inject
    private RentRepoCrudAdapter rentRepoCrudAdapter;
    @Inject
    private RentRepoGetAdapter rentRepoGetAdapter;
    @Inject
    private CatalogRepoGetAdapter catalogAdapter;
    @Inject
    private UserRepoGetAdapter userAdapter;

    public void addRent(String username, int catalogId, int year, int month, int day, int hour, int minute) {
        if (userAdapter.getUser(username) instanceof Client
                && userAdapter.getUser(username).isActive()
                && rentRepoGetAdapter.getUnfinishedRentsForCatalog(catalogId).isEmpty()) {
            Rent rent = new Rent((Client) userAdapter.getUser(username), catalogAdapter.getCatalog(catalogId));
            rent.setRentDateTime(year, month, day, hour, minute);
            rentRepoCrudAdapter.addRent(rent);
        }
    }

    public void removeRent(String id) {
        rentRepoCrudAdapter.removeRent(UUID.fromString(id));
    }




    public void finishRent(String id) {
        Rent temp = new Rent(UUID.fromString(id));
        if (rentRepoGetAdapter.getUnfinishedRents().contains(temp)) {
            temp = rentRepoGetAdapter.getUnfinishedRents().get(rentRepoGetAdapter.getUnfinishedRents().indexOf(temp));
            if (temp.getRentDateTime().isAfter(LocalDateTime.now())) {
                return;
            }
            temp.setReturnDateTime(LocalDateTime.now().getYear(),
                    LocalDateTime.now().getMonthValue(),
                    LocalDateTime.now().getDayOfMonth(),
                    LocalDateTime.now().getHour(),
                    LocalDateTime.now().getMinute());
            rentRepoCrudAdapter.updateRent(UUID.fromString(id), temp);
        }
    }


}
