package com.pas.zad2mvc.services;

import com.pas.zad2mvc.model.Rent;
import com.pas.zad2mvc.repositories.RentRepository;

import javax.inject.Inject;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import java.io.Serializable;
import java.util.List;

@Named
@Dependent
public class RentService implements Serializable {
    @Inject
    private RentRepository rentRepository;

    public void addRent(String username, int catalogId, int year, int month, int day, int hour, int minute) {
        rentRepository.addRent(username, catalogId, year, month, day, hour, minute);
    }

    public Rent getRent(String id) {
        return rentRepository.getRent(id);
    }

    public List<Rent> getRents() {
        return rentRepository.getRents();
    }

    public List<Rent> getRentsForClient(String username) {
        return rentRepository.getRentsForClient(username);
    }

    public List<Rent> getRentsForCatalog(int id) {
        return rentRepository.getRentsForCatalog(id);
    }

    public List<Rent> getUnfinishedRentsForCatalog(int id) {
        return rentRepository.getUnfinishedRentsForCatalog(id);
    }

    public List<Rent> filterRents(String rentFilter) {
        return rentRepository.filterRents(rentFilter);
    }

    public List<Rent> filterRentsForClient(String username, String rentFilter) {
        return rentRepository.filterRentsForClient(username, rentFilter);
    }

    public List<Rent> filterRentsForCatalog(int id, String rentFilter) {
        return rentRepository.filterRentsForCatalog(id, rentFilter);
    }

    public void finishRent(String id) {
        rentRepository.finishRent(id);
    }

    public void removeRent(String id) {
        rentRepository.removeRent(id);
    }

    @Override
    public String toString() {
        return rentRepository.toString();
    }
}
