package pl.lodz.p.it.tks.appservices.services.user;

public interface UserCrudService {

    void addAdmin(String username, boolean active, String firstName, String lastName);
    void addManager(String username, boolean active, String firstName, String lastName);
    void addClient(String username, boolean active, String firstName, String lastName) ;
    void updateUser(String username, String firstName, String lastName, boolean active);
}
