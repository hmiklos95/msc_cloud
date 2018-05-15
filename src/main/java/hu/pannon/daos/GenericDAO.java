package hu.pannon.daos;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class GenericDAO<T> {


    private static final EntityManagerFactory emfInstance =
            Persistence.createEntityManagerFactory("transactions-optional");

    protected EntityManager em() {
        return emfInstance .createEntityManager();
    }

    protected Class<T> entityClass;

    public GenericDAO(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public List<T> findAll() {
        EntityManager em = em();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(entityClass);
        Root<T> rootEntry = cq.from(entityClass);
        CriteriaQuery<T> all = cq.select(rootEntry);

        final TypedQuery<T> query = em.createQuery(all);
        return query.getResultList();
    }

    public T findById(Object id) {
        return em().find(entityClass, id);
    }

    public T save(T t) {
        EntityManager em = em();
        em.getTransaction().begin();
        T merged = em.merge(t);
        em.flush();
        em.getTransaction().commit();
        em.close();
        return merged;
    }
}
