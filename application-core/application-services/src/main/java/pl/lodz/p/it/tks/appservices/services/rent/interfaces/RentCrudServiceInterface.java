package pl.lodz.p.it.tks.appservices.services.rent.interfaces;

public interface RentCrudServiceInterface {

    public void addRent(String username, int catalogId, int year, int month, int day, int hour, int minute);
    public void removeRent(String id);
    public void finishRent(String id);
}
