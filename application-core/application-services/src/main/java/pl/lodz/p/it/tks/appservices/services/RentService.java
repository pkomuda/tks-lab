package pl.lodz.p.it.tks.appservices.services;

import pl.lodz.p.it.tks.domainmodel.Rent;
import pl.lodz.p.it.tks.domainmodel.users.Client;
import pl.lodz.p.it.tks.ports.aggregates.CatalogAdapter;
import pl.lodz.p.it.tks.ports.aggregates.RentAdapter;
import pl.lodz.p.it.tks.ports.aggregates.UserAdapter;

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
    private RentAdapter rentAdapter;
    @Inject
    private UserAdapter userAdapter;
    @Inject
    private CatalogAdapter catalogAdapter;

    public void addRent(String username, int catalogId, int year, int month, int day, int hour, int minute) {
        if (userAdapter.getUser(username) instanceof Client
                && userAdapter.getUser(username).isActive()
                && getUnfinishedRentsForCatalog(catalogId).isEmpty()) {
            Rent rent = new Rent((Client) userAdapter.getUser(username), catalogAdapter.getCatalog(catalogId));
            rent.setRentDateTime(year, month, day, hour, minute);
            rentAdapter.addRent(rent);
        }
    }

    public void removeRent(String id) {
        rentAdapter.removeRent(UUID.fromString(id));
    }

    public List<Rent> getUnfinishedRents() {
        return rentAdapter.getUnfinishedRents();
    }

    public List<Rent> getFinishedRents() {
        return rentAdapter.getFinishedRents();
    }

    public List<Rent> getUnfinishedRentsForClient(String username) {
        return rentAdapter.getUnfinishedRentsForClient(username);
    }

    public List<Rent> getFinishedRentsForClient(String username) {
        return rentAdapter.getFinishedRentsForClient(username);
    }

    public List<Rent> getUnfinishedRentsForCatalog(int id) {
        return rentAdapter.getUnfinishedRentsForCatalog(id);
    }

    public List<Rent> getFinishedRentsForCatalog(int id) {
        return rentAdapter.getFinishedRentsForCatalog(id);
    }

    public List<Rent> filterUnfinishedRents(String rentFilter) {
        return rentAdapter.filterUnfinishedRents(rentFilter);
    }

    public List<Rent> filterFinishedRents(String rentFilter) {
        return rentAdapter.filterFinishedRents(rentFilter);
    }

    public List<Rent> filterUnfinishedRentsForClient(String username, String rentFilter) {
        return rentAdapter.filterUnfinishedRentsForClient(username, rentFilter);
    }

    public List<Rent> filterFinishedRentsForClient(String username, String rentFilter) {
        return rentAdapter.filterFinishedRentsForClient(username, rentFilter);
    }

    public List<Rent> filterUnfinishedRentsForCatalog(int id, String rentFilter) {
        return rentAdapter.filterUnfinishedRentsForCatalog(id, rentFilter);
    }

    public List<Rent> filterFinishedRentsForCatalog(int id, String rentFilter) {
        return rentAdapter.filterFinishedRentsForCatalog(id, rentFilter);
    }

    public void finishRent(String id) {
        Rent temp = new Rent(UUID.fromString(id));
        if (getUnfinishedRents().contains(temp)) {
            temp = getUnfinishedRents().get(getUnfinishedRents().indexOf(temp));
            if (temp.getRentDateTime().isAfter(LocalDateTime.now())) {
                return;
            }
            temp.setReturnDateTime(LocalDateTime.now().getYear(),
                    LocalDateTime.now().getMonthValue(),
                    LocalDateTime.now().getDayOfMonth(),
                    LocalDateTime.now().getHour(),
                    LocalDateTime.now().getMinute());
            rentAdapter.updateRent(UUID.fromString(id), temp);
        }
    }
}
