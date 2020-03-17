package pl.lodz.p.it.tks.adapters.repositories;

import org.apache.commons.lang3.StringUtils;
import pl.lodz.p.it.tks.adapters.datamodel.RentEntity;

import javax.enterprise.context.Dependent;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Named
@Dependent
public class RentRepository {

    @PersistenceContext(unitName = "TKSUnit")
    private EntityManager entityManager;

    @Transactional
    public synchronized void addRent(RentEntity rentEntity) {
        entityManager.getTransaction().begin();
        entityManager.persist(rentEntity);
        entityManager.getTransaction().commit();
    }

    @Transactional
    public synchronized void updateRent(UUID id, RentEntity rentEntity) {
        RentEntity temp = entityManager.find(RentEntity.class, id);
        if (temp != null) {
            entityManager.getTransaction().begin();
            entityManager.detach(temp);
            temp.setClientEntity(rentEntity.getClientEntity());
            temp.setCatalogEntity(rentEntity.getCatalogEntity());
            temp.setRentDateTime(rentEntity.getRentDateTime());
            temp.setReturnDateTime(rentEntity.getReturnDateTime());
            entityManager.merge(temp);
            entityManager.getTransaction().commit();
        }
    }

    @Transactional
    public synchronized void removeRent(UUID id) {
        RentEntity rentEntity = entityManager.find(RentEntity.class, id);
        if (rentEntity != null) {
            entityManager.getTransaction().begin();
            entityManager.remove(rentEntity);
            entityManager.getTransaction().commit();
        }
    }

    @Transactional
    public synchronized List<RentEntity> getRents() {
        return entityManager.createQuery("SELECT r FROM Rent r", RentEntity.class).getResultList();
    }

    @Transactional
    public synchronized List<RentEntity> getUnfinishedRents() {
        return entityManager.createQuery("SELECT r FROM Rent r WHERE r.returnDateTime = null", RentEntity.class).getResultList();
    }

    @Transactional
    public synchronized List<RentEntity> getFinishedRents() {
        return entityManager.createQuery("SELECT r FROM Rent r WHERE r.returnDateTime != null", RentEntity.class).getResultList();
    }

    @Transactional
    public synchronized List<RentEntity> getUnfinishedRentsForClient(String username) {
        TypedQuery<RentEntity> query = entityManager.createQuery("SELECT r FROM Rent r WHERE r.returnDateTime = null AND r.clientEntity.username = ?1", RentEntity.class);
        return query.setParameter(1, username).getResultList();
    }

    @Transactional
    public synchronized List<RentEntity> getFinishedRentsForClient(String username) {
        TypedQuery<RentEntity> query = entityManager.createQuery("SELECT r FROM Rent r WHERE r.returnDateTime != null AND r.clientEntity.username = ?1", RentEntity.class);
        return query.setParameter(1, username).getResultList();
    }

    @Transactional
    public synchronized List<RentEntity> getRentsForCatalog(int id) {
        TypedQuery<RentEntity> query = entityManager.createQuery("SELECT r FROM Rent r WHERE r.catalogEntity.id = ?1", RentEntity.class);
        return query.setParameter(1, id).getResultList();
    }

    @Transactional
    public synchronized List<RentEntity> getUnfinishedRentsForCatalog(int id) {
        TypedQuery<RentEntity> query = entityManager.createQuery("SELECT r FROM Rent r WHERE r.returnDateTime = null AND r.catalogEntity.id = ?1", RentEntity.class);
        return query.setParameter(1, id).getResultList();
    }

    @Transactional
    public synchronized List<RentEntity> getFinishedRentsForCatalog(int id) {
        TypedQuery<RentEntity> query = entityManager.createQuery("SELECT r FROM Rent r WHERE r.returnDateTime != null AND r.catalogEntity.id = ?1", RentEntity.class);
        return query.setParameter(1, id).getResultList();
    }

    public List<RentEntity> filterRents(String rentFilter) {
        return getRents()
                .stream()
                .filter(rentEntity -> StringUtils.containsIgnoreCase(rentEntity.toString(), rentFilter))
                .collect(Collectors.toList());
    }

    public List<RentEntity> filterUnfinishedRents(String rentFilter) {
        return getUnfinishedRents()
                .stream()
                .filter(rentEntity -> StringUtils.containsIgnoreCase(rentEntity.toString(), rentFilter))
                .collect(Collectors.toList());
    }

    public List<RentEntity> filterFinishedRents(String rentFilter) {
        return getFinishedRents()
                .stream()
                .filter(rentEntity -> StringUtils.containsIgnoreCase(rentEntity.toString(), rentFilter))
                .collect(Collectors.toList());
    }

    public List<RentEntity> filterUnfinishedRentsForClient(String username, String rentFilter) {
        return getUnfinishedRentsForClient(username)
                .stream()
                .filter(rentEntity -> StringUtils.containsIgnoreCase(rentEntity.toString(), rentFilter))
                .collect(Collectors.toList());
    }

    public List<RentEntity> filterFinishedRentsForClient(String username, String rentFilter) {
        return getFinishedRentsForClient(username)
                .stream()
                .filter(rentEntity -> StringUtils.containsIgnoreCase(rentEntity.toString(), rentFilter))
                .collect(Collectors.toList());
    }

    public List<RentEntity> filterUnfinishedRentsForCatalog(int id, String rentFilter) {
        return getUnfinishedRentsForCatalog(id)
                .stream()
                .filter(rentEntity -> StringUtils.containsIgnoreCase(rentEntity.toString(), rentFilter))
                .collect(Collectors.toList());
    }

    public List<RentEntity> filterFinishedRentsForCatalog(int id, String rentFilter) {
        return getFinishedRentsForCatalog(id)
                .stream()
                .filter(rentEntity -> StringUtils.containsIgnoreCase(rentEntity.toString(), rentFilter))
                .collect(Collectors.toList());
    }
}
