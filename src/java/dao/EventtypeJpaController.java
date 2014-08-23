/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import dao.exceptions.NonexistentEntityException;
import dao.exceptions.PreexistingEntityException;
import entity.Eventtype;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Eric
 */
public class EventtypeJpaController {

    public EventtypeJpaController() {
        emf = Persistence.createEntityManagerFactory("blinkerPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Eventtype eventtype) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(eventtype);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEventtype(eventtype.getEventTypeID()) != null) {
                throw new PreexistingEntityException("Eventtype " + eventtype + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Eventtype eventtype) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            eventtype = em.merge(eventtype);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = eventtype.getEventTypeID();
                if (findEventtype(id) == null) {
                    throw new NonexistentEntityException("The eventtype with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Eventtype eventtype;
            try {
                eventtype = em.getReference(Eventtype.class, id);
                eventtype.getEventTypeID();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The eventtype with id " + id + " no longer exists.", enfe);
            }
            em.remove(eventtype);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Eventtype> findEventtypeEntities() {
        return findEventtypeEntities(true, -1, -1);
    }

    public List<Eventtype> findEventtypeEntities(int maxResults, int firstResult) {
        return findEventtypeEntities(false, maxResults, firstResult);
    }

    private List<Eventtype> findEventtypeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Eventtype.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Eventtype findEventtype(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Eventtype.class, id);
        } finally {
            em.close();
        }
    }

    public int getEventtypeCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Eventtype> rt = cq.from(Eventtype.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
