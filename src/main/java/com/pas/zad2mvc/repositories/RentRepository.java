package com.pas.zad2mvc.repositories;

import com.pas.zad2mvc.model.*;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

@Named
@ApplicationScoped
public class RentRepository {
    @Inject
    private UserRepository userRepository;
    @Inject
    private CatalogRepository catalogRepository;
    private LinkedHashMap<String, Rent> rents = new LinkedHashMap<>();

    private List<Rent> getUnfinishedRents() {
        return getRents()
                .stream()
                .filter(rent -> rent.getReturnDateTime() == null)
                .collect(Collectors.toList());
    }

    public void addRent(String username, int catalogId, int year, int month, int day, int hour, int minute) {
        if (userRepository.getUser(username) instanceof Client
                && userRepository.getUser(username).isActive()
                && getUnfinishedRentsForCatalog(catalogId).isEmpty()) {
            Rent rent = new Rent((Client) userRepository.getUser(username), catalogRepository.getCatalog(catalogId));
            rent.setRentDateTime(year, month, day, hour, minute);
            rents.put(rent.getId(), rent);
        }
    }

    public Rent getRent(String id) {
        return rents.get(id);
    }

    public List<Rent> getRents() {
        return new ArrayList<>(rents.values());
    }

    public List<Rent> getRentsForClient(String username) {
        return getRents()
                .stream()
                .filter(rent -> rent.getClient().getUsername().equals(username))
                .collect(Collectors.toList());
    }

    public List<Rent> getUnfinishedRentsForCatalog(int id) {
        return getRents()
                .stream()
                .filter(rent -> rent.getCatalog().getId() == id)
                .filter(rent -> rent.getReturnDateTime() == null)
                .collect(Collectors.toList());
    }

    public List<Rent> getRentsForCatalog(int id) {
        return getRents()
                .stream()
                .filter(rent -> rent.getCatalog().getId() == id)
                .collect(Collectors.toList());
    }

    public List<Rent> filterRents(String rentFilter) {
        return getRents()
                .stream()
                .filter(rent -> StringUtils.containsIgnoreCase(rent.toString(), rentFilter))
                .collect(Collectors.toList());
    }

    public List<Rent> filterRentsForClient(String username, String rentFilter) {
        return getRents()
                .stream()
                .filter(rent -> rent.getClient().getUsername().equals(username))
                .filter(rent -> StringUtils.containsIgnoreCase(rent.toString(), rentFilter))
                .collect(Collectors.toList());
    }

    public void finishRent(String id) {
        Rent temp = new Rent(id);
        if (getUnfinishedRents().contains(temp)) {
            if (getUnfinishedRents().get(getUnfinishedRents().indexOf(temp)).getRentDateTime().isAfter(LocalDateTime.now())) {
                return;
            }
            getRent(id).setReturnDateTime(LocalDateTime.now().getYear()
                    , LocalDateTime.now().getMonthValue()
                    , LocalDateTime.now().getDayOfMonth()
                    , LocalDateTime.now().getHour()
                    , LocalDateTime.now().getMinute());
        }
    }

    public void removeRent(String id) {
        rents.remove(id);
    }

    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < rents.size(); i++) {
            if (i == 0) {
                str = str.concat(getRents().get(i).toString() + "\n");
            } else {
                str = str.concat("\t   " + getRents().get(i).toString());
                if (i != rents.size() - 1) {
                    str = str.concat("\n");
                }
            }
        }
        return "RentRepo[" + str + "]";
    }

    @PostConstruct
    private void addRentPool() {
        addRent("client1", 1, 2019, 11, 28, 17, 30);
        addRent("client1", 4, 2019, 12, 1, 13, 25);
        addRent("client2", 2, 2019, 12, 5, 10, 11);
    }
}
