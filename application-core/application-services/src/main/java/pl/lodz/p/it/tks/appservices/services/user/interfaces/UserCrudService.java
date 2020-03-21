package pl.lodz.p.it.tks.appservices.services.user.interfaces;

public interface UserCrudService {

    void addAdmin(String username, boolean active, String firstName, String lastName, String password);
    void addManager(String username, boolean active, String firstName, String lastName, String password);
    void addClient(String username, boolean active, String firstName, String lastName, String password) ;
    void updateUser(String username, String firstName, String lastName, boolean active);
}
