package pl.lodz.p.it.tks.userservice.repositories;

import org.apache.commons.lang3.StringUtils;
import pl.lodz.p.it.tks.userservice.datamodel.UserEntity;

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

    @PersistenceContext(unitName = "TKSUserServiceUnit")
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
            temp.setPassword(userEntity.getPassword());
            temp.setFirstName(userEntity.getFirstName());
            temp.setLastName(userEntity.getLastName());
            temp.setActive(userEntity.isActive());
            temp.setType(userEntity.getType());
            entityManager.merge(temp);
            entityManager.getTransaction().commit();
        }
    }

    @Transactional
    public synchronized List<UserEntity> getUsers() {
        return entityManager.createQuery("SELECT u FROM UserEntity u", UserEntity.class).getResultList();
    }

    public List<UserEntity> filterUsers(String userFilter) {
        return getUsers()
                .stream()
                .filter(user -> StringUtils.containsIgnoreCase(user.toString(), userFilter))
                .collect(Collectors.toList());
    }
}
