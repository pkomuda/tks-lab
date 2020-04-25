package pl.lodz.p.it.tks.rest.adapters.repositories;

import org.apache.commons.lang3.StringUtils;
import pl.lodz.p.it.tks.rest.adapters.datamodel.catalogs.BookEntity;
import pl.lodz.p.it.tks.rest.adapters.datamodel.catalogs.CatalogEntity;
import pl.lodz.p.it.tks.rest.adapters.datamodel.catalogs.MovieEntity;

import javax.enterprise.context.Dependent;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Named
@Dependent
public class CatalogRepository {

    @PersistenceContext(unitName = "TKSUnit")
    private EntityManager entityManager;

    @Transactional
    public synchronized void addCatalog(CatalogEntity catalogEntity) {
        entityManager.getTransaction().begin();
        entityManager.persist(catalogEntity);
        entityManager.getTransaction().commit();
    }

    @Transactional
    public synchronized CatalogEntity getCatalog(int id) {
        CatalogEntity catalogEntity = entityManager.find(CatalogEntity.class, id);
        if (catalogEntity != null) {
            entityManager.detach(catalogEntity);
        }
        return catalogEntity;
    }

    @Transactional
    public synchronized void updateCatalog(int id, CatalogEntity catalogEntity) {
        CatalogEntity temp = entityManager.find(CatalogEntity.class, id);
        if (temp != null) {
            entityManager.getTransaction().begin();
            entityManager.detach(temp);
            temp.setTitle(catalogEntity.getTitle());
            temp.setReleaseYear(catalogEntity.getReleaseYear());
            if (temp instanceof BookEntity) {
                ((BookEntity) temp).setAuthor(((BookEntity) catalogEntity).getAuthor());
            } else if (temp instanceof MovieEntity) {
                ((MovieEntity) temp).setDirector(((MovieEntity) catalogEntity).getDirector());
                ((MovieEntity) temp).setFormat(((MovieEntity) catalogEntity).getFormat());
            }
            entityManager.merge(temp);
            entityManager.getTransaction().commit();
        }
    }

    @Transactional
    public synchronized void removeCatalog(int id) {
        CatalogEntity catalogEntity = entityManager.find(CatalogEntity.class, id);
        if (catalogEntity != null) {
            entityManager.getTransaction().begin();
            entityManager.remove(catalogEntity);
            entityManager.getTransaction().commit();
        }
    }

    @Transactional
    public synchronized List<CatalogEntity> getCatalogs() {
        return entityManager.createQuery("SELECT c FROM Catalog c", CatalogEntity.class).getResultList();
    }

    @Transactional
    public synchronized List<BookEntity> getBooks() {
        return entityManager.createQuery("SELECT c FROM Book c", BookEntity.class).getResultList();
    }

    @Transactional
    public synchronized List<MovieEntity> getMovies() {
        return entityManager.createQuery("SELECT c FROM Movie c", MovieEntity.class).getResultList();
    }

    public List<CatalogEntity> filterCatalogs(String catalogFilter) {
        return getCatalogs()
                .stream()
                .filter(catalogEntity -> StringUtils.containsIgnoreCase(catalogEntity.toString(), catalogFilter))
                .collect(Collectors.toList());
    }

    public List<BookEntity> filterBooks(String bookFilter) {
        return getBooks()
                .stream()
                .filter(bookEntity -> StringUtils.containsIgnoreCase(bookEntity.toString(), bookFilter))
                .collect(Collectors.toList());
    }

    public List<MovieEntity> filterMovies(String movieFilter) {
        return getMovies()
                .stream()
                .filter(movieEntity -> StringUtils.containsIgnoreCase(movieEntity.toString(), movieFilter))
                .collect(Collectors.toList());
    }
}
