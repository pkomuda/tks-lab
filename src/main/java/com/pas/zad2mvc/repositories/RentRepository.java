package com.pas.zad2mvc.repositories;

import com.pas.zad2mvc.model.Book;
import com.pas.zad2mvc.model.Client;
import com.pas.zad2mvc.model.Movie;
import com.pas.zad2mvc.model.Rent;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

@Named
@ApplicationScoped
public class RentRepository {
    private LinkedHashMap<String, Rent> rents = new LinkedHashMap<>();

    public synchronized void addRent(Rent rent) {
        rents.put(rent.getId(), rent);
    }

    public synchronized Rent getRent(String id) {
        return rents.get(id);
    }

    public synchronized void removeRent(String id) {
        rents.remove(id);
    }

    public synchronized List<Rent> getRents() {
        return new ArrayList<>(rents.values());
    }

    public List<Rent> getUnfinishedRents() {
        return getRents()
                .stream()
                .filter(rent -> rent.getReturnDateTime() == null)
                .collect(Collectors.toList());
    }

    public List<Rent> getFinishedRents() {
        return getRents()
                .stream()
                .filter(rent -> rent.getReturnDateTime() != null)
                .collect(Collectors.toList());
    }

    public List<Rent> getRentsForClient(String username) {
        return getRents()
                .stream()
                .filter(rent -> rent.getClient().getUsername().equals(username))
                .collect(Collectors.toList());
    }

    public List<Rent> getUnfinishedRentsForClient(String username) {
        return getRentsForClient(username)
                .stream()
                .filter(rent -> rent.getReturnDateTime() == null)
                .collect(Collectors.toList());
    }

    public List<Rent> getFinishedRentsForClient(String username) {
        return getRentsForClient(username)
                .stream()
                .filter(rent -> rent.getReturnDateTime() != null)
                .collect(Collectors.toList());
    }

    public List<Rent> getRentsForCatalog(int id) {
        return getRents()
                .stream()
                .filter(rent -> rent.getCatalog().getId() == id)
                .collect(Collectors.toList());
    }

    public List<Rent> getUnfinishedRentsForCatalog(int id) {
        return getRentsForCatalog(id)
                .stream()
                .filter(rent -> rent.getReturnDateTime() == null)
                .collect(Collectors.toList());
    }

    public List<Rent> getFinishedRentsForCatalog(int id) {
        return getRentsForCatalog(id)
                .stream()
                .filter(rent -> rent.getReturnDateTime() != null)
                .collect(Collectors.toList());
    }

    public List<Rent> filterRents(String rentFilter) {
        return getRents()
                .stream()
                .filter(rent -> StringUtils.containsIgnoreCase(rent.toString(), rentFilter))
                .collect(Collectors.toList());
    }

    public List<Rent> filterUnfinishedRents(String rentFilter) {
        return getUnfinishedRents()
                .stream()
                .filter(rent -> StringUtils.containsIgnoreCase(rent.toString(), rentFilter))
                .collect(Collectors.toList());
    }

    public List<Rent> filterFinishedRents(String rentFilter) {
        return getFinishedRents()
                .stream()
                .filter(rent -> StringUtils.containsIgnoreCase(rent.toString(), rentFilter))
                .collect(Collectors.toList());
    }

    public List<Rent> filterRentsForClient(String username, String rentFilter) {
        return getRentsForClient(username)
                .stream()
                .filter(rent -> StringUtils.containsIgnoreCase(rent.toString(), rentFilter))
                .collect(Collectors.toList());
    }

    public List<Rent> filterUnfinishedRentsForClient(String username, String rentFilter) {
        return getUnfinishedRentsForClient(username)
                .stream()
                .filter(rent -> StringUtils.containsIgnoreCase(rent.toString(), rentFilter))
                .collect(Collectors.toList());
    }

    public List<Rent> filterFinishedRentsForClient(String username, String rentFilter) {
        return getFinishedRentsForClient(username)
                .stream()
                .filter(rent -> StringUtils.containsIgnoreCase(rent.toString(), rentFilter))
                .collect(Collectors.toList());
    }

    public List<Rent> filterRentsForCatalog(int id, String rentFilter) {
        return getRentsForCatalog(id)
                .stream()
                .filter(rent -> StringUtils.containsIgnoreCase(rent.toString(), rentFilter))
                .collect(Collectors.toList());
    }

    public List<Rent> filterUnfinishedRentsForCatalog(int id, String rentFilter) {
        return getUnfinishedRentsForCatalog(id)
                .stream()
                .filter(rent -> StringUtils.containsIgnoreCase(rent.toString(), rentFilter))
                .collect(Collectors.toList());
    }

    public List<Rent> filterFinishedRentsForCatalog(int id, String rentFilter) {
        return getFinishedRentsForCatalog(id)
                .stream()
                .filter(rent -> StringUtils.containsIgnoreCase(rent.toString(), rentFilter))
                .collect(Collectors.toList());
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
        Rent rent1 = new Rent(new Client("client1", true, "Kim", "Wexler"), new Book(1, "The Shining", "Stephen King", 1997));
        rent1.setRentDateTime(2019, 11, 28, 17, 30);
        addRent(rent1);
        Rent rent2 = new Rent(new Client("client1", true, "Kim", "Wexler"), new Movie(4, "Trainspotting", "Danny Boyle", 1996, "DVD"));
        rent2.setRentDateTime(2019, 12, 1, 13, 25);
        addRent(rent2);
        Rent rent3 = new Rent(new Client("client2", true, "Gustavo", "Fring"), new Book(2, "The Lord of the Rings", "J.R.R. Tolkien", 2015));
        rent3.setRentDateTime(2019, 12, 5, 10, 11);
        addRent(rent3);
    }
}
