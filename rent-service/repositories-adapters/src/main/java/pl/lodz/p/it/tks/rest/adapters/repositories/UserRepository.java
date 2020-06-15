package pl.lodz.p.it.tks.rest.adapters.repositories;

import org.apache.commons.lang3.StringUtils;
import pl.lodz.p.it.tks.rest.adapters.datamodel.users.AdminEntity;
import pl.lodz.p.it.tks.rest.adapters.datamodel.users.ClientEntity;
import pl.lodz.p.it.tks.rest.adapters.datamodel.users.ManagerEntity;
import pl.lodz.p.it.tks.rest.adapters.datamodel.users.UserEntity;

import javax.enterprise.context.Dependent;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Named
@Dependent
public class UserRepository {

    @PersistenceContext(unitName = "TKSUnit")
    private EntityManager entityManager;

    @Transactional
    public synchronized void addUser(UserEntity userEntity) {
        entityManager.getTransaction().begin();
        entityManager.persist(userEntity);
        entityManager.getTransaction().commit();
    }

    @Transactional
    public synchronized UserEntity getUser(String username) {
        UserEntity userEntity = entityManager.find(UserEntity.class, username);
        if (userEntity != null) {
            entityManager.detach(userEntity);
        }
        return userEntity;
    }

    @Transactional
    public synchronized void updateUser(String username, UserEntity userEntity) {
        UserEntity temp = entityManager.find(UserEntity.class, username);
        if (temp != null) {
            entityManager.getTransaction().begin();
            entityManager.detach(temp);
            temp.setFirstName(userEntity.getFirstName());
            temp.setLastName(userEntity.getLastName());
            temp.setActive(userEntity.isActive());
            entityManager.merge(temp);
            entityManager.getTransaction().commit();
        }
    }

    @Transactional
    public synchronized List<UserEntity> getUsers() {
        return entityManager.createQuery("SELECT a FROM Account a", UserEntity.class).getResultList();
    }

    @Transactional
    public synchronized List<AdminEntity> getAdmins() {
        return entityManager.createQuery("SELECT a FROM Admin a", AdminEntity.class).getResultList();
    }

    @Transactional
    public synchronized List<ManagerEntity> getManagers() {
        return entityManager.createQuery("SELECT a FROM Manager a", ManagerEntity.class).getResultList();
    }

    @Transactional
    public synchronized List<ClientEntity> getClients() {
        return entityManager.createQuery("SELECT a FROM Client a", ClientEntity.class).getResultList();
    }

    public List<AdminEntity> filterAdmins(String adminFilter) {
        return getAdmins()
                .stream()
                .filter(user -> StringUtils.containsIgnoreCase(user.toString(), adminFilter))
                .collect(Collectors.toList());
    }

    public List<ManagerEntity> filterManagers(String managerFilter) {
        return getManagers()
                .stream()
                .filter(user -> StringUtils.containsIgnoreCase(user.toString(), managerFilter))
                .collect(Collectors.toList());
    }

    public List<ClientEntity> filterClients(String clientFilter) {
        return getClients()
                .stream()
                .filter(user -> StringUtils.containsIgnoreCase(user.toString(), clientFilter))
                .collect(Collectors.toList());
    }
}
