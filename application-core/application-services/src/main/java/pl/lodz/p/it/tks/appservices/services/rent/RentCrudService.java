package pl.lodz.p.it.tks.appservices.services.rent;

public interface RentCrudService {

    void addRent(String username, int catalogId, int year, int month, int day, int hour, int minute);
    void removeRent(String id);
    void finishRent(String id);
}
