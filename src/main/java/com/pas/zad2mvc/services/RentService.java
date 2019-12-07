package com.pas.zad2mvc.services;

import com.pas.zad2mvc.model.Client;
import com.pas.zad2mvc.model.Rent;
import com.pas.zad2mvc.repositories.CatalogRepository;
import com.pas.zad2mvc.repositories.RentRepository;
import com.pas.zad2mvc.repositories.UserRepository;

import javax.inject.Inject;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Named
@Dependent
public class RentService implements Serializable {
    @Inject
    private RentRepository rentRepository;
    @Inject
    private UserRepository userRepository;
    @Inject
    private CatalogRepository catalogRepository;

    public void addRent(String username, int catalogId, int year, int month, int day, int hour, int minute) {
        if (userRepository.getUser(username) instanceof Client
                && userRepository.getUser(username).isActive()
                && getUnfinishedRentsForCatalog(catalogId).isEmpty()) {
            Rent rent = new Rent((Client) userRepository.getUser(username), catalogRepository.getCatalog(catalogId));
            rent.setRentDateTime(year, month, day, hour, minute);
            rentRepository.addRent(rent);
        }
    }

    public Rent getRent(String id) {
        return rentRepository.getRent(id);
    }

    public void removeRent(String id) {
        rentRepository.removeRent(id);
    }

    public List<Rent> getRents() {
        return rentRepository.getRents();
    }

    public List<Rent> getUnfinishedRents() {
        return rentRepository.getUnfinishedRents();
    }

    public List<Rent> getFinishedRents() {
        return rentRepository.getFinishedRents();
    }

    public List<Rent> getRentsForClient(String username) {
        return rentRepository.getRentsForClient(username);
    }

    public List<Rent> getUnfinishedRentsForClient(String username) {
        return rentRepository.getUnfinishedRentsForClient(username);
    }

    public List<Rent> getFinishedRentsForClient(String username) {
        return rentRepository.getFinishedRentsForClient(username);
    }

    public List<Rent> getRentsForCatalog(int id) {
        return rentRepository.getRentsForCatalog(id);
    }

    public List<Rent> getUnfinishedRentsForCatalog(int id) {
        return rentRepository.getUnfinishedRentsForCatalog(id);
    }

    public List<Rent> getFinishedRentsForCatalog(int id) {
        return rentRepository.getFinishedRentsForCatalog(id);
    }

    public List<Rent> filterRents(String rentFilter) {
        return rentRepository.filterRents(rentFilter);
    }

    public List<Rent> filterUnfinishedRents(String rentFilter) {
        return rentRepository.filterUnfinishedRents(rentFilter);
    }

    public List<Rent> filterFinishedRents(String rentFilter) {
        return rentRepository.filterFinishedRents(rentFilter);
    }

    public List<Rent> filterRentsForClient(String username, String rentFilter) {
        return rentRepository.filterRentsForClient(username, rentFilter);
    }

    public List<Rent> filterUnfinishedRentsForClient(String username, String rentFilter) {
        return rentRepository.filterUnfinishedRentsForClient(username, rentFilter);
    }

    public List<Rent> filterFinishedRentsForClient(String username, String rentFilter) {
        return rentRepository.filterFinishedRentsForClient(username, rentFilter);
    }

    public List<Rent> filterRentsForCatalog(int id, String rentFilter) {
        return rentRepository.filterRentsForCatalog(id, rentFilter);
    }

    public List<Rent> filterUnfinishedRentsForCatalog(int id, String rentFilter) {
        return rentRepository.filterUnfinishedRentsForCatalog(id, rentFilter);
    }

    public List<Rent> filterFinishedRentsForCatalog(int id, String rentFilter) {
        return rentRepository.filterFinishedRentsForCatalog(id, rentFilter);
    }

    public void finishRent(String id) {
        Rent temp = new Rent(id);
        if (getUnfinishedRents().contains(temp)) {
            if (getUnfinishedRents().get(getUnfinishedRents().indexOf(temp)).getRentDateTime().isAfter(LocalDateTime.now())) {
                return;
            }
            getRent(id).setReturnDateTime(LocalDateTime.now().getYear(),
                    LocalDateTime.now().getMonthValue(),
                    LocalDateTime.now().getDayOfMonth(),
                    LocalDateTime.now().getHour(),
                    LocalDateTime.now().getMinute());
        }
    }

    @Override
    public String toString() {
        return rentRepository.toString();
    }
}
